// serialization/RecoverCADState.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Restoring the state of the fictitious CAD system
// {RunFirst: AStoreCADState}
import java.io.*;
import java.util.*;

public class RecoverCADState {
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
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
    } catch(IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
/* Output:
[
class Circle RED xPos[58] yPos[55] dim[93],
class Line GREEN xPos[61] yPos[61] dim[29],
class Circle RED xPos[68] yPos[0] dim[22],
class Line GREEN xPos[7] yPos[88] dim[28],
class Circle RED xPos[51] yPos[89] dim[9]]
*/
