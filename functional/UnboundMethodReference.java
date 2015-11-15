// functional/UnboundMethodReference.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
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
