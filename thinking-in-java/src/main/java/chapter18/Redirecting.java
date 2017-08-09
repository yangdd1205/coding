package chapter18;

import java.io.*;

/**
 * Created by yangdd on 2017/7/22.
 */
public class Redirecting {
    public static void main(String[] args) throws IOException {
        PrintStream console = System.out;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("/Users/yangdd/Documents/code/idea/learning/src/main/java/thinking/chapter18/Redirecting.java"));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("test.out")));
        System.setIn(in);
        System.setOut(out);
        System.setErr(out);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }
        out.close();
        System.setOut(console);
    }
}
