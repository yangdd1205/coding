package thinking.in.spring.ioc.overview.container;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import thinking.in.spring.ioc.overview.domain.User;

import java.util.Map;

/**
 * 注解能力 {@link org.springframework.context.ApplicationContext } 作为 IoC 容器示例
 */
public class AnnotationApplicationContextAsIoCContainerDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 建当前类作为配置 Class
        applicationContext.register(AnnotationApplicationContextAsIoCContainerDemo.class);

        // 启动上下文容器
        applicationContext.refresh();

        lookupByCollectionType(applicationContext);


        //关闭容器
        applicationContext.close();
    }

    @Bean
    public User user(){
        User user = new User();
        user.setId(1L);
        user.setName("杨");
        return user;
    }


    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("通过类型查找到所有的 User 集合对象：" + users);
        }
    }
}
