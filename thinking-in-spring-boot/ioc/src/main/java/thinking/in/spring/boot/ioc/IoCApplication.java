package thinking.in.spring.boot.ioc;


import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


public class IoCApplication {

    public static void main(String[] args) {
//        new SpringApplicationBuilder(IoCApplication.class)
//                .web(WebApplicationType.SERVLET)
//                .bannerMode(Banner.Mode.OFF)
//                .run(args);

        // 或者创建后设置
        SpringApplication springApplication = new SpringApplication(SpringBootConfig.class);
        springApplication.setWebApplicationType(WebApplicationType.SERVLET);
        springApplication.setBannerMode(Banner.Mode.OFF);
        // 添加初始化器
        // 方法一：直接在代码里面添加。优先级3
        springApplication.addInitializers(new ApplicationContextInitializerDemo());
        // 方法二：通过配置文件设置。application.properties。优先级1
        // 方法三：通过 spring.factories 文件声明 优先级2
        springApplication.run(args);
    }

    @SpringBootApplication
    static class SpringBootConfig{

    }
}
