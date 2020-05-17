package thinking.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import thinking.in.spring.ioc.overview.domain.User;

/**
 * Bean 实例化生命周期
 */
public class BeanInstantiationLifeCycleDemo {

    public static void main(String[] args) {


        executeBeanFactory();


        System.out.println("-------------------");
        executeApplicationContext();

    }


    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //添加 BeanPostProcessor 实现 MyInstantiationAwareBeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());


        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);


        String[] locations = {"classpath:/META-INF/dependency-lookup-context.xml", "classpath:/META-INF/bean-constructor-dependency-injection.xml"};

        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(locations);
        System.out.println("已加载 BeanDefinition 数量：" + beanNumbers);


        User user = beanFactory.getBean("user", User.class);

        System.out.println(user);

        User superUser = beanFactory.getBean("superUser", User.class);

        System.out.println(superUser);


        /**
         * 构造器注入按类型注入，resolveDependency
         */
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
    }


    private static void executeApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();



        // 方法二：将 MyInstantiationAwareBeanPostProcessor 作为 Bean 注册
        String[] locations = {"classpath:/META-INF/dependency-lookup-context.xml", "classpath:/META-INF/bean-constructor-dependency-injection.xml"};


        applicationContext.setConfigLocations(locations);

        applicationContext.refresh();


        User user = applicationContext.getBean("user", User.class);

        System.out.println(user);

        User superUser = applicationContext.getBean("superUser", User.class);

        System.out.println(superUser);



        UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);

        applicationContext.close();
    }
}

