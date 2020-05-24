package thinking.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * 带有字符编码的 {@link FileSystemResourceLoader}
 */
public class EncodedFileSystemResourceLoaderDemo {

    public static void main(String[] args) throws IOException {


        String currentJavaFilePath = "/" + System.getProperty("user.dir") + "/thinking-in-spring/resource/src/main/java/thinking/in/spring/resource/EncodedFileSystemResourceLoaderDemo.java";

        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();

        Resource resource = resourceLoader.getResource(currentJavaFilePath);

        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

        // 字符输入流
        try (Reader reader = encodedResource.getReader()) {
            System.out.println(IOUtils.toString(reader));
        }

    }
}
