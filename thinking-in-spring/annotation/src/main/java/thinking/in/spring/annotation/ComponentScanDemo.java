package thinking.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * {@link ComponentScan} 扫描示例
 */

//@ComponentScan(basePackages = "thinking.in.spring.annotation") // 指定 Class-Path(s)
//@MyComponentScan(scanBasePackages = "thinking.in.spring.annotation")
@MyComponentScan2(basePackages = "thinking.in.spring.annotation")
public class ComponentScanDemo {

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
