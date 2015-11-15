// streams/StreamOfOptionals.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;
import java.util.stream.*;

public class StreamOfOptionals {
  public static void main(String[] args) {
    Signal.stream()
      .limit(10)
      .forEach(System.out::println);
    System.out.println(" ---");
    Signal.stream()
      .limit(10)
      .filter(Optional::isPresent)
      .map(Optional::get)
      .forEach(System.out::println);
  }
}
/* Output:
Optional[Signal(dash)]
Optional[Signal(dot)]
Optional[Signal(dash)]
Optional.empty
Optional.empty
Optional[Signal(dash)]
Optional.empty
Optional[Signal(dot)]
Optional[Signal(dash)]
Optional[Signal(dash)]
 ---
Signal(dash)
Signal(dot)
Signal(dash)
Signal(dash)
Signal(dot)
Signal(dash)
Signal(dash)
*/
