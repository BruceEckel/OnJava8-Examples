// enums/Rock.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
package enums;
import static enums.Outcome.*;

public class Rock implements Item {
  @Override public Outcome compete(Item it) {
    return it.eval(this);
  }
  @Override
  public Outcome eval(Paper p) { return WIN; }
  @Override
  public Outcome eval(Scissors s) { return LOSE; }
  @Override
  public Outcome eval(Rock r) { return DRAW; }
  @Override public String toString() {
    return "Rock";
  }
}
