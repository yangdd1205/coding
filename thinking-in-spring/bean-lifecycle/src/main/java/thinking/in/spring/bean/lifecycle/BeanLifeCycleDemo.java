package thinking.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import thinking.in.spring.ioc.overview.domain.User;

public class BeanLifeCycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());

        // 添加 CommonAnnotationBeanPostProcessor 解决 @PostConstruct
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);


        String[] locations = {"classpath:/META-INF/dependency-lookup-context.xml", "classpath:/META-INF/bean-constructor-dependency-injection.xml"};

        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(locations);
        System.out.println("已加载 BeanDefinition 数量：" + beanNumbers);

        // 显示调用 preInstantiateSingletons
        //  SmartInitializingSingleton 通常 Spring ApplicationContext 场景使用
        // preInstantiateSingletons 将已注册的  BeanDefinition 提前初始化成 Spring Bean
        beanFactory.preInstantiateSingletons();


        User user = beanFactory.getBean("user", User.class);

        System.out.println(user);

        User superUser = beanFactory.getBean("superUser", User.class);

        System.out.println(superUser);


        /**
         * 构造器注入按类型注入，resolveDependency
         */
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);

        System.out.println(userHolder);

        beanFactory.destroyBean("userHolder",userHolder);

        System.out.println(userHolder);
    }
}
