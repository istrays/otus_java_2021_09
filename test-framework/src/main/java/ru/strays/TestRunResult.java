package ru.strays;

import java.util.Objects;

public class TestRunResult {

    private final String testName;
    private final Status status;
    private final Exception exception;

    public TestRunResult(String testName, Status status, Exception exception) {
        this.testName = testName;
        this.status = status;
        this.exception = exception;
    }

    public TestRunResult(String testName, Status status) {
        this(testName, status, null);
    }

    public String getTestName() {
        return testName;
    }

    public Status getStatus() {
        return status;
    }

    public Exception getException() {
        return exception;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestRunResult that = (TestRunResult) o;
        return Objects.equals(testName, that.testName) &&
                status == that.status &&
                Objects.equals(exception, that.exception);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testName, status, exception);
    }

    @Override
    public String toString() {
        return String.format("Test result: [%s] - %s", testName, status);
    }
}
