package thinking.in.spring.bean.factory;

import org.springframework.beans.factory.FactoryBean;
import thinking.in.spring.ioc.overview.domain.User;

public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
