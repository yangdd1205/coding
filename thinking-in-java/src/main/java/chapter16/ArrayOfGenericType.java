package chapter16;

public class ArrayOfGenericType<T> {

    T[] array;

    public ArrayOfGenericType(int size){
        //array = new T[size]; //Illegal
        array = (T[]) new Object[size];
    }
}
