package thinking.in.spring.environment;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@link org.springframework.test.context.TestPropertySource} 测试示例
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestPropertySourceTest.class)
@TestPropertySource(locations = "classpath:/META-INF/test.properties", properties = "user.name=杨")
public class TestPropertySourceTest {

    @Value("${user.name}")
    private String userName;

    @Autowired
    private ConfigurableEnvironment environment;


    @Test
    public void testUserName() {
        Assert.assertEquals("杨", userName);

        for (PropertySource<?> ps : environment.getPropertySources()) {
            System.out.printf("PropertySource(name=%s) 'user.name' 属性：%s\n", ps.getName(), ps.getProperty("user.name"));
        }
    }
}
