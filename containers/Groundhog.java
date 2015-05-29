//: containers/Groundhog.java
// ©2015 MindView LLC: see Copyright.txt
// Looks plausible, but doesn't work as a HashMap key.

public class Groundhog {
  protected int number;
  public Groundhog(int n) { number = n; }
  @Override
  public String toString() {
    return "Groundhog #" + number;
  }
} ///:~
