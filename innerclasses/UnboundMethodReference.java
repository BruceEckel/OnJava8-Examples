// innerclasses/UnboundMethodReference.java
// ©2015 MindView LLC: see Copyright.txt
// Method reference without an object.
import java.util.*;
import java.util.function.*;
import static com.mindviewinc.util.Print.*;

class X {
  String f() { return "X.f()"; }
}

public class UnboundMethodReference {
  public static void main(String[] args) {
    Function<String, Integer> len = String::length;
    print(len.apply("UnboundMethodReference"));

    List<String> words =
      Arrays.asList("Rain", "Spain", "Plain");
    words.forEach(System.out::println);

    Function<X, String> xfr = X::f;
    print(xfr.apply(new X()));
  }
}
/* Output:
22
Rain
Spain
Plain
X.f()
*/
