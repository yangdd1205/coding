package chapter18;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by yangdd on 2017/7/25.
 */
public class GetData {

    private static final int BSIZE = 1024;

    public static void main(String[] args) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        int i = 0;
        while (i++ < bb.limit())
            if (bb.get() != 0) {
                System.out.println("nonzero");
            }
        System.out.println("i = " + i);
        bb.rewind();
        bb.asCharBuffer().put("Howdy!");
        char c;
        while ((c = bb.getChar()) != 0) {
            System.out.print(c + " ");
        }
        System.out.println();
        bb.rewind();
        bb.asIntBuffer().put(99471142);
        System.out.println(bb.getInt());
        bb.rewind();
        bb.asLongBuffer().put(99471142);
        System.out.println(bb.getLong());
        bb.rewind();
        bb.asFloatBuffer().put(99471142);
        System.out.println(bb.getFloat());
        bb.rewind();
        bb.asDoubleBuffer().put(99471142);
        System.out.println(bb.getDouble());
        bb.rewind();
    }

}
