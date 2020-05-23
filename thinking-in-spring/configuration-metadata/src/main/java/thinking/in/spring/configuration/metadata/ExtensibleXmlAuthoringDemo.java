package thinking.in.spring.configuration.metadata;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import thinking.in.spring.ioc.overview.domain.User;

/**
 * Spring XML 元素扩展示例
 */
public class ExtensibleXmlAuthoringDemo {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();


        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);


        int beanDefinitionsCount = beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/user-context.xml");
        System.out.printf("已加载 %d 个 BeanDefinition \n", beanDefinitionsCount);

        User user = beanFactory.getBean(User.class);
        System.out.println(user);


    }
}
