// containersindepth/Groundhog2.java
// ©2015 MindView LLC: see Copyright.txt
// A class that's used as a key in a HashMap
// must override hashCode() and equals().

public class Groundhog2 extends Groundhog {
  public Groundhog2(int n) { super(n); }
  @Override
  public int hashCode() { return number; }
  @Override
  public boolean equals(Object o) {
    return o instanceof Groundhog2 &&
      (number == ((Groundhog2)o).number);
  }
}
