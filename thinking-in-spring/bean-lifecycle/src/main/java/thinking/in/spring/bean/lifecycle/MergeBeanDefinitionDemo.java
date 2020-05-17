package thinking.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import thinking.in.spring.ioc.overview.domain.SuperUser;
import thinking.in.spring.ioc.overview.domain.User;

/**
 * BeanDefinition 合并示例
 */
public class MergeBeanDefinitionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);


        String location = "classpath:/META-INF/dependency-lookup-context.xml";

        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(location);
        System.out.println("已加载 BeanDefinition 数量：" + beanNumbers);


        User user = beanFactory.getBean("user", User.class);

        System.out.println(user);

        User superUser = beanFactory.getBean("superUser", User.class);

        System.out.println(superUser);
    }


}
