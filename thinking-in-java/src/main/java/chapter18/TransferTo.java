package chapter18;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by yangdd on 2017/7/24.
 */
public class TransferTo {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("arguments: sourcefile destfile");
            System.exit(1);
        }
        FileChannel in = new FileInputStream(args[0]).getChannel(), out = new FileOutputStream(args[1]).getChannel();
        in.transferTo(0, in.size(), out);
        //or
        out.transferFrom(in, 0, in.size());
    }
}
