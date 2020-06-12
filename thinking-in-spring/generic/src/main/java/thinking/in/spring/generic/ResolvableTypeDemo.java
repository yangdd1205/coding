package thinking.in.spring.generic;

import org.springframework.core.ResolvableType;

public class ResolvableTypeDemo {

    public static void main(String[] args) {
        // 工厂创建
        // StringList <- ArrayList <- AbstractList <- List
        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);


        resolvableType.getSuperType();//ArrayList
        resolvableType.getSuperType().getSuperType();//AbstractList

        System.out.println(resolvableType.asCollection().resolve());//获取 raw 类型
        System.out.println(resolvableType.asCollection().resolveGeneric(0)); // 获取泛型参数类型
    }
}
