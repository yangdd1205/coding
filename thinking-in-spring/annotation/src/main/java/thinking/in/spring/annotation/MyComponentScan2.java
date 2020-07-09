package thinking.in.spring.annotation;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 自定义 ComponentScan 注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponentScan // 元注解。实现 @Component 的派生
public @interface MyComponentScan2 {


    @AliasFor(annotation = MyComponentScan.class, attribute = "scanBasePackages")//隐形别名
            //"多态"，主注解提供新的属性方法引用"父"（元）注解中的属性方法
    String[] basePackages() default {};


    // 通过#来判断覆盖
    String[] scanBasePackages() default {};



    @AliasFor("scanBasePackages")
    String[] packages() default {};

    
}
