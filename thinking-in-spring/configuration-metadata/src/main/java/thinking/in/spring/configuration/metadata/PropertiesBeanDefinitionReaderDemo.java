package thinking.in.spring.configuration.metadata;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import thinking.in.spring.ioc.overview.domain.User;


public class PropertiesBeanDefinitionReaderDemo {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();


        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);


        Resource resource = new ClassPathResource("/META-INF/user-bean-definitions.properties");
        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");
        int beanDefinitionsCount = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.printf("已加载 %d 个 BeanDefinition \n",beanDefinitionsCount);


        User user = beanFactory.getBean(User.class);
        System.out.println(user);
    }
}
