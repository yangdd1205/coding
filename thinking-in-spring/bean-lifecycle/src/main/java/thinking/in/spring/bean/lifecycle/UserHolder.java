package thinking.in.spring.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import thinking.in.spring.ioc.overview.domain.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class UserHolder implements BeanNameAware, BeanFactoryAware,
        BeanClassLoaderAware, EnvironmentAware,
        InitializingBean,SmartInitializingSingleton,DisposableBean {

    private  final User user;



    private Integer number;


    private String description;


    private ClassLoader classLoader;

    private BeanFactory beanFactory;

    private String beanName;


    private Environment environment;




    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public UserHolder(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", beanFactory=" + beanFactory +
                ", beanName='" + beanName + '\'' +
                ", environment=" + environment +
                '}';
    }


    /**
     * 回调顺序：2
     * @param classLoader
     */
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }


    /**
     * 回调顺序：3
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    /**
     * 回调顺序：1
     * @param name
     */
    @Override
    public void setBeanName(String name) {
        this.beanName = name;

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 依赖注解驱动
     */
    @PostConstruct
    public void initPostConstruct(){
        this.setDescription("The user holder V4");
        System.out.println("initPostConstruct()="+this.getDescription());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.setDescription("The user holder V5");
        System.out.println("afterPropertiesSet()="+this.getDescription());
    }


    public void init(){
        this.setDescription("The user holder V6");
        System.out.println("init()="+this.getDescription());
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.setDescription("The user holder V8");
        System.out.println("afterSingletonsInstantiated()="+this.getDescription());
    }

    @PreDestroy
    public void PreDestroy(){
        this.setDescription("The user holder V10");
        System.out.println("PreDestroy()="+this.getDescription());
    }


    @Override
    public void destroy() throws Exception {
        this.setDescription("The user holder V11");
        System.out.println("destroy()="+this.getDescription());
    }


    public void doDestroy(){
        this.setDescription("The user holder V12");
        System.out.println("doDestroy()="+this.getDescription());
    }


    @Override
    protected void finalize() throws Throwable {
        System.out.println("UserHolder is finalize....  ");
    }
}
