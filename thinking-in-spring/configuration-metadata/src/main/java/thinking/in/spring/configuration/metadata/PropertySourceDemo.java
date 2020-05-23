package thinking.in.spring.configuration.metadata;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;
import thinking.in.spring.ioc.overview.domain.User;

import java.util.HashMap;
import java.util.Map;

@PropertySource("classpath:/META-INF/user-bean-definitions.properties")
public class PropertySourceDemo {
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

        // 扩展 Environment 中的 PropertySource
        // 添加 PropertySource 操作必须在 refresh 之前完成
        Map<String, Object> propertiesSource = new HashMap<>();
        org.springframework.core.env.PropertySource propertySource = new MapPropertySource("first-property-source", propertiesSource);
        propertiesSource.put("user.name","yangdongdong");

        context.getEnvironment().getPropertySources().addFirst(propertySource);

        context.register(PropertySourceDemo.class);

        context.refresh();

        Map<String, User> usersMap = context.getBeansOfType(User.class);
        usersMap.forEach((k, v) -> {
            System.out.printf("User Bean name: %s，content：%s\n", k, v);
        });


        System.out.println(context.getEnvironment().getPropertySources());

        context.close();
    }
}
