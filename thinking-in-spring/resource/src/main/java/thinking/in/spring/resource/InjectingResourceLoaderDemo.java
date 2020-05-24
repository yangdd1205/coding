package thinking.in.spring.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import thinking.in.spring.resource.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * 注入 {@link Resource} 示例
 *
 * @see ResourceLoader
 * @see Resource
 * @see Value
 * @see AnnotationConfigApplicationContext
 */
public class InjectingResourceLoaderDemo implements ResourceLoaderAware {

    private ResourceLoader resourceLoader; // 方法一

    @Autowired
    private ResourceLoader autowiredResourceLoader; // 方法二

    @Autowired
    private AbstractApplicationContext applicationContext; // 方法三


    @PostConstruct
    public void init() {

        System.out.println("resourceLoader == autowiredResourceLoader : " + (resourceLoader == autowiredResourceLoader));
        System.out.println("resourceLoader == applicationContext : " + (resourceLoader == applicationContext));
    }


    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class 配置类
        applicationContext.register(InjectingResourceLoaderDemo.class);

        applicationContext.refresh();

        applicationContext.close();

    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
