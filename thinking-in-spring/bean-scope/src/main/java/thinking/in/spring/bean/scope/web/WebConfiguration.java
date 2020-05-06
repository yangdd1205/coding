package thinking.in.spring.bean.scope.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import thinking.in.spring.ioc.overview.domain.User;

@Configuration
@EnableWebMvc
public class WebConfiguration {

    @Bean
//    @RequestScope
//    @SessionScope
    @ApplicationScope // ScopedProxyUtils.TARGET_NAME_PREFIX scope 对象 Bean Name 前缀
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("杨");
        return user;
    }
}
