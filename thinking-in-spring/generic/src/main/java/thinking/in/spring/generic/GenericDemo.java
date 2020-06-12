package thinking.in.spring.generic;

import java.util.ArrayList;
import java.util.Collection;

public class GenericDemo {

    public static void main(String[] args) {


        Collection<String> list =new ArrayList<>();
        list.add("Hello");
        list.add("World");

        // 编译错误
//        list.add(1)

        // 泛型擦写
        Collection tmp = list;
        // 编译通过
        tmp.add(1);

        System.out.println(list);
    }
}
