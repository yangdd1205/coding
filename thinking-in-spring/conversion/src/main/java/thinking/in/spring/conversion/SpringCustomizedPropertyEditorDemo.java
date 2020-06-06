package thinking.in.spring.conversion;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import thinking.in.spring.ioc.overview.domain.User;

/**
 * Spring 自定义 {@link org.springframework.beans.propertyeditors.PropertiesEditor} 示例
 */
public class SpringCustomizedPropertyEditorDemo {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/property-editors-context.xml");

        // AbstractApplicationContext -> "conversionService" ConversionService Bean ->
        // ConfigurableBeanFactory#setConversionService(ConversionService) ->
        // AbstractAutowireCapableBeanFactory.instantiateBean
        // AbstractBeanFactory#getConversionService- >
        // BeanDefinition -> BeanWrapper -> 属性转换（数据来源：PropertyValues） ->
        // setPropertyValues（PropertyValues）-> TypeConverter#convertIfNecessnary ->
        // TypeConverterDelegate#convertIfNecessnary -> PropertyEditor or ConversionService

        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);
        applicationContext.close();
    }
}
