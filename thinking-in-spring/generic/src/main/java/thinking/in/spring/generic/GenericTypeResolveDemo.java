package thinking.in.spring.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.Map;

/**
 * {@link org.springframework.core.GenericTypeResolver} 示例
 */
public class GenericTypeResolveDemo {

    public static void main(String[] args) throws NoSuchMethodException {

        displayReturnTypeGenericInfo(GenericTypeResolveDemo.class, List.class, "getString");
        // String Comparable<String> 具体化
        displayReturnTypeGenericInfo(GenericTypeResolveDemo.class, Comparable.class, "getString");
        displayReturnTypeGenericInfo(GenericTypeResolveDemo.class, List.class, "getList");
        // List<Object> 是 List 泛型参数具体化
        displayReturnTypeGenericInfo(GenericTypeResolveDemo.class, List.class, "getObjectList");
        // StringList 是 List 泛型参数具体化
        displayReturnTypeGenericInfo(GenericTypeResolveDemo.class, List.class, "getStringList");


        //
    }


    public static StringList getStringList() {
        return null;
    }

    public static <E> List<E> getList() {
        return null;
    }
    public static  List<Object> getObjectList() {//泛型参数类型具体化（字节码有记录）
        return null;
    }


    public static String getString() {
        return null;
    }


    private static void displayReturnTypeGenericInfo(Class<?> containingClass, Class genericIfc, String methodName, Class... argumentTypes) throws NoSuchMethodException {
        Method method = containingClass.getMethod(methodName, argumentTypes);
        // 声明类 GenericTypeResolveDemo.class
        Class<?> returnType = GenericTypeResolver.resolveReturnType(method, containingClass);
        // 常规类作为方法返回值
        System.out.printf("GenericTypeResolver.resolveReturnType(%s,%s) = %s\n", methodName, containingClass.getSimpleName(), returnType);

        // 常规类型不具备泛型参数类型 List<E>
        Class<?> returnReturnTypeArgument = GenericTypeResolver.resolveReturnTypeArgument(method, genericIfc);
        System.out.printf("GenericTypeResolver.resolveReturnTypeArgument(%s,%s) = %s\n", methodName, containingClass.getSimpleName(), returnReturnTypeArgument);


        Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(StringList.class);
        System.out.println(typeVariableMap);
    }


}
