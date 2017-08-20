package junit.v1;

import java.util.ArrayList;
import java.util.List;

public class TestResult {

    private List<TestFailure> failures;
    private List<TestFailure> errors;
    private int runTests;


    public TestResult() {
        failures = new ArrayList<>();
        errors = new ArrayList<>();
    }

    void run(final TestCase testCase) {
        startTest(testCase);
        try {
            testCase.doRun();
        } catch (AssertionFailedError e) {
            addFailure(testCase, e);
        } catch (Throwable throwable) {
            addError(testCase, throwable);
        }
    }

    private void startTest(TestCase testCase) {
        final int count = testCase.countTestCases();
        synchronized (this) {
            runTests += count;
        }
    }

    public void addError(Test test, Throwable t) {
        errors.add(new TestFailure(test, t));
    }

    public void addFailure(Test test, AssertionFailedError t) {
        failures.add(new TestFailure(test, t));
    }

    public List<TestFailure> getFailures() {
        return failures;
    }

    public List<TestFailure> getErrors() {
        return errors;
    }

    public int errorCount() {
        return errors.size();
    }

    public int failureCount() {
        return failures.size();
    }

    public int runCount() {
        return runTests;
    }
}
