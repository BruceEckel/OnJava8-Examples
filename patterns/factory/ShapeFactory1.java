// patterns/factory/ShapeFactory1.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// A simple static factory method
// {java patterns.factory.ShapeFactory1}
package patterns.factory;
import java.util.*;
import java.util.stream.*;

class BadShapeCreation extends RuntimeException {
  BadShapeCreation(String msg) {
    super(msg);
  }
}

abstract class Shape {
  public abstract void draw();
  public abstract void erase();
  static Shape factory(String type) {
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
  public void draw() {
    System.out.println("Circle.draw");
  }
  @Override
  public void erase() {
    System.out.println("Circle.erase");
  }
}

class Square extends Shape {
  Square() {} // Friendly constructor
  @Override
  public void draw() {
    System.out.println("Square.draw");
  }
  @Override
  public void erase() {
    System.out.println("Square.erase");
  }
}

public class ShapeFactory1 {
  public static void main(String[] args) {
    List<Shape> shapes = Stream.of(
      "Circle", "Square",
      "Square", "Circle",
      "Circle", "Square")
      .map(Shape::factory)
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
