package thinking.in.spring.boot.configuration.manual.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import thinking.in.spring.boot.configuration.manual.annotation.color.Yellow;

@Configuration
public class ColorRegistrarConfiguration {
    @Bean
    public Yellow yellow() {
        return new Yellow();
    }
}
