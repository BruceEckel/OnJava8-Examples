// streams/SelectElement.java
// ©2016 MindView LLC: see Copyright.txt
import java.util.*;
import java.util.stream.*;
import static streams.RandInts.*;

public class SelectElement {
  public static void main(String[] args) {
    System.out.println(rands().findFirst().getAsInt());
    System.out.println(
      rands().parallel().findFirst().getAsInt());
    System.out.println(rands().findAny().getAsInt());
    System.out.println(
      rands().parallel().findAny().getAsInt());
  }
}
/* Output:
258
258
258
242
*/
