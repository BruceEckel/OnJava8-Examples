// streams/Machine2.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;
import onjava.Operation;

public class Machine2 {
  public static void main(String[] args) {
    Arrays.stream(new Operation[] {
      () -> Operation.show("Bing"),
      () -> Operation.show("Crack"),
      () -> Operation.show("Twist"),
      () -> Operation.show("Pop")
    }).forEach(Operation::execute);
  }
}
/* Output:
Bing
Crack
Twist
Pop
*/
