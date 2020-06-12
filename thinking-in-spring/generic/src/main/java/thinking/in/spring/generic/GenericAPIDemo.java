package thinking.in.spring.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.stream.Stream;

public class GenericAPIDemo {

    public static void main(String[] args) {
        // 原生类型 primitive types  int long float
        Class intClass = int.class;

        // 数组类型  array types int[],Object[]
        Class objectArrayClass = Object[].class;

        // 原始类型 raw types: java.lang.String
        Class rawClass = String.class;

        // 泛型参数类型 parameterized Type
        ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();
        System.out.println(parameterizedType.toString());

        // 泛型类型变量，Type Variable：
        // <E>
        Type[] typeVariables = parameterizedType.getActualTypeArguments();
        Stream.of(typeVariables)
                .map(TypeVariable.class::cast)
                .forEach(System.out::println);


    }
}
