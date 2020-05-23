package thinking.in.spring.configuration.metadata;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.util.ObjectUtils;
import thinking.in.spring.ioc.overview.domain.User;

/**
 * Bean 配置元信息示例
 */
public class BeanConfigurationMetadataDemo {

    public static void main(String[] args) {


        // BeanDefinition 的定义（声明）

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name","yang");

        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        // 附加属性（不影响 Bean populate、initialize）
        beanDefinition.setAttribute("name","杨");

        // 当前 BeanDefinition 来自何方（辅助作用）
        beanDefinition.setSource(BeanConfigurationMetadataDemo.class);

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
                    BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
                    if (bd.getSource().equals(BeanConfigurationMetadataDemo.class)) {
                        // 属性（存储）上下文
                        String name = (String) bd.getAttribute("name");
                        User user = (User) bean;
                        user.setName(name);
                    }

                }
                return bean;
            }
        });
        beanFactory.registerBeanDefinition("user",beanDefinition);


        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);


    }
}
