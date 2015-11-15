// patterns/ShapeFactory2.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Polymorphic factory methods.
import java.util.*;
import java.util.function.*;
import java.util.stream.*;


class BadShapeCreation extends RuntimeException {
  BadShapeCreation(String msg) {
    super(msg);
  }
}

interface Shape {
  void draw();
  void erase();
}

abstract class ShapeFactory {
  static Map<String, Supplier<Shape>> factories =
    new HashMap<>();
  static Shape createShape(String id)
  throws BadShapeCreation {
    if(!factories.containsKey(id)) {
      try {
        Class.forName(id); // Load dynamically
      } catch(ClassNotFoundException e) {
        throw new BadShapeCreation(id);
      }
      // See if it was put in:
      if(!factories.containsKey(id))
        throw new BadShapeCreation(id);
    }
    return factories.get(id).get();
  }
}

class Circle implements Shape {
  private Circle() {}
  public void draw() {
    System.out.println("Circle.draw");
  }
  public void erase() {
    System.out.println("Circle.erase");
  }
  static {
    ShapeFactory.factories.put("Circle", Circle::new);
  }
}

class Square implements Shape {
  private Square() {}
  public void draw() {
    System.out.println("Square.draw");
  }
  public void erase() {
    System.out.println("Square.erase");
  }
  static {
    ShapeFactory.factories.put("Square", Square::new);
  }
}

public class ShapeFactory2 {
  public static void main(String args[]) {
    List<Shape> shapes = Stream.of("Circle", "Square",
      "Square", "Circle", "Circle", "Square")
      .map(ShapeFactory::createShape)
      .collect(Collectors.toList());
    shapes.forEach(Shape::draw);
    shapes.forEach(Shape::erase);
  }
}
/* Output:
Circle.draw
Square.draw
Square.draw
Circle.draw
Circle.draw
Square.draw
Circle.erase
Square.erase
Square.erase
Circle.erase
Circle.erase
Square.erase
*/
