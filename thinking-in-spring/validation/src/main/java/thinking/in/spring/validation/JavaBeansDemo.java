package thinking.in.spring.validation;

import thinking.in.spring.ioc.overview.domain.User;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

public class JavaBeansDemo {



    public static void main(String[] args) throws IntrospectionException {
        // stopClass，排除（截止）类
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class,Object.class);

        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(propertyDescriptor -> {
                    propertyDescriptor.getReadMethod();//Getter 方法
                    propertyDescriptor.getWriteMethod();// Setter 方法
                    System.out.println(propertyDescriptor);
                });


        // 输出 User 定义方法
        Stream.of(beanInfo.getMethodDescriptors()).forEach(System.out::println);
    }
}
