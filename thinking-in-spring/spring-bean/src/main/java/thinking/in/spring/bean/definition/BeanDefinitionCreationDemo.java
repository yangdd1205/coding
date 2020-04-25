package thinking.in.spring.bean.definition;


import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import thinking.in.spring.ioc.overview.domain.User;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition} 构建实例
 */
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {

        // 1. 通过 BeanDefinitionBuilder 构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        //  通过属性设置
        beanDefinitionBuilder.addPropertyValue("age", 1);
        beanDefinitionBuilder.addPropertyValue("name", "杨");

        // 获取 BeanDefinition 实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();


        // BeanDefinition 并非 Bean 终态，可以自定义修改


        // 2. 通过 AbstractBeanDefinition 以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);

        // 通过 MutablePropertyValues 批量设置属性
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        // 第一种方式
//        propertyValues.addPropertyValue("age", 1);
//        propertyValues.addPropertyValue("name", "杨");
        // 第二种方式
        propertyValues.add("age", 1)
                .add("name", "杨");
        genericBeanDefinition.setPropertyValues(propertyValues);


    }
}
