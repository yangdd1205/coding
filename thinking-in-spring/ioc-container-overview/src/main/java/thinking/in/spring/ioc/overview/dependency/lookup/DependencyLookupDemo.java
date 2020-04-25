package thinking.in.spring.ioc.overview.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import thinking.in.spring.ioc.overview.annotation.Super;
import thinking.in.spring.ioc.overview.domain.User;

import java.util.Map;

/**
 * 依赖查找
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {


        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");

        lookupInRealTime(beanFactory);

        lookupInLazy(beanFactory);


        // 按类查询
        lookupByType(beanFactory);

        // 按类型查找集合类型
        lookupByCollectionType(beanFactory);


        lookupByAnnotation(beanFactory);
    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            Map<String, User> users = ((Map) ((ListableBeanFactory) beanFactory).getBeansWithAnnotation(Super.class));
            System.out.println("通过注解查找到所有的 User 集合对象：" + users);
        }
    }

    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("通过类型查找到所有的 User 集合对象：" + users);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("通过类型实时查找：" + user);
    }


    private static void lookupInRealTime(BeanFactory beanFactory) {

        User user = (User) beanFactory.getBean("user");
        System.out.println("通过名称实时查找：" + user);
    }


    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找：" + user);

    }
}
