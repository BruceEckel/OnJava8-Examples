// functional/AnonymousClosure.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.function.*;

public class AnonymousClosure {
  IntSupplier make_fun(int x) {
    int i = 0;
    // Same rules apply:
    // i++; // Not "effectively final"
    // x++; // Ditto
    return new IntSupplier() {
      public int getAsInt() { return x + i; }
    };
  }
}
