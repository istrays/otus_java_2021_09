package ru.strays;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtils {

    private ReflectionUtils() {
    }

    public static Class<?> getClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(String.format("Class for name [%s] was not found", className), e);
        }
    }

    public static Constructor<?> getNoArgsConstructor(Class<?> aClass) {
        try {
            return aClass.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(String.format("[%s] class does not have no args constructor", aClass.getName()), e);
        }
    }

    public static void invokeMethod(Object target, Method method) {
        try {
            method.invoke(target);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(String.format("The test method [%s] is not public", method.getName()), e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(String.format("Exception during test method [%s] invocation", method.getName()), e);
        }
    }
}
