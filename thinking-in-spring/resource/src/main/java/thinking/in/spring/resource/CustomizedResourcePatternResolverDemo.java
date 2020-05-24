package thinking.in.spring.resource;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.PathMatcher;
import thinking.in.spring.resource.util.ResourceUtils;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 自定义 {@link ResourcePatternResolver} 示例
 *
 * @see ResourcePatternResolver
 * @see PathMatchingResourcePatternResolver
 * @see PathMatcher
 */
public class CustomizedResourcePatternResolverDemo {

    public static void main(String[] args) throws IOException {
        // 读取当前 package 下面的 Java 文件
        // *.java

        String currentPackagePath = "/" + System.getProperty("user.dir") + "/thinking-in-spring/resource/src/main/java/thinking/in/spring/resource/";


        String locationPattern = currentPackagePath + "*.java";


        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());

        resourcePatternResolver.setPathMatcher(new JavaFilePathMatcher());

        Resource[] resources = resourcePatternResolver.getResources(locationPattern);


        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);

    }


    static class JavaFilePathMatcher implements PathMatcher{

        @Override
        public boolean isPattern(String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean match(String pattern, String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean matchStart(String pattern, String path) {
            return false;
        }

        @Override
        public String extractPathWithinPattern(String pattern, String path) {
            return null;
        }

        @Override
        public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
            return null;
        }

        @Override
        public Comparator<String> getPatternComparator(String path) {
            return null;
        }

        @Override
        public String combine(String pattern1, String pattern2) {
            return null;
        }
    }
}
