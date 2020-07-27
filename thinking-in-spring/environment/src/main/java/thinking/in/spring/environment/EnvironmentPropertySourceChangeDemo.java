package thinking.in.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.*;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link Environment} 配置属性源变更示例
 */
public class EnvironmentPropertySourceChangeDemo {

    @Value("${user.name}") // 不具备动态更新能力
    private String userName;



    // PropertySource("first-property-source"){user.name=杨}
    // PropertySource(Java System property){user.name=yangdd}

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EnvironmentPropertySourceChangeDemo.class);

        ConfigurableEnvironment environment = context.getEnvironment();


        MutablePropertySources propertySources = environment.getPropertySources();

        // 动态地插入 PropertySource 到 propertySources
        Map<String,Object> source = new HashMap<>();
        source.put("user.name","杨");
        MapPropertySource propertySource = new MapPropertySource("first-property-source",source);
        propertySources.addFirst(propertySource);


        context.refresh();

        source.put("user.name","007");
        EnvironmentPropertySourceChangeDemo environmentPropertySourceChangeDemo = context.getBean(EnvironmentPropertySourceChangeDemo.class);
        System.out.println(environmentPropertySourceChangeDemo.userName);

        for (PropertySource<?> ps : propertySources) {
            System.out.printf("PropertySource(name=%s) 'user.name' 属性：%s\n",ps.getName(),ps.getProperty("user.name"));
        }

        context.close();


    }
}
