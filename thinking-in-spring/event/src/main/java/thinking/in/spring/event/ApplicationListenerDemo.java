package thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.*;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * {@link ApplicationListener}
 */
@EnableAsync//启动异步
public class ApplicationListenerDemo implements ApplicationEventPublisherAware {

    public static void main(String[] args) {
        //GenericApplicationContext context = new GenericApplicationContext();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ApplicationListenerDemo.class);

        //方法一： 基于 Spring 接口：向 Spring 应用上下文注册事件
        // a 方法：基于 ConfigurableApplicationListener API 实现
        context.addApplicationListener(event -> println("ApplicationListener - 接收到 Spring 事件：" + event));


        // b方法：基于 ApplicationListener 注册为 Spring Bean
        context.register(MyApplicationListener.class);

        // 方法二：基于 Spring 注解：@org.springframework.context.event.EventListener

        context.refresh();//ContextRefreshedEvent


        context.start();//ContextStartedEvent

        context.stop();//ContextStoppedEvent
        context.close();//ContextClosedEvent
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new ApplicationEvent("Hello,World") {
        });

        // 发送 PayloadApplicationEvent
        applicationEventPublisher.publishEvent("Hello,World");
    }

    static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            println("MyApplicationListener - 接收到 Spring 时间：" + event);
        }
    }

    @Async
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        println("@EventListener - 接收到 Spring ContextRefreshedEvent");
    }

    @Order(2)
    @EventListener
    public void onApplicationEvent(ContextStartedEvent event) {
        println("@EventListener(onApplicationEvent) - 接收到 Spring ContextRefreshedEvent");
    }

    @Order(1)
    @EventListener
    public void onApplicationEvent1(ContextStartedEvent event) {
        println("@EventListener(onApplicationEvent1) - 接收到 Spring ContextRefreshedEvent");
    }

    @EventListener
    public void onApplicationEvent(ContextStoppedEvent event) {
        println("@EventListener - 接收到 Spring ContextStoppedEvent");
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent event) {
        println("@EventListener - 接收到 Spring ContextClosedEvent");
    }


    private static void println(Object printable) {
        System.out.printf("[线程：%s] ：%s\n", Thread.currentThread().getName(), printable);
    }
}
