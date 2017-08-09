package chapter18;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class House implements Serializable {
}

class Animal implements Serializable {
    private String name;
    private House preferedHouse;

    Animal(String name, House preferedHouse) {
        this.name = name;
        this.preferedHouse = preferedHouse;
    }

    @Override
    public String toString() {
        return name + "[" + super.toString() + "], " + preferedHouse + "\n";
    }
}

public class MyWorld {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        House house = new House();
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Bosco the dog", house));
        animals.add(new Animal("Ralph the hamster", house));
        animals.add(new Animal("Molly the cat", house));
        System.out.println("animals: " + animals);

        ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
        ObjectOutputStream o1 = new ObjectOutputStream(buf1);
        o1.writeObject(animals);
        o1.writeObject(animals);// Write a 2nd set
        //Write to a different stream:
        ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
        ObjectOutputStream o2 = new ObjectOutputStream(buf2);
        o2.writeObject(animals);

        // Now get them back
        ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(buf1.toByteArray()));
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(buf2.toByteArray()));

        List animals1 = (List) in1.readObject(), animals2 = (List) in1.readObject(), animals3 = (List) in2.readObject();

        System.out.println("animals1: " + animals1);
        System.out.println("animals2: " + animals2);
        System.out.println("animals3: " + animals3);

    }
}/* Output:
animals: [Bosco the dog[thinking.chapter18.Animal@60e53b93], thinking.chapter18.House@5e2de80c
, Ralph the hamster[thinking.chapter18.Animal@1d44bcfa], thinking.chapter18.House@5e2de80c
, Molly the cat[thinking.chapter18.Animal@266474c2], thinking.chapter18.House@5e2de80c
]
animals1: [Bosco the dog[thinking.chapter18.Animal@9807454], thinking.chapter18.House@3d494fbf
, Ralph the hamster[thinking.chapter18.Animal@1ddc4ec2], thinking.chapter18.House@3d494fbf
, Molly the cat[thinking.chapter18.Animal@133314b], thinking.chapter18.House@3d494fbf
]
animals2: [Bosco the dog[thinking.chapter18.Animal@9807454], thinking.chapter18.House@3d494fbf
, Ralph the hamster[thinking.chapter18.Animal@1ddc4ec2], thinking.chapter18.House@3d494fbf
, Molly the cat[thinking.chapter18.Animal@133314b], thinking.chapter18.House@3d494fbf
]
animals3: [Bosco the dog[thinking.chapter18.Animal@b1bc7ed], thinking.chapter18.House@7cd84586
, Ralph the hamster[thinking.chapter18.Animal@30dae81], thinking.chapter18.House@7cd84586
, Molly the cat[thinking.chapter18.Animal@1b2c6ec2], thinking.chapter18.House@7cd84586
]
*///:~
