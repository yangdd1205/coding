package thinking.in.spring.conversion;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.stereotype.Component;
import thinking.in.spring.ioc.overview.domain.User;

/**
 * 自定义 {@link PropertyEditorRegistrar} 实现
 */
//@Component
public class CustomizedPropertyEditorRegistrar implements PropertyEditorRegistrar{


    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {

        registry.registerCustomEditor(User.class,"context", new StringToPropertiesPropertyEditor());
    }
}
