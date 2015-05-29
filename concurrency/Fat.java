//: concurrency/Fat.java
// ©2015 MindView LLC: see Copyright.txt
// Objects that are expensive to create.

public class Fat {
  private volatile double d; // Prevent optimization
  private static int counter = 0;
  private final int id = counter++;
  public Fat() {
    // Expensive, interruptible operation:
    for(int i = 1; i < 10000; i++) {
      d += (Math.PI + Math.E) / (double)i;
    }
  }
  public void operation() { System.out.println(this); }
  @Override
  public String toString() { return "Fat id: " + id; }
} ///:~
