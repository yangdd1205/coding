package chapter16;

import java.util.Random;

public class CompType implements Comparable<CompType> {
    int i, j;
    private static int count = 1;

    public CompType(int n1, int n2) {
        i = n1;
        j = n2;
    }

    @Override
    public String toString() {
        String result = "[ i = " + i + ", j = " + j + "]";
        if (count++ % 3 == 0) {
            result += "\n";
        }
        return result;
    }

    @Override
    public int compareTo(CompType o) {
        return (i < o.i ? -1 : (i == o.i ? 0 : 1));
    }

    private static Random r = new Random(47);

}
