package thinking.in.spring.ioc.overview.repository;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import thinking.in.spring.ioc.overview.domain.User;

import java.util.Collection;

/**
 * 用户信息仓库
 */
public class UserRepository {

    /**
     * 定义 Bean
     */
    private Collection<User> users;


    /**
     * 内建非 Bean 对象，依赖
     */
    private BeanFactory beanFactory;



    private ObjectFactory<ApplicationContext> objectFactory;


    public ObjectFactory<ApplicationContext> getObjectFactory() {
        return objectFactory;
    }

    public void setObjectFactory(ObjectFactory<ApplicationContext> objectFactory) {
        this.objectFactory = objectFactory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
