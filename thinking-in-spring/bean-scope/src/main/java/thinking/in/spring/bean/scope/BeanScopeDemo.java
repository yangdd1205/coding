package thinking.in.spring.bean.scope;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import thinking.in.spring.ioc.overview.domain.User;

import java.util.Map;

public class BeanScopeDemo implements DisposableBean {


    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;
    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser1;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;
    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;

    @Autowired
    private Map<String,User> users;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    @Bean
    // 默认 scope 就是 Singleton
    private static User singletonUser() {
        return createUser();
    }

    @Bean
    @Scope(ConfigurableListableBeanFactory.SCOPE_PROTOTYPE)
    private static User prototypeUser() {
        return createUser();
    }

    private static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class 配置类
        applicationContext.register(BeanScopeDemo.class);


        applicationContext.refresh();

        // 结论一：
        // Singleton Bean 无论依赖查找还是依赖注入，均为同一个对象
        // Prototype Bean 无论依赖查找还是依赖注入，均为新生成对象

        // 结论二：
        // 如果依赖注入集合类型的对象，Singleton Bean 和 Prototype Bean 均会存在一个
        // Prototype Bean 有别于其他地方注入的 Prototype Bean

        // 结论三：
        // Singleton Bean 和 Prototype Bean 均会执行初始化方法回调
        // 仅 Singleton Bean 会执行销毁方法回调
        scopedBeansByLookup(applicationContext);


        scopedBeansByInjection(applicationContext);

        applicationContext.close();

    }

    private static void scopedBeansByInjection(AnnotationConfigApplicationContext applicationContext) {
        BeanScopeDemo demo = applicationContext.getBean(BeanScopeDemo.class);

        System.out.println("BeanScopeDemo.singletonUser = " + demo.singletonUser);
        System.out.println("BeanScopeDemo.singletonUser1 = " + demo.singletonUser1);
        System.out.println("BeanScopeDemo.prototypeUser = " + demo.prototypeUser);
        System.out.println("BeanScopeDemo.prototypeUser1 = " + demo.prototypeUser1);
        System.out.println("BeanScopeDemo.prototypeUser2 = " + demo.prototypeUser2);
        System.out.println("BeanScopeDemo.users = " + demo.users);

    }


    private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            User singletonUser = applicationContext.getBean("singletonUser", User.class);
            System.out.println("singletonUser = " + singletonUser);


            User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
            System.out.println("prototypeUser = " + prototypeUser);

        }
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("BeanScopeDemo 开始销毁中......");
        this.prototypeUser.destroy();
        this.prototypeUser1.destroy();
        this.prototypeUser2.destroy();

        this.users.forEach((k,v)->{
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(k);
            if (beanDefinition.isPrototype()) {
                v.destroy();
            }
        });
        System.out.println("BeanScopeDemo 销毁完成");

    }
}
