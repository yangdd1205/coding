package chapter18;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by yangdd on 2017/7/22.
 */
public class TextFile extends ArrayList<String> {

    //Read a file as as single string
    public static String read(String fileName) {
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
            try {

                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");

                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    //Writer a single file in one method call
    public static void write(String fileName, String text) {
        try {
            PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
            try {
                out.write(text);
            } finally {
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Read a file. split by any regular expression

    public TextFile(String fileName, String splitter) {
        super(Arrays.asList(read(fileName).split(splitter)));
        // Regular expression split() often leaves an empty
        // String at the first postion
        if (get(0).equals("")) remove(0);
    }

    public TextFile(String fileName) {
        this(fileName, "\n");
    }

    public void write(String fileName) {
        try {
            PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
            try {
                for (String item : this) {
                    out.write(item);
                }
            } finally {
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String file = read("TextFile.java");
        write("test.txt", file);
        TextFile text = new TextFile("test2.txt");
        TreeSet<String> words = new TreeSet<>(new TextFile("TextFile.java", "\\w+"));
        System.out.println(words.headSet("a"));
    }

}
