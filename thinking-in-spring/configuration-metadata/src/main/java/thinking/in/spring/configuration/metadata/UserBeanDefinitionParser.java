package thinking.in.spring.configuration.metadata;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;
import thinking.in.spring.ioc.overview.domain.User;

/**
 * "user" 元素的 {@link org.springframework.beans.factory.xml.BeanDefinitionParser} 实现
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        setPropertyValue("id", element, builder);
        setPropertyValue("name", element, builder);
        setPropertyValue("city", element, builder);
    }


    private void setPropertyValue(String attributeName, Element element, BeanDefinitionBuilder builder) {

        String attributeValue = element.getAttribute(attributeName);

        if (StringUtils.hasText(attributeValue)) {
            builder.addPropertyValue(attributeName, attributeValue);
        }
    }
}
