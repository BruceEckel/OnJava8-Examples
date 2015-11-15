// streams/Machine.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;

class Bing implements Operation {
  public void execute() {
    Operation.show("Bing");
  }
}

public class Machine {
  public static void main(String[] args) {
    Operation.runOps(Arrays.asList(
      new Bing(),
      new Operation() {
        public void execute() {
          Operation.show("Anon");
        }
      },
      () -> Operation.show("Crack"),
      () -> Operation.show("Twist"),
      () -> Operation.show("Pop")
    ));
  }
}
/* Output:
Bing
Anon
Crack
Twist
Pop
*/
