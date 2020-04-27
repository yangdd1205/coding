package thinking.in.spring.dependency.lookup;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import thinking.in.spring.ioc.overview.domain.User;

public class TypeSafetyDependencyLookupDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 建当前类作为配置 Class
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);

        // 启动上下文容器
        applicationContext.refresh();


        displayBeanFactoryGetBean(applicationContext);
        displayBeanFactoryGetObject(applicationContext);
        displayObjectProviderIfAvailable(applicationContext);
        displayListableBeanFactoryGetBeansOfType(applicationContext);
        displayObjectProviderStreamOps(applicationContext);
        applicationContext.close();
    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStreamOps", () -> userObjectProvider.forEach(System.out::print));
    }

    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory beanFactory) {

        printBeansException("displayListableBeanFactory", () -> beanFactory.getBeansOfType(User.class));
    }

    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderIfAvailable", () -> userObjectProvider.getIfAvailable());
    }

    private static void displayBeanFactoryGetObject(BeanFactory beanFactory) {
        ObjectFactory<User> userObjectFactory = beanFactory.getBeanProvider(User.class);


        printBeansException("displayBeanFactoryGetObject", () -> userObjectFactory.getObject());

    }


    public static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
    }


    private static void printBeansException(String source, Runnable runnable) {
        try {
            System.err.println("================================");
            System.err.println("Source from :" + source);
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
