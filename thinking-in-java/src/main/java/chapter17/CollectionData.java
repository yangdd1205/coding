package chapter17;

import util.Generator;

import util.Generator;

import java.util.ArrayList;

public class CollectionData<T> extends ArrayList {
    public CollectionData(Generator<T> gen, int quantity) {
        for (int i = 0; i < quantity; i++) {
            add(gen.next());
        }
    }

    public static <T> CollectionData list(Generator gen, int quantity) {
        return new CollectionData<T>(gen, quantity);
    }
}
