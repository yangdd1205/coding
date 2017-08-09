package chapter18;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by yangdd on 2017/7/26.
 */
public class MappedIO {
    private static int numOfInts = 4000000;
    private static int numOfBuffInts = 200000;

    private abstract static class Tester {
        private String name;

        public Tester(String name) {
            this.name = name;
        }

        public void runTest() {
            System.out.print(name + " ");
            try {
                long start = System.nanoTime();
                test();
                double duration = System.nanoTime() - start;
                System.out.format("%.2f\n", duration / 1.0e9);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        public abstract void test() throws IOException;
    }

    private static Tester[] testers = {
            new Tester("Stream Write") {
                @Override
                public void test() throws IOException {
                    DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File("temp.tmp"))));
                    for (int i = 0; i < numOfInts; i++) {
                        dos.writeInt(i);
                    }
                    dos.close();
                }
            },
            new Tester("Mapped Write") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new RandomAccessFile("temp.tmp", "rw").getChannel();
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
                    for (int i = 0; i < numOfInts; i++) {
                        ib.put(i);
                    }
                    fc.close();
                }
            },
            new Tester("Stream Read") {
                @Override
                public void test() throws IOException {
                    DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream("temp.tmp")));
                    for (int i = 0; i < numOfInts; i++) {
                        dis.readInt();
                    }
                    dis.close();
                }
            },
            new Tester("Mapped Read") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new FileInputStream("temp.tmp").getChannel();
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).asIntBuffer();
                    while (ib.hasRemaining()) {
                        ib.get();
                    }
                    fc.close();
                }
            },
            new Tester("Stream Read/Write") {
                @Override
                public void test() throws IOException {
                    RandomAccessFile raf = new RandomAccessFile("temp.tmp", "rw");
                    raf.write(1);
                    for (int i = 0; i < numOfBuffInts; i++) {
                        raf.setLength(raf.length() - 4);
                        raf.writeInt(raf.readInt());
                    }
                    raf.close();
                }
            },
            new Tester("Mapped Read/Write") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new RandomAccessFile("temp.tmp", "rw").getChannel();
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
                    ib.put(0);
                    for (int i = 1; i < numOfBuffInts; i++) {
                        ib.put(ib.get(i - 1));
                    }
                    fc.close();
                }
            }
    };

    public static void main(String[] args) {
        for (Tester tester : testers) {
            tester.runTest();
        }
    }
}/* Output:
Stream Write 0.23
Mapped Write 0.02
Stream Read 0.33
Mapped Read 0.01
Stream Read/Write 2.34
Mapped Read/Write 0.01
*///:~
