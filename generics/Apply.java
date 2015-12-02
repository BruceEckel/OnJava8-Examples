// generics/Apply.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// {main: ApplyTest}
import java.lang.reflect.*;
import java.util.*;

public class Apply {
  public static <T, S extends Iterable<? extends T>>
  void apply(S seq, Method f, Object... args) {
    try {
      for(T t: seq)
        f.invoke(t, args);
    } catch(IllegalAccessException |
            IllegalArgumentException |
            InvocationTargetException e) {
      // Failures are programmer errors
      throw new RuntimeException(e);
    }
  }
}

class Shape {
  public void rotate() {
    System.out.println(this + " rotate");
  }
  public void resize(int newSize) {
    System.out.println(this + " resize " + newSize);
  }
}

class Square extends Shape {}

class FilledList<T> extends ArrayList<T> {
  public FilledList(Class<? extends T> type, int size) {
    try {
      for(int i = 0; i < size; i++)
        // Assumes no-arg constructor:
        add(type.newInstance());
    } catch(InstantiationException |
            IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
}

class ApplyTest {
  public static void main(String[] args) throws Exception {
    List<Shape> shapes = new ArrayList<>();
    for(int i = 0; i < 10; i++)
      shapes.add(new Shape());
    Apply.apply(shapes, Shape.class.getMethod("rotate"));
    Apply.apply(shapes,
      Shape.class.getMethod("resize", int.class), 5);
    List<Square> squares = new ArrayList<>();
    for(int i = 0; i < 10; i++)
      squares.add(new Square());
    Apply.apply(squares, Shape.class.getMethod("rotate"));
    Apply.apply(squares,
      Shape.class.getMethod("resize", int.class), 5);

    Apply.apply(new FilledList<>(Shape.class, 10),
      Shape.class.getMethod("rotate"));
    Apply.apply(new FilledList<>(Square.class, 10),
      Shape.class.getMethod("rotate"));

    SimpleQueue<Shape> shapeQ = new SimpleQueue<>();
    for(int i = 0; i < 5; i++) {
      shapeQ.add(new Shape());
      shapeQ.add(new Square());
    }
    Apply.apply(shapeQ, Shape.class.getMethod("rotate"));
  }
}
/* Output: (First and last 10 Lines)
Shape@139a55 rotate
Shape@106d69c rotate
Shape@52e922 rotate
Shape@25154f rotate
Shape@10dea4e rotate
Shape@647e05 rotate
Shape@1909752 rotate
Shape@1f96302 rotate
Shape@14eac69 rotate
Shape@a57993 rotate
________...________...________...________...________
Shape@d3c617 rotate
Square@1947c6b rotate
Shape@1193f2d rotate
Square@15da254 rotate
Shape@1c63996 rotate
Square@1716361 rotate
Shape@1f3c5b5 rotate
Square@164951d rotate
Shape@11210ee rotate
Square@17e1886 rotate
*/
