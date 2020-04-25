package thinking.in.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    // 基于 @PostConstruct 注解
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct UserFactory 初始化中...");
    }


    public void initUserFactory() {
        System.out.println("自定义初始化方法 UserFactory 初始化中...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet：UserFactory 初始化中..");
    }


    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy 销毁中");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy 销毁中");
    }


    public void doDestroy() {
        System.out.println("定义销毁方法，销毁中");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("当前 DefaultUserFactory 对象正在被回收..");
    }
}
