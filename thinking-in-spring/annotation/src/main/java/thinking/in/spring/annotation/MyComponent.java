package thinking.in.spring.annotation;


import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 自定义 Component 注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component // 元注解。实现 @Component 的派生
public @interface MyComponent {
}
