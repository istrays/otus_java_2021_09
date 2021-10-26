package ru.strays;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

class Reporter {

    private Reporter() { }

    static void reportSummary(List<TestRunResult> testResults) {
        System.out.println("==================================");
        System.out.println("Test results:");
        System.out.println("----------------------------------");
        System.out.printf("Total tests: %d%n", testResults.size());
        Map<Boolean, Long> resultsByPassed = testResults
                .stream()
                .collect(Collectors.partitioningBy(result -> Status.SUCCESS.equals(result.getStatus()), Collectors.counting()));
        System.out.printf("Passed tests: %d%n", resultsByPassed.get(true));
        System.out.printf("Failed tests: %d%n", resultsByPassed.get(false));
        System.out.println("==================================");
    }

    static void reportSingle(TestRunResult testRun) {
        System.out.println(testRun);
        if (!Objects.isNull(testRun.getException())) {
            testRun.getException().printStackTrace();
        }
    }
}
