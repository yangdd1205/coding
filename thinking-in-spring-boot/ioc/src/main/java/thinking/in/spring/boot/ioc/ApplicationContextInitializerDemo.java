package thinking.in.spring.boot.ioc;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 在 IoC 容器初始化之前回调。也就是在 {@linkplain ConfigurableApplicationContext#refresh()} 之前
 */
public class ApplicationContextInitializerDemo implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("ApplicationContextInitializerDemo#initialize run...");
    }
}
