// streams/OptionalBasics.java
// ©2016 MindView LLC: see Copyright.txt
import java.util.*;
import java.util.stream.*;

class OptionalBasics {
  static void test(Optional<String> optString) {
    if(optString.isPresent())
      System.out.println(optString.get());
    else
      System.out.println("Nothing inside!");
  }
  public static void main(String[] args) {
    test(Stream.of("Epithets").findFirst());
    test(Stream.<String>empty().findFirst());
  }
}
/* Output:
Epithets
Nothing inside!
*/
