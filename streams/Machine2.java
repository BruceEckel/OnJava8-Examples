// streams/Machine2.java
// ©2016 MindView LLC: see Copyright.txt
import java.util.*;

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
