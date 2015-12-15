// functional/Closure6.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.function.*;

public class Closure6 {
  IntSupplier make_fun(int x) {
    int i = 0;
    i++;
    x++;
    final int i_final = i;
    final int x_final = x;
    return () -> x_final + i_final;
  }
}
