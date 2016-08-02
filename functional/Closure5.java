// functional/Closure5.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// {CompileTimeError} (Will not compile)
import java.util.function.*;

public class Closure5 {
  IntSupplier make_fun(int x) {
    int i = 0;
    i++;
    x++;
    return () -> x + i;
  }
}
