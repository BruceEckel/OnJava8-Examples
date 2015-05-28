//: patterns/factory/ShapeFactory1.java
// A simple static factory method.
package patterns.factory;
import java.util.*;
import static net.mindview.util.Print.*;

class BadShapeCreation extends Exception {
  BadShapeCreation(String msg) {
    super(msg);
  }
}

abstract class Shape {
  public abstract void draw();
  public abstract void erase();
  static Shape factory(String type)
    throws BadShapeCreation {
    switch(type) {
      case "Circle": return new Circle();
      case "Square": return new Square();
      default:
        throw new BadShapeCreation(type);
    }
  }
}

class Circle extends Shape {
  Circle() {} // Friendly constructor
  @Override
  public void draw() { print("Circle.draw"); }
  @Override
  public void erase() { print("Circle.erase"); }
}

class Square extends Shape {
  Square() {} // Friendly constructor
  @Override
  public void draw() { print("Square.draw"); }
  @Override
  public void erase() { print("Square.erase"); }
}

public class ShapeFactory1 {
  public static void main(String args[]) {
    String shlist[] = { "Circle", "Square",
      "Square", "Circle", "Circle", "Square" };
    List<Shape> shapes = new ArrayList<>();
    try {
      for(String shlist1 : shlist) {
        shapes.add(Shape.factory(shlist1));
      }
    } catch(BadShapeCreation e) {
      e.printStackTrace();
      return;
    }
    shapes.forEach(Shape::draw);
    shapes.forEach(Shape::erase);
  }
} ///:~
