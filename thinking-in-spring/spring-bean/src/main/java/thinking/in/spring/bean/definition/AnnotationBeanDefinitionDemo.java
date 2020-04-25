package thinking.in.spring.bean.definition;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import thinking.in.spring.ioc.overview.domain.User;


// 通过 Import
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class 配置类
        applicationContext.register(AnnotationBeanDefinitionDemo.class);


        registryUserBeanDefinition(applicationContext, "yangdd");
        registryUserBeanDefinition(applicationContext);


        applicationContext.refresh();


        System.out.println("所有 Config 类：" + applicationContext.getBeansOfType(Config.class));
        System.out.println("所有 User 类：" + applicationContext.getBeansOfType(User.class));

        applicationContext.close();
    }


    private static void registryUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", 1L).addPropertyValue("name", "杨");
        if (StringUtils.hasText(beanName)) {
            // Java API 命名方式注册
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            // Java API 非命名方式注册
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    private static void registryUserBeanDefinition(BeanDefinitionRegistry registry) {
        registryUserBeanDefinition(registry, null);
    }


    @Component//通过 component
    public static class Config {
        // 通过 Bean 注解
        @Bean(value = {"user", "yang"})
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("杨");
            return user;
        }
    }
}
