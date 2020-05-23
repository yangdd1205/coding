package thinking.in.spring.configuration.metadata;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import thinking.in.spring.ioc.overview.domain.User;
import thinking.in.spring.ioc.overview.enums.City;

import java.util.Map;

@PropertySource(name = "yamlPropertySource", value = "classpath:/META-INF/user.yaml", factory = YamlPropertySourceFactory.class)
public class AnnotatedYamlPropertySourceDemo {

    @Bean
    public User user(@Value("${user.id}") Long id, @Value("${user.name}") String name, @Value("${user.city}")City city) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCity(city);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AnnotatedYamlPropertySourceDemo.class);

        context.refresh();


        User user = context.getBean("user", User.class);
        System.out.println(user);

        context.close();
    }
}
