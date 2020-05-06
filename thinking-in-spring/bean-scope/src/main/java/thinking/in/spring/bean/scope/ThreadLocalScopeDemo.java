package thinking.in.spring.bean.scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import thinking.in.spring.ioc.overview.domain.User;

/**
 * 自定义 Scope {@link ThreadLocalScope} 示例
 */
public class ThreadLocalScopeDemo {


    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    private User user() {
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
        applicationContext.register(ThreadLocalScopeDemo.class);


        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            // 注册自定义 Scope
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });

        applicationContext.refresh();

        scopedBeansByLookup(applicationContext);

        applicationContext.close();

    }

    private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                User singletonUser = applicationContext.getBean("user", User.class);
                System.out.printf("[Thread id: %d] user = %s\n", Thread.currentThread().getId(), singletonUser);

            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
