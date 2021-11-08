// enumerations/Shapes.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Preview in JDK 17
// Compile with javac flags:
//   --enable-preview --source 17
// Run with java flag: --enable-preview
import java.util.*;

sealed interface Shape {
  double area();
}

record Circle(double radius) implements Shape {
  @Override public double area() {
    return Math.PI * radius * radius;
  }
}

record Rectangle(double side1, double side2)
  implements Shape {
  @Override public double area() {
    return side1 * side2;
  }
}

public class Shapes {
  static void classify(Shape s) {
    System.out.println(switch(s) {
      case Circle c && c.area() < 100.0
        -> "Small Circle: " + c;
      case Circle c -> "Large Circle: " + c;
      case Rectangle r && r.side1() == r.side2()
        -> "Square: " + r;
      case Rectangle r -> "Rectangle: " + r;
    });
  }
  public static void main(String[] args) {
    List.of(
      new Circle(5.0),
      new Circle(25.0),
      new Rectangle(12.0, 12.0),
      new Rectangle(12.0, 15.0)
    ).forEach(t -> classify(t));
  }
}
/* Output:
Small Circle: Circle[radius=5.0]
Large Circle: Circle[radius=25.0]
Square: Rectangle[side1=12.0, side2=12.0]
Rectangle: Rectangle[side1=12.0, side2=15.0]
*/
