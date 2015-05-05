//: containers/Groundhog.java
// Looks plausible, but doesn't work as a HashMap key.

public class Groundhog {
  protected int number;
  public Groundhog(int n) { number = n; }
  @Override
  public String toString() {
    return "Groundhog #" + number;
  }
} ///:~
