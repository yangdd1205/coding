package thinking.in.spring.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
// 通过 @Import 具体实现
//@Import({HelloWorldConfiguration.class})// 方法一：通过 Configuration Class 实现
//@Import(HelloWorldImportSelector.class)//方法二：通过 ImportSelector 接口 实现
//@Import(HelloWorldImportBeanDefinitionRegistrar.class)//方法三：通过 ImportBeanDefinitionRegistrar 接口实现
@Import(HelloWorld.class) // 方法四：普通类，
public @interface EnableHelloWorld {
}
