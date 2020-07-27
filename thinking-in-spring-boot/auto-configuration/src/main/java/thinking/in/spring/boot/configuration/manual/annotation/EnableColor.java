package thinking.in.spring.boot.configuration.manual.annotation;

import org.springframework.context.annotation.Import;
import thinking.in.spring.boot.configuration.manual.annotation.color.Red;
import thinking.in.spring.boot.configuration.manual.config.ColorImportBeanDefinitionRegistrar;
import thinking.in.spring.boot.configuration.manual.config.ColorImportSelector;
import thinking.in.spring.boot.configuration.manual.config.ColorRegistrarConfiguration;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
// import 的四种传入类型。
// 1. 普通类 Red.class
// 2. 配置类 ColorRegistrarConfiguration.class
// 3. ImportSelector 接口 ColorImportSelector.class
// 4. ImportBeanDefinitionRegistrar 接口 ColorImportBeanDefinitionRegistrar.class
@Import({Red.class, ColorRegistrarConfiguration.class, ColorImportSelector.class, ColorImportBeanDefinitionRegistrar.class})
public @interface EnableColor {
}
