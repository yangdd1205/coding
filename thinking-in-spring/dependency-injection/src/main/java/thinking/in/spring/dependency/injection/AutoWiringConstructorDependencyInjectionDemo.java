package thinking.in.spring.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Constructor AutoWiring 依赖注入
 */
public class AutoWiringConstructorDependencyInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);


        String xmlResourcePath = "classpath:/META-INF/autowiring-dependency-constructor-injection.xml";

        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);


        UserHolder userHolder = beanFactory.getBean(UserHolder.class);

        System.out.println(userHolder);
    }
}
