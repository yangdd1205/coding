package thinking.in.spring.ioc.overview.dependency.injection;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import thinking.in.spring.ioc.overview.repository.UserRepository;

/**
 * 依赖注入
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {


        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        //依赖来源一： 自定义 Bean
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
//        System.out.println(userRepository.getUsers());

        // 依赖来源二：依赖注入（内建依赖）
        System.out.println(userRepository.getBeanFactory());

        ObjectFactory objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject() == beanFactory);

        // 依赖查找（错误）
        //System.out.println(beanFactory.getBean(BeanFactory.class));


        //依赖来源三：容器内建 Bean

        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("获取 Environment  类型的 Bean: " + environment);


        whoIsIocContainer(userRepository,beanFactory);

    }

    private static void whoIsIocContainer(UserRepository userRepository, BeanFactory beanFactory) {
        // 为什么不会成立
        // ApplicationContext is sub-interface BeanFactory BeanFactory 是一个最基本的 IoC 容器，而 ApplicationContext 提供了更多的功能
        // ApplicationContext 组合了 BeanFactory 的实现
        // 这是两个对象
         System.out.println(userRepository.getBeanFactory()  == beanFactory);

         // ApplicationContext is BeanFactory


    }


}
