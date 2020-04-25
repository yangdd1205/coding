package thinking.in.spring.bean.definition;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import thinking.in.spring.ioc.overview.domain.User;

public class BeanAliasDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");


        User yang = beanFactory.getBean("yang", User.class);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(yang == user);

    }
}
