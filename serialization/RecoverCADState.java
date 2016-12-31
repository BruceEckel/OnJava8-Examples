// serialization/RecoverCADState.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Restoring the state of the fictitious CAD system
// {RunFirst: AStoreCADState}
import java.io.*;
import java.util.*;

public class RecoverCADState {
  @SuppressWarnings("unchecked")
  public static void
  main(String[] args) throws Exception {
    try(
      ObjectInputStream in =
        new ObjectInputStream(
          new FileInputStream("CADState.dat"))
    ) {
      // Read in the same order they were written:
      List<Class<? extends Shape>> shapeTypes =
        (List<Class<? extends Shape>>)in.readObject();
      Line.deserializeStaticState(in);
      List<Shape> shapes = (List<Shape>)in.readObject();
      System.out.println(shapes);
    }
  }
}
/* Output:
[class Circlecolor[1] xPos[35] yPos[37] dim[41]
, class Squarecolor[0] xPos[20] yPos[77] dim[79]
, class Linecolor[3] xPos[56] yPos[68] dim[48]
, class Circlecolor[1] xPos[93] yPos[70] dim[7]
, class Squarecolor[0] xPos[0] yPos[25] dim[62]
, class Linecolor[3] xPos[34] yPos[50] dim[82]
, class Circlecolor[1] xPos[31] yPos[67] dim[66]
, class Squarecolor[0] xPos[54] yPos[21] dim[6]
, class Linecolor[3] xPos[63] yPos[39] dim[63]
, class Circlecolor[1] xPos[13] yPos[90] dim[92]
]
*/
