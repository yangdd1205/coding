package thinking.in.spring.bean.definition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Bean 垃圾回收实例
 */
public class BeanGarbageCollectionDemo {


    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class 配置类
        applicationContext.register(BeanInitializationDemo.class);

        applicationContext.refresh();

        applicationContext.close();

        System.gc();
    }
}
