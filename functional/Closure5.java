// functional/Closure5.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {CompileTimeError} (Will not compile)
import java.util.function.*;

public class Closure5 {
  IntSupplier makeFun(int x) {
    int i = 0;
    i++;
    x++;
    return () -> x + i;
  }
}
