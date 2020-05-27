package thinking.in.spring.i18n;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * 动态（更新）资源{@link org.springframework.context.MessageSource} 实现
 * <p>
 * 实现步骤：
 * 1.定义资源（properties 文件）
 * 2.初始化 Properties 对象
 * 3.实现 AbstractMessageSource#resolveCode
 * 4.监听资源文件 （Java NIO 2 WatchService）
 * 5.使用线程池处理文件变化
 * 6.重新装载 properties 对象
 */
public class DynamicResourceMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

    private static final String resourceFileName = "msg.properties";

    private static final String resourcePath = "/META-INF/msg.properties";

    private static final String ENCODING = "UTF-8";

    private Properties messageProperties;

    private final Resource messagePropertiesResource;

    private ResourceLoader resourceLoader;


    private final ExecutorService executorService;


    public DynamicResourceMessageSource() {
        this.messagePropertiesResource = getMessagePropertiesResource();
        messageProperties = loadMessageProperties();
        this.executorService = Executors.newSingleThreadExecutor();
        // 监听资源文件 （Java NIO 2 WatchService）
        onMessagePropertiesChanged();
    }

    private void onMessagePropertiesChanged() {
        if (this.getMessagePropertiesResource().isFile()) {
            try {
                File messagePropertiesFile = this.getMessagePropertiesResource().getFile();
                Path messagePropertiesFilePath = messagePropertiesFile.toPath();
                // 获取文件系统
                FileSystem fileSystem = FileSystems.getDefault();
                //新建 WatchService
                WatchService watchService = fileSystem.newWatchService();
                // 获取资源文件所在的目录
                Path dirPath = messagePropertiesFilePath.getParent();
                // 注册 WatchService 到 dirPath，并且关心修改事件
                dirPath.register(watchService, ENTRY_MODIFY);


                processMessagePropertiesChanged(watchService);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
    }

    private void processMessagePropertiesChanged(WatchService watchService) {
        executorService.submit(() -> {
            while (true) {
                WatchKey watchKey = watchService.take();
                try {
                    // 是否有效
                    if (watchKey.isValid()) {
                        for (WatchEvent<?> event : watchKey.pollEvents()) {
                            Watchable watchable = watchKey.watchable();
                            // 目录路径（监听的目录）
                            Path dirPath = (Path) watchable;
                            Path fileRelativePath = (Path) event.context();
                            if (resourceFileName.equals(fileRelativePath.getFileName().toString())) {
                                Path filePath = dirPath.resolve(fileRelativePath);
                                System.out.println("修改的文件：" + filePath);
                                File file = filePath.toFile();
                                Properties properties = loadMessageProperties(new FileReader(file));
                                synchronized (messageProperties) {
                                    messageProperties.clear();
                                    messageProperties.putAll(properties);
                                }
                            }
                        }
                    }

                } finally {
                    if (watchKey != null) {
                        watchKey.reset();//重置 watchKey
                    }
                }

            }
        });
    }

    private Properties loadMessageProperties() {
        EncodedResource encodedResource = new EncodedResource(this.getMessagePropertiesResource(), ENCODING);
        try {
            return loadMessageProperties(encodedResource.getReader());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Properties loadMessageProperties(Reader reader) {
        Properties properties = new Properties();
        try {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return properties;
    }


    private Resource getMessagePropertiesResource() {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(resourcePath);
        return resource;
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String messageFormatPattern = messageProperties.getProperty(code);
        if (StringUtils.hasText(messageFormatPattern)) {
            return new MessageFormat(messageFormatPattern, locale);
        }
        return null;
    }

    private ResourceLoader getResourceLoader() {
        return this.resourceLoader != null ? this.resourceLoader : new DefaultResourceLoader();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public static void main(String[] args) throws InterruptedException {
        DynamicResourceMessageSource messageSource = new DynamicResourceMessageSource();
        for (int i = 0; i < 1000; i++) {
            String message = messageSource.getMessage("name", new Object[]{}, Locale.getDefault());
            System.out.println(message);
            Thread.sleep(1000L);
        }

    }
}
