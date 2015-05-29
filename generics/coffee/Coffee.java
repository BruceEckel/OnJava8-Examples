//: generics/coffee/Coffee.java
// ©2015 MindView LLC: see Copyright.txt
package generics.coffee;

public class Coffee {
  private static long counter = 0;
  private final long id = counter++;
  @Override
  public String toString() {
    return getClass().getSimpleName() + " " + id;
  }
} ///:~
