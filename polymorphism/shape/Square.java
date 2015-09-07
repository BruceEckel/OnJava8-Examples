// polymorphism/shape/Square.java
// ©2015 MindView LLC: see Copyright.txt
package polymorphism.shape;
import static com.mindviewinc.util.Print.*;

public class Square extends Shape {
  @Override
  public void draw() { print("Square.draw()"); }
  @Override
  public void erase() { print("Square.erase()"); }
}
