package thinking.in.spring.resource;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * X Handler 测试示例
 */
public class HandlerTest {

    public static void main(String[] args) throws IOException {
        URL url = new URL("x:///META-INF/default.properties"); //类似于 classpath

        InputStream inputStream = url.openStream();

        System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")));

    }
}
