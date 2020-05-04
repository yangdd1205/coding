package thinking.in.spring.dependency.injection;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import thinking.in.spring.dependency.injection.annotation.UserGroup;
import thinking.in.spring.ioc.overview.domain.User;

import java.util.Collection;
import java.util.Set;

/**
 * {@link org.springframework.beans.factory.ObjectProvider} 实现延迟注入
 */
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;// 实时注入

    @Autowired
    private ObjectProvider<User> userObjectProvider; // 延迟注入

    @Autowired
    private ObjectFactory<Set<User>> usersObjectFactory;

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class 配置类，配置类也是 Spring Bean
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);


        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);


        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);


        applicationContext.refresh();


        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);


        System.out.println("demo.user = " + demo.user);


        System.out.println("demo.userObjectProvider = " + demo.userObjectProvider.getObject());
        System.out.println("demo.usersObjectFactory = " + demo.usersObjectFactory.getObject());

        System.out.println(demo.user == demo.userObjectProvider.getObject());


        demo.userObjectProvider.forEach(System.out::println);


        applicationContext.close();
    }
}
