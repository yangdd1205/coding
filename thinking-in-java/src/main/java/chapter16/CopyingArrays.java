package chapter16;

import java.util.Arrays;

public class CopyingArrays {

    public static void main(String[] args) {
        int[] i = new int[7];
        int[] j = new int[10];

        Arrays.fill(i, 47);
        Arrays.fill(j, 99);

        System.out.println("i = " + Arrays.toString(i));
        System.out.println("j = " + Arrays.toString(j));

        System.arraycopy(i, 0, j, 0, i.length);
        System.out.println("j = " + Arrays.toString(j));

        int[] k = new int[5];
        Arrays.fill(k, 103);
        System.arraycopy(i, 0, k, 0, k.length);
        System.out.println("k = " + Arrays.toString(k));

        //Object:
        Integer[] u = new Integer[10];
        Integer[] v = new Integer[5];
        Arrays.fill(u, new Integer(47));
        Arrays.fill(v, new Integer(99));
        System.out.println("u = " + Arrays.toString(u));
        System.out.println("v = " + Arrays.toString(v));

        System.arraycopy(v, 0, u, u.length / 2, v.length);
        System.out.println("u = " + Arrays.toString(u));
    }
}/* Output:
i = [47, 47, 47, 47, 47, 47, 47]
j = [99, 99, 99, 99, 99, 99, 99, 99, 99, 99]
j = [47, 47, 47, 47, 47, 47, 47, 99, 99, 99]
k = [47, 47, 47, 47, 47]
u = [47, 47, 47, 47, 47, 47, 47, 47, 47, 47]
v = [99, 99, 99, 99, 99]
u = [47, 47, 47, 47, 47, 99, 99, 99, 99, 99]
*///:~
