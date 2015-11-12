// streams/Machine.java
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
