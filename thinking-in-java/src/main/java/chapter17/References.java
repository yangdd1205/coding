package chapter17;

import java.lang.ref.*;
import java.util.LinkedList;

class VeryBig {
    private static final int SIZE = 10000;
    private long[] la = new long[SIZE];
    private String ident;

    public VeryBig(String id) {
        this.ident = id;
    }

    public String toString() {
        return ident;
    }

    protected void finalize() {
        System.out.println("Finalizing " + ident);
    }
}

public class References {
    private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<>();

    public static void checkQueue() {
        Reference<? extends VeryBig> inq = rq.poll();

        if (inq != null) {
            System.out.println("In queue: " + inq.get());
        }

    }

    public static void main(String[] args) {
        int size = 10;
        // Or， choose size via the command line:
        if (args.length > 0) {
            size = new Integer(args[0]);
        }
        LinkedList<SoftReference<VeryBig>> sa = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            sa.add(new SoftReference<VeryBig>(new VeryBig("Soft " + i), rq));
            System.out.println("Just created: " + sa.getLast());
            checkQueue();
        }

        LinkedList<WeakReference<VeryBig>> wa = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            wa.add(new WeakReference<VeryBig>(new VeryBig("Weak " + i), rq));
            System.out.println("Just created: " + wa.getLast());
            checkQueue();
        }
        SoftReference<VeryBig> s = new SoftReference<VeryBig>(new VeryBig("Soft"));
        WeakReference<VeryBig> w = new WeakReference<VeryBig>(new VeryBig("Weak"));
        System.gc();

        LinkedList<PhantomReference<VeryBig>> pa = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            pa.add(new PhantomReference<VeryBig>(new VeryBig("Phantom " + i), rq));
            System.out.println("Just created: " + pa.getLast());
            checkQueue();
        }
    }

}
/* Output:
Just created: java.lang.ref.SoftReference@2401f4c3
Just created: java.lang.ref.SoftReference@7637f22
Just created: java.lang.ref.SoftReference@4926097b
Just created: java.lang.ref.SoftReference@762efe5d
Just created: java.lang.ref.SoftReference@5d22bbb7
Just created: java.lang.ref.SoftReference@41a4555e
Just created: java.lang.ref.SoftReference@3830f1c0
Just created: java.lang.ref.SoftReference@39ed3c8d
Just created: java.lang.ref.SoftReference@71dac704
Just created: java.lang.ref.SoftReference@123772c4
Just created: java.lang.ref.WeakReference@2d363fb3
Just created: java.lang.ref.WeakReference@7d6f77cc
Just created: java.lang.ref.WeakReference@5aaa6d82
Just created: java.lang.ref.WeakReference@73a28541
Just created: java.lang.ref.WeakReference@6f75e721
Just created: java.lang.ref.WeakReference@69222c14
Just created: java.lang.ref.WeakReference@606d8acf
Just created: java.lang.ref.WeakReference@782830e
Just created: java.lang.ref.WeakReference@470e2030
Just created: java.lang.ref.WeakReference@3fb4f649
Finalizing Weak
Finalizing Weak 9
Finalizing Weak 8
Finalizing Weak 7
Finalizing Weak 6
Finalizing Weak 5
Finalizing Weak 4
Finalizing Weak 3
Finalizing Weak 2
Finalizing Weak 1
Finalizing Weak 0
Just created: java.lang.ref.PhantomReference@383534aa
In queue: null
Just created: java.lang.ref.PhantomReference@6bc168e5
In queue: null
Just created: java.lang.ref.PhantomReference@7b3300e5
In queue: null
Just created: java.lang.ref.PhantomReference@2e5c649
In queue: null
Just created: java.lang.ref.PhantomReference@136432db
In queue: null
Just created: java.lang.ref.PhantomReference@7382f612
In queue: null
Just created: java.lang.ref.PhantomReference@1055e4af
In queue: null
Just created: java.lang.ref.PhantomReference@3caeaf62
In queue: null
Just created: java.lang.ref.PhantomReference@e6ea0c6
In queue: null
Just created: java.lang.ref.PhantomReference@6a38e57f
In queue: null
*///:~
