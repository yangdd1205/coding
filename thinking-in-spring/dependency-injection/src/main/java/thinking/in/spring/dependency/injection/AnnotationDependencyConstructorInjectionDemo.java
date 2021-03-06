package thinking.in.spring.dependency.injection;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import thinking.in.spring.ioc.overview.domain.User;

/**
 * 基于 注解 资源的依赖 Constructor 方法注入示例
 */
public class AnnotationDependencyConstructorInjectionDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class 配置类
        applicationContext.register(AnnotationDependencyConstructorInjectionDemo.class);


        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);


        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);


        applicationContext.refresh();


        UserHolder userHolder = applicationContext.getBean(UserHolder.class);

        System.out.println(userHolder);


        applicationContext.close();

    }

    @Bean
    public UserHolder userHolder(User user) {

        return new UserHolder(user);
    }
}


