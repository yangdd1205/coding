package chapter17;

import util.Generator;
import util.MapData;
import util.Pair;

import java.util.Iterator;

class Letters implements Generator<Pair<Integer, String>>, Iterable<Integer> {
    private int size = 9;
    private int number = 1;
    private char letter = 'A';

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return number < size;
            }

            @Override
            public Integer next() {
                return number++;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public Pair<Integer, String> next() {
        return new Pair<>(number++, "" + letter++);
    }
}

public class MapDataTest {

    public static void main(String[] args) {
        // Pair Generator:
        System.out.println(MapData.map(new Letters(), 11));
        // Two separate generators:
        //System.out.println(MapData.map(new CountingGenerator.Character(),new RandomGenerator.String(3),8));

        // A key Generator and a single value:
        //System.out.println(MapData.map(new CountingGenerator.Character(),"Value",8));

        // An Iterable and a value generator：
        //System.out.println(MapData.map(new Letters(),new RandomGenerator.String(3)));

        // An Iterable and a single Value:
        System.out.println(MapData.map(new Letters(), "Pop"));
    }
}/* Output:
{1=A, 2=B, 3=C, 4=D, 5=E, 6=F, 7=G, 8=H, 9=I, 10=J, 11=K}
{1=Pop, 2=Pop, 3=Pop, 4=Pop, 5=Pop, 6=Pop, 7=Pop, 8=Pop}
*///:~
