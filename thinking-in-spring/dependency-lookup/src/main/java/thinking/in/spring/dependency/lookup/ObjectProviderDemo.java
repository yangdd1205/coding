package thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import thinking.in.spring.ioc.overview.domain.User;

/**
 * 通过 {@link org.springframework.beans.factory.ObjectProvider} 进行查找
 */
public class ObjectProviderDemo {

    public static void main(String[] args) {

        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 建当前类作为配置 Class
        applicationContext.register(ObjectProviderDemo.class);

        // 启动上下文容器
        applicationContext.refresh();


        lookupByObjectProvider(applicationContext);

        lookupIfAvailable(applicationContext);


        lookupByStreamOps(applicationContext);

        applicationContext.close();
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> stringObjectProvider = applicationContext.getBeanProvider(String.class);
//        Iterable<String> stringIterable = stringObjectProvider;
//        for (String string : stringIterable) {
//            System.out.println(string);
//        }
        stringObjectProvider.stream().forEach(System.out::println);
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        User user = userObjectProvider.getIfAvailable(User::createUser);
        System.out.println("当前 User 对象：" + user);
    }


    @Bean
    @Primary
    private String helloWorld() { // 方法名就是 Bean 名称 = "helloWorld"
        return "Hello,World";
    }


    @Bean
    private String message() {
        return "Message";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }


}
