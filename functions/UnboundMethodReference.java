// functions/UnboundMethodReference.java
// Method reference without an object.
import java.util.*;
import java.util.function.*;

class X {
  String f() { return "X.f()"; }
}

public class UnboundMethodReference {
  public static void main(String[] args) {
    Function<String, Integer> len = String::length;
    System.out.println(len.apply("UnboundMethodReference"));

    List<String> words =
      Arrays.asList("Rain", "Spain", "Plain");
    words.forEach(System.out::println);

    Function<X, String> xfr = X::f;
    System.out.println(xfr.apply(new X()));
  }
}
/* Output:
22
Rain
Spain
Plain
X.f()
*/
