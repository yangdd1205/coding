package junit.v1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSuite implements Test {

    private List<Test> testList = new ArrayList<>();


    public TestSuite(Class<? extends Test> testClass) {
        //TODO 没有获取父类的方法
        Method[] methods = testClass.getDeclaredMethods();
        Arrays.asList(methods).forEach(method -> {
            addTestMethod(method, testClass);
        });

    }

    private void addTestMethod(Method method, Class<? extends Test> testClass) {
        if (isPublicMethod(method)) {
            addTest(method, testClass);
        }
    }

    private void addTest(Method method, Class<? extends Test> testClass) {
        try {
            Constructor constructor = testClass.getConstructor(null);
            TestCase testCase = (TestCase) constructor.newInstance(null);
            testCase.setName(method.getName());
            testList.add(testCase);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private boolean isPublicMethod(Method method) {
        return method.getModifiers() == Modifier.PUBLIC && isTestMethod(method);
    }

    private boolean isTestMethod(Method method) {
        return method.getName().startsWith("test") && method.getParameterCount() == 0 && method.getReturnType().equals(Void.TYPE);
    }

    @Override
    public int countTestCases() {
        int count = 0;
        for (Test test : testList) {
            count += test.countTestCases();
        }
        return count;
    }

    @Override
    public void run(TestResult result) {
        testList.forEach(test -> {
            test.run(result);
        });
    }


}
