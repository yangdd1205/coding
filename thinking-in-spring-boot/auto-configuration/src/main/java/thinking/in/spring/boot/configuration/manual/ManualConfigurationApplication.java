package thinking.in.spring.boot.configuration.manual;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import thinking.in.spring.boot.configuration.manual.config.ColorConfiguration;

import java.util.stream.Stream;

/**
 * SpringFramework 手动配置示例
 */
public class ManualConfigurationApplication {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ColorConfiguration.class);

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        Stream.of(beanDefinitionNames).forEach(System.out::println);

    }

}
