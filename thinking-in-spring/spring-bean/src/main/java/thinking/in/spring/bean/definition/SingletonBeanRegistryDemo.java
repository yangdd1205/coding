package thinking.in.spring.bean.definition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import thinking.in.spring.bean.factory.DefaultUserFactory;
import thinking.in.spring.bean.factory.UserFactory;

/**
 * 外部单例注册
 */
public class SingletonBeanRegistryDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        UserFactory userFactory = new DefaultUserFactory();
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        beanFactory.registerSingleton("userFactory", userFactory);
        // 注册 外部单例对象
        applicationContext.refresh();


        UserFactory userFactoryByLookup = beanFactory.getBean("userFactory", UserFactory.class);

        System.out.println(userFactory == userFactoryByLookup);


        applicationContext.close();
    }
}
