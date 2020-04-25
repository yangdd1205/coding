package thinking.in.spring.bean.definition;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import thinking.in.spring.ioc.overview.domain.User;

/**
 * Bean 实例化示例
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");


        User userByStaticMethod = beanFactory.getBean("user-by-static-method", User.class);
        System.out.println(userByStaticMethod);
        User userByInstanceMethod = beanFactory.getBean("user-by-instance-method", User.class);
        System.out.println(userByInstanceMethod);
        System.out.println(userByStaticMethod == userByInstanceMethod);


        User userByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);
        System.out.println(userByFactoryBean);

        System.out.println(userByStaticMethod == userByFactoryBean);
    }


}
