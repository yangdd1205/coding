package v1;

import junit.v1.TestCase;
import junit.v1.TestResult;
import junit.v1.TestSuite;

import static junit.v1.Assert.assertEquals;

public class CalculatorTest extends TestCase {

    Calculator calculator = null;

    public void setUp() {
        calculator = new Calculator();
    }

    public void tearDown() {
        calculator = null;
    }

    public void testAdd() {

        calculator.add(10);
        assertEquals(10, calculator.getResult());
    }

    public void testSubtract() {
        calculator.add(10);
        calculator.subtract(5);
        assertEquals(4, calculator.getResult());
    }

    public static void main(String[] args) {
        TestResult testResult = new TestResult();
        new TestSuite(CalculatorTest.class).run(testResult);
        System.out.println("Tests run: " + testResult.runCount() + ", Failures: " + testResult.failureCount() + ", Errors: " + testResult.errorCount());
    }
}
