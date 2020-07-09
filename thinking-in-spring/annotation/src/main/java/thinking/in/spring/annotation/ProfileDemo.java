package thinking.in.spring.annotation;

import org.springframework.context.annotation.*;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * {@link @ProFile} 示例
 */
@Configuration
public class ProfileDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ProfileDemo.class);


        // 获取 environment
        ConfigurableEnvironment environment = context.getEnvironment();
        environment.setDefaultProfiles("odd");// 默认 profiles，兜底
        environment.setActiveProfiles("even");//增加活跃的

        context.refresh();


        Integer number = context.getBean("number", Integer.class);
        System.out.println(number);

        // Annotation -> AnnotationAttributes
        context.close();

    }

    @Bean(name = "number")
    @Profile("odd")
    public Integer odd() {
        return 1;
    }

    @Bean(name = "number")
//    @Profile("even")
    @Conditional(EvenProfileConditional.class)
    public Integer even() {
        return 2;
    }
}
