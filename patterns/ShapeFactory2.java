//: patterns/ShapeFactory2.java
// Polymorphic factory methods.
import java.util.*;

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
  static Map factories = new HashMap();
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
    return 
      ((ShapeFactory)factories.get(id)).create();
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
  public void draw() { 
    System.out.println("Square.draw"); 
  }
  public void erase() { 
    System.out.println("Square.erase"); 
  }
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
    ArrayList shapes = new ArrayList();
    try {
      for(int i = 0; i < shlist.length; i++)
        shapes.add(
          ShapeFactory.createShape(shlist[i]));
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
