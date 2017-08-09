package chapter18;

import java.io.PrintWriter;

/**
 * Created by yangdd on 2017/7/22.
 */
public class ChangeSystemOut {
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out,true);
        out.println("Hello， world");
    }
}
