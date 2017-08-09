package chapter18;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class RecoverCADState {

    public static void main(String[] args) throws Exception {

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("CADState.out"));
        // Read in the same order they were written:
        List<Class<? extends Shape>> shapeTypes = (List<Class<? extends Shape>>) in.readObject();

        Line.deserializeStaticState(in);

        List<Shape> shapes = (List<Shape>) in.readObject();

        System.out.println(shapes);
    }
}/* Output:
[Circle color[1 ] xPos[58] yPos[55] dim[93]
, Square color[0 ] xPos[61] yPos[61] dim[29]
, Line color[3 ] xPos[68] yPos[0] dim[22]
, Circle color[1 ] xPos[7] yPos[88] dim[28]
, Square color[0 ] xPos[51] yPos[89] dim[9]
, Line color[3 ] xPos[78] yPos[98] dim[61]
, Circle color[1 ] xPos[20] yPos[58] dim[16]
, Square color[0 ] xPos[40] yPos[11] dim[22]
, Line color[3 ] xPos[4] yPos[83] dim[6]
, Circle color[1 ] xPos[75] yPos[10] dim[42]
]
*///:~