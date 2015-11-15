// streams/Optionals.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Optionals {
  static void basics(Optional<String> optString) {
    if(optString.isPresent())
      System.out.println(optString.get());
    else
      System.out.println("Nothing inside!");
  }
  static void if_present(Optional<String> optString) {
    optString.ifPresent(System.out::println);
  }
  static void or_else(Optional<String> optString) {
    System.out.println(optString.orElse("Nada"));
  }
  static void or_else_get(Optional<String> optString) {
    System.out.println(
      optString.orElseGet(() -> "Generated"));
  }
  static void or_else_throw(Optional<String> optString) {
    try {
      System.out.println(optString.orElseThrow(
          () -> new Exception("Supplied")));
    } catch(Exception e) {
      System.out.println("Caught " + e);
    }
  }
  static void
  test(String testName, Consumer<Optional<String>> cos) {
    System.out.println(" === " + testName + " === ");
    cos.accept(Stream.of("Epithets").findFirst());
    cos.accept(Stream.<String>empty().findFirst());
  }
  public static void main(String[] args) {
    test("basics", Optionals::basics);
    test("ifPresent", Optionals::if_present);
    test("orElse", Optionals::or_else);
    test("orElseGet", Optionals::or_else_get);
    test("orElseThrow", Optionals::or_else_throw);
  }
}
/* Output:
 === basics ===
Epithets
Nothing inside!
 === ifPresent ===
Epithets
 === orElse ===
Epithets
Nada
 === orElseGet ===
Epithets
Generated
 === orElseThrow ===
Epithets
Caught java.lang.Exception: Supplied
*/
