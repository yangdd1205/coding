package thinking.in.spring.annotation;


import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义 Component 注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent
public @interface MyComponent2 {
}
