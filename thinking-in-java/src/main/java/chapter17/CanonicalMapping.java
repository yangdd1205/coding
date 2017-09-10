package chapter17;

import java.util.WeakHashMap;

class Element {
    private String ident;

    public Element(String id) {
        this.ident = id;
    }

    @Override
    public String toString() {
        return ident;
    }

    @Override
    public int hashCode() {
        return ident.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Element && ident.equals(((Element) obj).ident);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalizing " + getClass().getSimpleName() + " " + ident);
    }
}

class Key extends Element {
    public Key(String id) {
        super(id);
    }
}

class Value extends Element {
    public Value(String id) {
        super(id);
    }
}


public class CanonicalMapping {
    public static void main(String[] args) {
        int size = 1000;
        // Or, choose size via the command line:
        if (args.length > 0) {
            size = new Integer(args[0]);
        }

        Key[] keys = new Key[size];
        WeakHashMap<Key, Value> map = new WeakHashMap<>();

        for (int i = 0; i < size; i++) {
            Key k = new Key(Integer.toString(i));
            Value v = new Value(Integer.toString(i));
            if (i % 3 == 0) {
                keys[i] = k;//Save as "real" references
            }
            map.put(k, v);
        }
        System.gc();
    }
}
