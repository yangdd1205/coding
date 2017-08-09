package chapter16;

import java.util.Arrays;

public class FillingArrays {
    public static void main(String[] args) {
        int size = 6;
        boolean[] a1 = new boolean[size];
        byte[] a2 = new byte[size];
        char[] a3 = new char[size];
        short[] a4 = new short[size];
        int[] a5 = new int[size];
        long[] a6 = new long[size];
        float[] a7 = new float[size];
        double[] a8 = new double[size];
        String[] a9 = new String[size];

        Arrays.fill(a1,true);
        System.out.println("a1 = "+Arrays.toString(a1));

        Arrays.fill(a2, (byte) 11);
        System.out.println("a2 = "+Arrays.toString(a2));

        Arrays.fill(a3,'x');
        System.out.println("a3 = "+Arrays.toString(a3));

        Arrays.fill(a9,"Hello");
        System.out.println("a9 = "+Arrays.toString(a9));

        Arrays.fill(a9,3,5,"World");
        System.out.println("a9 = "+Arrays.toString(a9));

    }
} /* Output:
a1 = [true, true, true, true, true, true]
a2 = [11, 11, 11, 11, 11, 11]
a3 = [x, x, x, x, x, x]
a9 = [Hello, Hello, Hello, Hello, Hello, Hello]
a9 = [Hello, Hello, Hello, World, World, Hello]
*///:~
