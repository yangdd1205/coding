package thinking.in.spring.i18n;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Spring Boot 场景下自定义  {@link org.springframework.context.MessageSource} Bean
 *
 * @see org.springframework.context.MessageSource
 * @see org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration
 * @see org.springframework.context.support.ReloadableResourceBundleMessageSource
 */
public class CustomizedMessageSourceBeanDemo {

    /**
     * 在 Spring Boot 场景中，Primary Configuration Sources(Classes) 高于 *AutoConfiguration
     *
     * @return
     */
    @Bean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)
    public MessageSource messageSource() {
        return new ReloadableResourceBundleMessageSource();
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(CustomizedMessageSourceBeanDemo.class).web(WebApplicationType.NONE).run(args);
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        if (beanFactory.containsLocalBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME)) {
            beanFactory.getBeanDefinition(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME);
            MessageSource messageSource = applicationContext.getBean(AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME, MessageSource.class);
            System.out.println(messageSource);
        }
        applicationContext.close();

    }

}
