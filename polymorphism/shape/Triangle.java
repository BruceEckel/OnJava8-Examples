// polymorphism/shape/Triangle.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
package polymorphism.shape;

public class Triangle extends Shape {
  @Override
  public void draw() {
    System.out.println("Triangle.draw()");
  }
  @Override
  public void erase() {
    System.out.println("Triangle.erase()");
  }
}
