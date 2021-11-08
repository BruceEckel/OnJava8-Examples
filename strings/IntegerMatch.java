// strings/IntegerMatch.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.stream.*;

public class IntegerMatch {
  public static void main(String[] args) {
    String possiblyMinus = "-?\\d+";
    Stream.of(
      "-1234".matches(possiblyMinus),
      "5678".matches(possiblyMinus),
      "+911".matches(possiblyMinus),
      "+911".matches("(-|\\+)?\\d+")
    ).forEach(System.out::println);
  }
}
/* Output:
true
true
false
true
*/
