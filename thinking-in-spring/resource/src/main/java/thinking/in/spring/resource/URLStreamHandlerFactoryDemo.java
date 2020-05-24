package thinking.in.spring.resource;

import org.springframework.util.StreamUtils;
import sum.net.www.protocol.x.Handler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.nio.charset.Charset;

public class URLStreamHandlerFactoryDemo {


    public static void main(String[] args) throws IOException {

        URL.setURLStreamHandlerFactory(new YURLStreamHandlerFactory());

        URL url = new URL("y:///META-INF/production.properties"); //类似于 classpath

        InputStream inputStream = url.openStream();

        System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")));


    }


    static class YURLStreamHandlerFactory implements URLStreamHandlerFactory {

        @Override
        public URLStreamHandler createURLStreamHandler(String protocol) {
            if ("y".equals(protocol)) {
                return new Handler();
            }
            return null;
        }
    }
}
