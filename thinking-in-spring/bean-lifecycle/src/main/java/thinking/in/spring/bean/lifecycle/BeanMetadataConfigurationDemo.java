package thinking.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import thinking.in.spring.ioc.overview.domain.User;

/**
 * Bean 元信息配置示例
 */
public class BeanMetadataConfigurationDemo {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 实例化基于 Properties 资源，BeanDefinitionReader
        Resource resource = new ClassPathResource("/META-INF/user.properties");
        //指定编码 UTF-8
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println("已加载的 BeanDefinition 数量：" + beanNumbers);


        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        // 基于 XML 资源 可以看 BeanFactoryAsIoCContainerDemo




    }
}
