package chapter16;

import java.util.ArrayList;
import java.util.List;

public class ArrayOfGenerics {

    public static void main(String[] args) {

        List<String>[] ls;

        List[] la = new List[10];

        ls = (List<String>[]) la;

        ls[0] = new ArrayList<>();

        // Compile-time checking produces an error
        //ls[1] = new ArrayList<Integer>();

        // The problem: List<String> is a subtype of Object
        Object[] objects = ls;//So assignment is OK

        // Compiles and runs without complaint:
        objects[1] = new ArrayList<Integer>();

        // However, if your needs are straightforward it is
        // possible to create an array of generics, albeit
        // with an "unchecked" warning:

    }

}
