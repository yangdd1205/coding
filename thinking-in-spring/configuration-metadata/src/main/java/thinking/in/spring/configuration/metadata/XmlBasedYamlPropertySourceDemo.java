package thinking.in.spring.configuration.metadata;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import thinking.in.spring.ioc.overview.domain.User;

import java.util.Map;

public class XmlBasedYamlPropertySourceDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();


        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);


        int beanDefinitionsCount = beanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/yaml-property-source-context.xml");
        System.out.printf("已加载 %d 个 BeanDefinition \n", beanDefinitionsCount);

        Map<String, Object> yamlMap = beanFactory.getBean("yamlMap", Map.class);
        System.out.println(yamlMap);
    }
}
