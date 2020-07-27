package thinking.in.spring.environment;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import thinking.in.spring.ioc.overview.domain.User;

/**
 * {@link org.springframework.beans.factory.config.PropertyPlaceholderConfigurer} 处理占位符示例
 */
public class PropertyPlaceholderConfigurerDemo {

    public static void main(String[] args) {


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/placeholders-resolver.xml");

        User user = context.getBean("user", User.class);

        System.out.println(user);

        context.close();
    }
}
