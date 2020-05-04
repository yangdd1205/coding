package thinking.in.spring.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于 XML 资源的依赖 Setter 方法注入示例
 */
public class XmlDependencySetterInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);


        String xmlResourcePath = "classpath:/META-INF/dependency-setter-injection.xml";

        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);


        UserHolder userHolder = beanFactory.getBean(UserHolder.class);

        System.out.println(userHolder);

    }
}
