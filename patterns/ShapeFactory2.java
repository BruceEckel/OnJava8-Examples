// patterns/ShapeFactory2.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.*;
import java.lang.reflect.*;
import java.util.stream.*;
import patterns.shapes.*;

class DynamicFactory {
  static Map<String, Constructor> factories =
    new HashMap<>();
  static Constructor load(String id) {
    System.out.println("loading " + id);
    try {
      return Class.forName("patterns.shapes." + id)
        .getConstructor();
    } catch(Exception e) {
      throw new BadShapeCreation(id);
    }
  }
  static Shape create(String id) {
    try {
      return (Shape)factories
        .computeIfAbsent(id, DynamicFactory::load)
        .newInstance();
    } catch(Exception e) {
      throw new BadShapeCreation(id);
    }
  }
}

public class ShapeFactory2 {
  public static void main(String[] args) {
    Stream.of("Circle", "Square", "Triangle",
      "Square", "Circle", "Circle", "Triangle")
      .map(DynamicFactory::create)
      .peek(Shape::draw)
      .peek(Shape::erase)
      .count();
  }
}
/* Output:
loading Circle
Circle[0] draw
Circle[0] erase
loading Square
Square[1] draw
Square[1] erase
loading Triangle
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
