// polymorphism/shape/Circle.java
// ©2016 MindView LLC: see Copyright.txt
package polymorphism.shape;

public class Circle extends Shape {
  @Override
  public void draw() { System.out.println("Circle.draw()"); }
  @Override
  public void erase() { System.out.println("Circle.erase()"); }
}
