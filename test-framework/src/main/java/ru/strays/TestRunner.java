package ru.strays;

import ru.strays.annotations.After;
import ru.strays.annotations.Before;
import ru.strays.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.strays.ReflectionUtils.getNoArgsConstructor;
import static ru.strays.ReflectionUtils.invokeMethod;

public class TestRunner {

    private final List<TestRunResult> results;
    private final Class<?> testClass;
    private final Constructor<?> testClassConstructor;

    public TestRunner(Class<?> testClass) {
        this.testClass = testClass;
        this.testClassConstructor = getNoArgsConstructor(testClass);
        this.results = new ArrayList<>();
    }

    public static void runForClassName(String className) {
        TestRunner runner = new TestRunner(ReflectionUtils.getClass(className));
        runner.run();
    }

    public void run() {
        Method[] declaredMethods = testClass.getDeclaredMethods();
        Method[] allPublicMethods = testClass.getMethods();

        Method[] testMethods = getTestMethods(declaredMethods);
        Method[] setUpMethods = getSetUpMethods(allPublicMethods);
        Method[] tearDownMethods = getTearDownMethods(allPublicMethods);

        for (Method testMethod : testMethods) {
            TestRunResult testRunResult = runTestMethod(testMethod, setUpMethods, tearDownMethods);
            results.add(testRunResult);
            Reporter.reportSingle(testRunResult);
        }
        Reporter.reportSummary(results);
    }

    private TestRunResult runTestMethod(Method testMethod, Method[] setUps, Method[] tearDowns) {
        try {
            Object testObject = testClassConstructor.newInstance();
            Arrays.stream(setUps).forEach(method -> invokeMethod(testObject, method));
            testMethod.invoke(testObject);
            Arrays.stream(tearDowns).forEach(method -> invokeMethod(testObject, method));
            return new TestRunResult(testMethod.getName(), Status.SUCCESS);
        } catch (Exception e) {
            return new TestRunResult(testMethod.getName(), Status.FAIL, e);
        }
    }

    private Method[] getTestMethods(Method[] methods) {
        return Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(Test.class))
                .filter(method -> Modifier.isPublic(method.getModifiers()))
                .toArray(Method[]::new);
    }

    private Method[] getSetUpMethods(Method[] methods) {
        return Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(Before.class))
                .toArray(Method[]::new);
    }

    private Method[] getTearDownMethods(Method[] methods) {
        return Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(After.class))
                .toArray(Method[]::new);
    }
}
