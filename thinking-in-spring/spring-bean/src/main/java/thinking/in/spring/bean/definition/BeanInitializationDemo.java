package thinking.in.spring.bean.definition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import thinking.in.spring.bean.factory.DefaultUserFactory;
import thinking.in.spring.bean.factory.UserFactory;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Bean 初始化 Demo
 */
public class BeanInitializationDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class 配置类
        applicationContext.register(BeanInitializationDemo.class);
        applicationContext.refresh();
        // 非延迟初始化在 Spring 应用上下文后，被初始化
        System.out.println("Spring 应用上下文已启动");
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);

        System.out.println(userFactory);
        System.out.println("Spring 应用上下文准备关闭");
        applicationContext.close();
        System.out.println("Spring 应用上下文已关闭");
    }

//    @Lazy
    @Bean(initMethod = "initUserFactory",destroyMethod = "doDestroy")
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
