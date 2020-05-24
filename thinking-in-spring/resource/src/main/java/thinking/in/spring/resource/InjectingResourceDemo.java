package thinking.in.spring.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import thinking.in.spring.resource.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * 注入 {@link org.springframework.core.io.Resource} 示例
 *
 * @see org.springframework.core.io.Resource
 * @see org.springframework.beans.factory.annotation.Value
 * @see org.springframework.context.annotation.AnnotationConfigApplicationContext
 */
public class InjectingResourceDemo {

    @Value("classpath:/META-INF/default.properties")
    private Resource defaultPropertiesResource;

    @Value("${user.dir}")
    private String currentProjectRootPath;

    @Value("classpath*:/META-INF/*.properties")
    private Resource[] propertiesResource;

    @PostConstruct
    public void init() {
        System.out.println(ResourceUtils.getContent(defaultPropertiesResource));
        System.out.println("***********");
        Stream.of(propertiesResource).map(ResourceUtils::getContent).forEach(System.out::println);
        System.out.println("***********");
        System.out.println(currentProjectRootPath);

    }


    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class 配置类
        applicationContext.register(InjectingResourceDemo.class);

        applicationContext.refresh();

        applicationContext.close();

    }
}
