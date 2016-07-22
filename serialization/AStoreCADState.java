// serialization/AStoreCADState.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Saving the state of a fictitious CAD system
import java.io.*;
import java.util.*;

abstract class Shape implements Serializable {
  public static final int RED = 1, BLUE = 2, GREEN = 3;
  private int xPos, yPos, dimension;
  private static SplittableRandom rand = new SplittableRandom(47);
  private static int counter = 0;
  public abstract void setColor(int newColor);
  public abstract int getColor();
  public Shape(int xVal, int yVal, int dim) {
    xPos = xVal;
    yPos = yVal;
    dimension = dim;
  }
  public String toString() {
    return getClass() +
      "color[" + getColor() + "] xPos[" + xPos +
      "] yPos[" + yPos + "] dim[" + dimension + "]\n";
  }
  public static Shape randomFactory() {
    int xVal = rand.nextInt(100);
    int yVal = rand.nextInt(100);
    int dim = rand.nextInt(100);
    switch(counter++ % 3) {
      default:
      case 0: return new Circle(xVal, yVal, dim);
      case 1: return new Square(xVal, yVal, dim);
      case 2: return new Line(xVal, yVal, dim);
    }
  }
}

class Circle extends Shape {
  private static int color = RED;
  public Circle(int xVal, int yVal, int dim) {
    super(xVal, yVal, dim);
  }
  public void setColor(int newColor) { color = newColor; }
  public int getColor() { return color; }
}

class Square extends Shape {
  private static int color;
  public Square(int xVal, int yVal, int dim) {
    super(xVal, yVal, dim);
    color = RED;
  }
  public void setColor(int newColor) { color = newColor; }
  public int getColor() { return color; }
}

class Line extends Shape {
  private static int color = RED;
  public static void
  serializeStaticState(ObjectOutputStream os)
  throws IOException { os.writeInt(color); }
  public static void
  deserializeStaticState(ObjectInputStream os)
  throws IOException { color = os.readInt(); }
  public Line(int xVal, int yVal, int dim) {
    super(xVal, yVal, dim);
  }
  public void setColor(int newColor) { color = newColor; }
  public int getColor() { return color; }
}

public class AStoreCADState {
  public static void
  main(String[] args) throws Exception {
    List<Class<? extends Shape>> shapeTypes =
      new ArrayList<>();
    // Add references to the class objects:
    shapeTypes.add(Circle.class);
    shapeTypes.add(Square.class);
    shapeTypes.add(Line.class);
    List<Shape> shapes = new ArrayList<>();
    // Make some shapes:
    for(int i = 0; i < 10; i++)
      shapes.add(Shape.randomFactory());
    // Set all the static colors to GREEN:
    for(int i = 0; i < 10; i++)
      ((Shape)shapes.get(i)).setColor(Shape.GREEN);
    // Save the state vector:
    try(ObjectOutputStream out =
          new ObjectOutputStream(
            new FileOutputStream("CADState.dat"))) {
      out.writeObject(shapeTypes);
      Line.serializeStaticState(out);
      out.writeObject(shapes);
    }
    // Display the shapes:
    System.out.println(shapes);
  }
}
/* Output:
[class Circlecolor[3] xPos[35] yPos[37] dim[41]
, class Squarecolor[3] xPos[20] yPos[77] dim[79]
, class Linecolor[3] xPos[56] yPos[68] dim[48]
, class Circlecolor[3] xPos[93] yPos[70] dim[7]
, class Squarecolor[3] xPos[0] yPos[25] dim[62]
, class Linecolor[3] xPos[34] yPos[50] dim[82]
, class Circlecolor[3] xPos[31] yPos[67] dim[66]
, class Squarecolor[3] xPos[54] yPos[21] dim[6]
, class Linecolor[3] xPos[63] yPos[39] dim[63]
, class Circlecolor[3] xPos[13] yPos[90] dim[92]
]
*/
