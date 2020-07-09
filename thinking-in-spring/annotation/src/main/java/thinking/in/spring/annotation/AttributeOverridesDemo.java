package thinking.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring 注解属性覆盖示例
 */
//@MyComponentScan2(scanBasePackages = "thinking.in.spring.annotation")
// @MyComponentScan2.scanBasePackages 覆盖  @MyComponentScan.scanBasePackages
@MyComponentScan2(packages = "thinking.in.spring.annotation")
//@MyComponentScan2.packages 覆盖了 ->  @MyComponentScan2.scanBasePackages 覆盖了 ->  @MyComponentScan.scanBasePackages

public class AttributeOverridesDemo {

    public static void main(String[] args) {


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ComponentScanDemo.class);


        context.refresh();


        TestClass testClass = context.getBean(TestClass.class);
        System.out.println(testClass);

        // Annotation -> AnnotationAttributes
        context.close();


    }

}
