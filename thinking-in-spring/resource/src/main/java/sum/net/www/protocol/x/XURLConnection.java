package sum.net.www.protocol.x;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import sun.net.www.URLConnection;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


/**
 * X {@link URLConnection} 实现
 */
public class XURLConnection extends URLConnection {


    private final ClassPathResource resource;

    public XURLConnection(URL url) {
        super(url);
        this.resource = new ClassPathResource(url.getPath());
    }

    @Override
    public void connect() throws IOException {

    }

    @Override
    public InputStream getInputStream() throws IOException {
        return resource.getInputStream();
    }
}
