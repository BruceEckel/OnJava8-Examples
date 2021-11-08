// serialization/AStoreCADState.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Saving the state of a fictitious CAD system
import java.io.*;
import java.util.*;
import java.util.stream.*;

enum Color { RED, BLUE, GREEN }

abstract class Shape implements Serializable {
  private int xPos, yPos, dimension;
  Shape(int xVal, int yVal, int dim) {
    xPos = xVal;
    yPos = yVal;
    dimension = dim;
  }
  public abstract void setColor(Color newColor);
  public abstract Color getColor();
  @Override public String toString() {
    return "\n" + getClass() + " " + getColor() +
      " xPos[" + xPos + "] yPos[" + yPos +
      "] dim[" + dimension + "]";
  }
  private static Random rand = new Random(47);
  private static int counter = 0;
  public static Shape randomFactory() {
    int xVal = rand.nextInt(100);
    int yVal = rand.nextInt(100);
    int dim = rand.nextInt(100);
    switch(counter++ % 2) {
      default:
      case 0: return new Circle(xVal, yVal, dim);
      case 1: return new Line(xVal, yVal, dim);
    }
  }
}

class Circle extends Shape {
  Circle(int xVal, int yVal, int dim) {
    super(xVal, yVal, dim);
  }
  private static Color color = Color.RED;
  @Override public void setColor(Color newColor) {
    color = newColor;
  }
  @Override public Color getColor() { return color; }
}

class Line extends Shape {
  Line(int xVal, int yVal, int dim) {
    super(xVal, yVal, dim);
  }
  private static Color color = Color.RED;
  @Override public void setColor(Color newColor) {
    color = newColor;
  }
  @Override public Color getColor() { return color; }
  public static void
  serializeStaticState(ObjectOutputStream os)
  throws IOException {
    os.writeObject(color);
  }
  public static void
  deserializeStaticState(ObjectInputStream os)
  throws IOException, ClassNotFoundException {
    color = (Color)os.readObject();
  }
}

public class AStoreCADState {
  public static void main(String[] args) {
    List<Class<? extends Shape>> shapeTypes =
      Arrays.asList(Circle.class, Line.class);
    List<Shape> shapes = IntStream.range(0, 5)
      .mapToObj(i -> Shape.randomFactory())
      .collect(Collectors.toList());
    // Set all the static colors to GREEN:
    shapes.forEach(s -> s.setColor(Color.GREEN));
    // Serialize everything to CADState.dat:
    try(
      ObjectOutputStream out =
        new ObjectOutputStream(
          new FileOutputStream("CADState.dat"))
    ) {
      out.writeObject(shapeTypes);
      Line.serializeStaticState(out);
      out.writeObject(shapes);
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
    // Display the shapes:
    System.out.println(shapes);
  }
}
/* Output:
[
class Circle GREEN xPos[58] yPos[55] dim[93],
class Line GREEN xPos[61] yPos[61] dim[29],
class Circle GREEN xPos[68] yPos[0] dim[22],
class Line GREEN xPos[7] yPos[88] dim[28],
class Circle GREEN xPos[51] yPos[89] dim[9]]
*/
