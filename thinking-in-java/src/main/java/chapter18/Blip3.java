package chapter18;

import java.io.*;

public class Blip3 implements Externalizable {

    private int i;
    private String s;// No initialized

    public Blip3() {
        System.out.println("Blip3 Constructor");
        //s,i not  initialized
    }

    public Blip3(int i, String s) {
        System.out.println("Blip3(int i, String s)");
        this.i = i;
        this.s = s;
        // s & i initialized only in non-default constructor.
    }

    @Override
    public String toString() {
        return s + i;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip3.writeExternal");
        // You must do this:
        out.writeObject(s);
        out.writeInt(i);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip3.readExternal");
        // You must do this:
        s = (String) in.readObject();
        i = in.readInt();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Constructing objects:");
        Blip3 b3 = new Blip3(47, "A String ");
        System.out.println(b3);
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Blip3.out"));
        System.out.println("Saving object:");
        o.writeObject(b3);
        o.close();

        //Now get it back:
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Blip3.out"));
        System.out.println("Recovering b3:");
        b3 = (Blip3) in.readObject();
        System.out.println(b3);
    }
}/* Output:
Constructing objects:
Blip3(int i, String s)
A String 47
Saving object:
Blip3.writeExternal
Recovering b3:
Blip3 Constructor
Blip3.readExternal
A String 47
*///:~
