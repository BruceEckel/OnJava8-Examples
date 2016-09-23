// functional/Closure4.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.function.*;

public class Closure4 {
  IntSupplier make_fun(final int x) {
    final int i = 0;
    return () -> x + i;
  }
}
