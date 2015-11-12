// streams/Machine2.java
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
