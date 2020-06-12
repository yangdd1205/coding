package thinking.in.spring.generic;

import org.springframework.core.GenericCollectionTypeResolver;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link org.springframework.core.GenericCollectionTypeResolver} 示例
 */
public class GenericCollectionTypeResolveDemo {

    private static StringList stringList;

    private static List<String> strings;

    public static void main(String[] args) throws Exception{
        // StringList extends ArrayList<String> 具体化
        // getCollectionType 返回具体化泛型参数类型集合的成员类型 = String
        System.out.println(GenericCollectionTypeResolver.getCollectionType(StringList.class));
        System.out.println(GenericCollectionTypeResolver.getCollectionType(ArrayList.class));

        // 获取字段
        Field field = GenericCollectionTypeResolveDemo.class.getDeclaredField("stringList");
        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(field));

         field = GenericCollectionTypeResolveDemo.class.getDeclaredField("strings");
        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(field));
    }
}
