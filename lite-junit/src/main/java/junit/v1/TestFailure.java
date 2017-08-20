package junit.v1;


public class TestFailure {
    private Test failureTest;
    private Throwable thrownException;

    public TestFailure(Test failureTest, Throwable thrownException) {
        this.failureTest = failureTest;
        this.thrownException = thrownException;
    }

    public Test getFailureTest() {
        return failureTest;
    }

    public Throwable getThrownException() {
        return thrownException;
    }

}
