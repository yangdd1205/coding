package thinking.in.spring.dependency.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import thinking.in.spring.dependency.injection.annotation.UserGroup;
import thinking.in.spring.ioc.overview.domain.User;

import java.util.Collection;

/**
 * {@link org.springframework.beans.factory.annotation.Qualifier} 注解依赖注入
 */
public class QualifierAnnotationDependencyInjectionDemo {


    @Autowired
    private User user;//

    @Autowired
    @Qualifier("user")
    private User namedUser;

    @Bean
    @Qualifier
    public User user1(){
        return createUser(7L);
    }
    @Bean
    @Qualifier
    public User user2(){
        return createUser(8L);
    }


    @Bean
    @UserGroup
    public User user3(){
        return createUser(9L);
    }

    @Bean
    @UserGroup
    public User user4(){
        return createUser(10L);
    }

    private static User createUser(Long id){
        User user = new User();
        user.setId(id);
        return user;
    }




    // 整体应用上下文存在 6 个 User 类型 Bean:
    // superUser
    // user
    // user1 -> @Qualifier
    // user2 -> @Qualifier
    // user3 -> @UserGroup
    // user4 -> @UserGroup


    @Autowired
    private Collection<User> allUser; // 2 Bean  =  user + superUser

    @Autowired
    @Qualifier
    private Collection<User>  qualifierUser; // 4 Bean = user1 + user2 + user3 + user4

    @Autowired
    @UserGroup
    private Collection<User> groupUsers; // 2 Bean = user3 + user4


    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class 配置类，配置类也是 Spring Bean
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);


        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);


        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);


        applicationContext.refresh();


        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);


        System.out.println("demo.user = " + demo.user);
        System.out.println("demo.namedUser = " + demo.namedUser);
        System.out.println("demo.allUser = " + demo.allUser);
        System.out.println("demo.qualifierUser = " + demo.qualifierUser);
        System.out.println("demo.groupUsers = " + demo.groupUsers);
        applicationContext.close();
    }
}
