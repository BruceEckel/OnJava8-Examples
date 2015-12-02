// functional/Closure7.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// {CompileTimeError} (Won't compile)
import java.util.function.*;

public class Closure7 {
  IntSupplier make_fun(int x) {
    Integer i = new Integer(0);
    i = i + 1;
    return () -> x + i;
  }
}
