// functional/Closure9.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {CompileTimeError} (Will not compile)
import java.util.*;
import java.util.function.*;

public class Closure9 {
  Supplier<List<Integer>> makeFun() {
    List<Integer> ai = new ArrayList<>();
    ai = new ArrayList<>(); // Reassignment
    return () -> ai;
  }
}
