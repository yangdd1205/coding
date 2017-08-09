package chapter18;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 * Created by yangdd on 2017/7/27.
 */
public class ZipCompress {
    public static void main(String[] args) throws IOException {

        FileOutputStream f = new FileOutputStream("test.zip");
        CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());//Checksum类来计算和校验文件的校验和方式：Adler32（它快一些）和CRC32（慢一些，但更准确）
        ZipOutputStream zos = new ZipOutputStream(csum);
        BufferedOutputStream out = new BufferedOutputStream(zos);
        zos.setComment("A test of Java Zipping");
        for (String arg : args) {
            System.out.println("Writing file " + arg);
            BufferedReader in = new BufferedReader(new FileReader(arg));
            //对于每一个要加入压缩档案的文件，都必须调用putNextEntry()，并将其传递给一个ZipEntry对象。
            zos.putNextEntry(new ZipEntry(arg));
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            in.close();
            out.flush();
        }
        out.close();

        // Checksum valid only after the file has been closed
        System.out.println("Checksum: " + csum.getChecksum().getValue());
        // Now extract the files:
        System.out.println("Reading file");
        FileInputStream fi = new FileInputStream("test.zip");
        CheckedInputStream csumi = new CheckedInputStream(fi, new Adler32());
        ZipInputStream in2 = new ZipInputStream(csumi);
        BufferedInputStream bis = new BufferedInputStream(in2);
        ZipEntry ze;
        while ((ze = in2.getNextEntry()) != null) {
            System.out.println("Reading file " + ze);
            int x;
            while ((x = bis.read()) != -1) {
                System.out.write(x);
            }
        }
        if (args.length == 1) {
            System.out.println("Checksum: " + csumi.getChecksum().getValue());
        }
        bis.close();
        // Alternative way to open and read Zip files:
        ZipFile zf = new ZipFile("test.zip");
        Enumeration e = zf.entries();
        while (e.hasMoreElements()) {
            ZipEntry ze2 = (ZipEntry) e.nextElement();
            System.out.println("File: " + ze2);
        }
    }
}
