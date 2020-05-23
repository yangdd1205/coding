package thinking.in.spring.configuration.metadata;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import thinking.in.spring.ioc.overview.domain.User;

import java.util.Map;

/**
 * 基于 Java 注解 Spring IoC 容器元信息配置示例
 */
// 将当前类作为 Configuration Class
@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
@Import(User.class)
@PropertySource("classpath:/META-INF/user-bean-definitions.properties") // Java 1.8+ @Repeatable 支持
@PropertySource("classpath:/META-INF/user-bean-definitions.properties")
public class AnnotatedSpringIoCContainerMetaConfigurationDemo {


    // user.name 是 Java properties 默认用户，而不是配置文件里面定义的
    @Bean
    public User configuredUser(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AnnotatedSpringIoCContainerMetaConfigurationDemo.class);

        context.refresh();

        Map<String, User> usersMap = context.getBeansOfType(User.class);
        usersMap.forEach((k, v) -> {
            System.out.printf("User Bean name: %s，content：%s\n", k, v);
        });

        context.close();
    }
}
