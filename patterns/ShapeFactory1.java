// patterns/ShapeFactory1.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// A simple static factory method
import java.util.*;
import java.util.stream.*;
import patterns.shapes.*;

class StaticFactory {
  static Shape create(String type) {
    switch(type) {
      case "Circle": return new Circle();
      case "Square": return new Square();
      case "Triangle": return new Triangle();
      default:
        throw new BadShapeCreation(type);
    }
  }
}

public class ShapeFactory1 {
  public static void main(String[] args) {
    Stream.of("Circle", "Square", "Triangle",
      "Square", "Circle", "Circle", "Triangle")
      .map(StaticFactory::create)
      .peek(Shape::draw)
      .peek(Shape::erase)
      .count(); // Terminal operation
  }
}
/* Output:
Circle[0] draw
Circle[0] erase
Square[1] draw
Square[1] erase
Triangle[2] draw
Triangle[2] erase
Square[3] draw
Square[3] erase
Circle[4] draw
Circle[4] erase
Circle[5] draw
Circle[5] erase
Triangle[6] draw
Triangle[6] erase
*/
