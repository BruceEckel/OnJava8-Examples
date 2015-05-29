//: patterns/ShapeFactory2.java
// ©2015 MindView LLC: see Copyright.txt
// Polymorphic factory methods.
import java.util.*;
import java.util.function.*;
import static net.mindview.util.Print.*;

class BadShapeCreation extends Exception {
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
  public void draw() { print("Circle.draw"); }
  public void erase() { print("Circle.erase"); }
  static {
    ShapeFactory.factories.put("Circle", Circle::new);
  }
}

class Square implements Shape {
  private Square() {}
  public void draw() { print("Square.draw"); }
  public void erase() { print("Square.erase"); }
  static {
    ShapeFactory.factories.put("Square", Square::new);
  }
}

public class ShapeFactory2 {
  public static void main(String args[]) {
    String shlist[] = { "Circle", "Square",
      "Square", "Circle", "Circle", "Square" };
    ArrayList<Shape> shapes = new ArrayList<>();
    try {
      for(String shlist1 : shlist) {
        shapes.add(
          ShapeFactory.createShape(shlist1));
      }
    } catch(BadShapeCreation e) {
      e.printStackTrace();
      return;
    }
    shapes.forEach(Shape::draw);
    shapes.forEach(Shape::erase);
  }
} ///:~
