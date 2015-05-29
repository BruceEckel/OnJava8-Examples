//: polymorphism/shape/Circle.java
// ©2015 MindView LLC: see Copyright.txt
package polymorphism.shape;
import static net.mindview.util.Print.*;

public class Circle extends Shape {
  @Override
  public void draw() { print("Circle.draw()"); }
  @Override
  public void erase() { print("Circle.erase()"); }
} ///:~
