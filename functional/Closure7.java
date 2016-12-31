// functional/Closure7.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {CompileTimeError} (Will not compile)
import java.util.function.*;

public class Closure7 {
  IntSupplier makeFun(int x) {
    Integer i = new Integer(0);
    i = i + 1;
    return () -> x + i;
  }
}
