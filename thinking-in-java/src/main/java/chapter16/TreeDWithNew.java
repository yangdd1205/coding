package chapter16;

import java.util.Arrays;

public class TreeDWithNew {

    public static void main(String[] args) {
        // 3-D Array with fixed length
        int[][][] a = new int[2][2][4];
        System.out.println(Arrays.deepToString(a));
    }
}/* Output:
[[[0, 0, 0, 0], [0, 0, 0, 0]], [[0, 0, 0, 0], [0, 0, 0, 0]]]
*///:~