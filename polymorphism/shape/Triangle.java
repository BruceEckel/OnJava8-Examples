//: polymorphism/shape/Triangle.java
// ©2015 MindView LLC: see Copyright.txt
package polymorphism.shape;
import static net.mindview.util.Print.*;

public class Triangle extends Shape {
  @Override
  public void draw() { print("Triangle.draw()"); }
  @Override
  public void erase() { print("Triangle.erase()"); }
} ///:~
