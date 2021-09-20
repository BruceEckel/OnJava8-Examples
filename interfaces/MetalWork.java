// interfaces/MetalWork.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import onjava.Operation;

class Heat implements Operation {
  @Override public void execute() {
    Operation.show("Heat");
  }
}

public class MetalWork {
  public static void main(String[] args) {
    // Must be defined in a static context
    // to access a method reference:
    Operation twist = new Operation() {
      public void execute() {
        Operation.show("Twist");
      }
    };
    Operation.runOps(
      new Heat(),                     // [1]
      new Operation() {               // [2]
        public void execute() {
          Operation.show("Hammer");
        }
      },
      twist::execute,                 // [3]
      () -> Operation.show("Anneal")  // [4]
    );
  }
}
/* Output:
Heat
Hammer
Twist
Anneal
*/
