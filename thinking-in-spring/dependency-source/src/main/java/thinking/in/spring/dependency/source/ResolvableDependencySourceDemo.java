package thinking.in.spring.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * Resolvable Dependency 作为依赖来源
 */
public class ResolvableDependencySourceDemo {
    @Autowired
    private String value;

    @PostConstruct
    public void init() {
        System.out.println(value);
    }

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();


        // 注册 Configuration Class 配置类
        applicationContext.register(ResolvableDependencySourceDemo.class);

        // 使用
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerResolvableDependency(String.class, "Hello,World");
        });


        applicationContext.refresh();


        /**

         这样写的问题是，@Autowired 是强注入的，在依赖查找时，String.class 还没注入

         AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
         if (beanFactory instanceof ConfigurableListableBeanFactory) {
         ConfigurableListableBeanFactory configurableListableBeanFactory = ConfigurableListableBeanFactory.class.cast(beanFactory);
         // 注入 Resolvable Dependency
         configurableListableBeanFactory.registerResolvableDependency(String.class,"Hello,World");
         }
         **/


        applicationContext.close();

    }
}
