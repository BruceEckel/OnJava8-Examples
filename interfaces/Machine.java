// interfaces/Machine.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;
import onjava.Operation;

class Bing implements Operation {
  public void execute() {
    Operation.show("Bing");
  }
}

class Crack implements Operation {
  public void execute() {
    Operation.show("Crack");
  }
}

class Twist implements Operation {
  public void execute() {
    Operation.show("Twist");
  }
}

public class Machine {
  public static void main(String[] args) {
    Operation.runOps(
      new Bing(), new Crack(), new Twist());
  }
}
/* Output:
Bing
Crack
Twist
*/
