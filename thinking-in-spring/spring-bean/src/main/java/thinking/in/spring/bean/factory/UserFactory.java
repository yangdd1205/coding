package thinking.in.spring.bean.factory;

import thinking.in.spring.ioc.overview.domain.User;

public interface UserFactory {


    default User createUser() {
        return User.createUser();
    }
}
