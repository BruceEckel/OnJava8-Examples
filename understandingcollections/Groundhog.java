// understandingcollections/Groundhog.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Looks plausible, but doesn't work as a HashMap key

public class Groundhog {
  protected int number;
  public Groundhog(int n) { number = n; }
  @Override
  public String toString() {
    return "Groundhog #" + number;
  }
}
