//: patterns/ShapeFactory2.java
// Polymorphic factory methods.
import java.util.*;
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
  protected abstract Shape create();
  static Map<String, ShapeFactory> factories =
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
    return factories.get(id).create();
  }
}

class Circle implements Shape {
  private Circle() {}
  public void draw() { print("Circle.draw"); }
  public void erase() { print("Circle.erase"); }
  static class Factory extends ShapeFactory {
    @Override
    protected Shape create() {
      return new Circle();
    }
  }
  static {
    ShapeFactory.factories.put(
      "Circle", new Circle.Factory());
  }
}

class Square implements Shape {
  private Square() {}
  public void draw() { print("Square.draw"); }
  public void erase() { print("Square.erase"); }
  static class Factory extends ShapeFactory {
    @Override
    protected Shape create() {
      return new Square();
    }
  }
  static {
    ShapeFactory.factories.put(
      "Square", new Square.Factory());
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
    Iterator<Shape> i = shapes.iterator();
    while(i.hasNext()) {
      Shape s = i.next();
      s.draw();
      s.erase();
    }
  }
} ///:~
