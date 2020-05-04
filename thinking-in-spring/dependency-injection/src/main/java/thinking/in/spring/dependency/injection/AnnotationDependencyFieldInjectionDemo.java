package thinking.in.spring.dependency.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import thinking.in.spring.ioc.overview.domain.User;

import javax.annotation.Resource;

/**
 * 基于 注解 资源的依赖 Field 方法注入示例
 */
public class AnnotationDependencyFieldInjectionDemo {

    @Autowired
    private
//    static @Autowired 会忽略掉 static 字段
    UserHolder userHolder;

    @Resource
    private UserHolder userHolder2;


    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class 配置类，配置类也是 Spring Bean
        applicationContext.register(AnnotationDependencyFieldInjectionDemo.class);


        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);


        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);


        applicationContext.refresh();


        AnnotationDependencyFieldInjectionDemo demo = applicationContext.getBean(AnnotationDependencyFieldInjectionDemo.class);


        // 通过 @Autowired 自动关联
        UserHolder userHolder = demo.userHolder;

        System.out.println(userHolder);


        System.out.println(demo.userHolder2);


        System.out.println(userHolder == demo.userHolder2);

        applicationContext.close();

    }

    @Bean
    public UserHolder userHolder(User user) {

        return new UserHolder(user);
    }
}


