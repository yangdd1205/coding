package thinking.in.spring.annotation;

import java.lang.annotation.*;

/**
 * 配置注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyConfiguration {

    /**
     * 名称属性
     * @return
     */
    String name();
}
