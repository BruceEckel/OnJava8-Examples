//: patterns/factory/ShapeFactory1.java
// A simple static factory method.
package patterns.factory;
import java.util.*;

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
    if(type == "Circle") return new Circle();
    if(type == "Square") return new Square();
    throw new BadShapeCreation(type);
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
  public static void main(String args[]) {
    String shlist[] = { "Circle", "Square", 
      "Square", "Circle", "Circle", "Square" };
    ArrayList shapes = new ArrayList();
    try {
      for (String shlist1 : shlist) {
        shapes.add(Shape.factory(shlist1));
      }
    } catch(BadShapeCreation e) {
      e.printStackTrace();
      return;
    }
    Iterator i = shapes.iterator();
    while(i.hasNext()) {
      Shape s = (Shape)i.next();
      s.draw();
      s.erase();
    }
  } 
} ///:~
