// enumerations/Dominance.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Preview in JDK 17
// Compile with javac flags:
//   --enable-preview --source 17
import java.util.*;

sealed interface Base {}
record Derived() implements Base {}

public class Dominance {
  static String test(Base base) {
    return switch(base) {
      case Derived d -> "Derived";
      case Base b -> "B";            // [1]
    };
  }
}
