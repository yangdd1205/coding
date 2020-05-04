package thinking.in.spring.dependency.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import thinking.in.spring.dependency.injection.annotation.InjectedUser;
import thinking.in.spring.dependency.injection.annotation.MyAutoWired;
import thinking.in.spring.ioc.overview.domain.User;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.*;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

/**
 * 注解驱动的依赖注入处理过程
 */
public class AnnotationDependencyInjectionResolutionDemo {

    @Autowired
    @Lazy
    private User lazyUser;


    @Autowired          // 依赖查找（处理）
    private User user;  // DependencyDescriptor ->
    // 必须（require=true
    // 实时注入（eager=true）
    // 通过类型（User.class）
    // 字段名称（"user"）
    // 是否是首要的（primary=true）


    @Autowired
    private Map<String, User> users;// 这里返回的 Map 是 LinkedHashMap 里面的元素信息是跟定义的顺序一样的。

    @MyAutoWired
    private Optional<User> optionalUser;

    @Inject
    private User injectedUser;

    @InjectedUser
    private User myInjectedUser;

//    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)//使用自定义的类替换掉默认的 AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME
//    public static /* 加 static 的目的是脱离当前类的控制，更早的被注册到容器中  */ AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        //beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class); // 只使用自定义注解
//        // 兼容 新老注解
//        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(Arrays.asList(Autowired.class,Inject.class,InjectedUser.class));
//        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
//        return beanPostProcessor;
//    }

    /**
     * 新老两个 AutowiredAnnotationBeanPostProcessor 同时存在。
     * @return
     */
    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class); // 只使用自定义注解
        return beanPostProcessor;
    }

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class 配置类
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);


        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);


        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);


        applicationContext.refresh();


        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

        System.out.println("demo.user = " + demo.user);

        System.out.println("demo.injectedUser = " + demo.injectedUser);


        System.out.println("demo.optionalUser = " + demo.optionalUser);

        System.out.println("demo.myInjectedUser = " + demo.myInjectedUser);

        applicationContext.close();

    }

}


