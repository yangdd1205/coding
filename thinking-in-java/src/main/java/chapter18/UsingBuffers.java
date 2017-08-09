package chapter18;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * Created by yangdd on 2017/7/25.
 */
public class UsingBuffers {

    private static void SymmetricScramble(CharBuffer buffer) {
        while (buffer.hasRemaining()) {
            buffer.mark();
            char c1 = buffer.get();
            char c2 = buffer.get();
            buffer.reset();
            buffer.put(c2).put(c1);
        }
    }

    public static void main(String[] args) {
        char[] data = "UsingBuffers".toCharArray();
        ByteBuffer bb = ByteBuffer.allocate(data.length * 2);
        CharBuffer cb = bb.asCharBuffer();
        cb.put(data);
        System.out.println(cb.rewind());
        SymmetricScramble(cb);
        System.out.println(cb.rewind());
        SymmetricScramble(cb);
        System.out.println(cb.rewind());
    }
}/* Output:
UsingBuffers
sUniBgfuefsr
UsingBuffers
*///~

