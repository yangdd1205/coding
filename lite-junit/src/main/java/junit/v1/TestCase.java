package junit.v1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class TestCase implements Test {

    protected String name;


    public void setName(String name) {
        this.name = name;
    }

    public int countTestCases() {
        return 1;
    }

    protected void setUp() {
    }

    protected void tearDown() {
    }

    @Override
    public void run(TestResult result) {
        result.run(this);
    }

    public void doRun() throws Throwable {
        setUp();
        try {
            doTest();
        } finally {
            tearDown();
        }
    }

    private void doTest() throws Throwable {
        Method method = null;
        try {
            method = getClass().getMethod(name);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        try {
            method.invoke(this, null);
        } catch (IllegalAccessException e) {
            e.fillInStackTrace();
            throw e;
        } catch (IllegalArgumentException e) {
            e.fillInStackTrace();
            throw e;
        } catch (InvocationTargetException e) {
            e.fillInStackTrace();
            throw e;
        }
    }

}
