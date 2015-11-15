// streams/Bubbles.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.stream.*;

public class Bubbles {
  public static void main(String[] args) {
    Stream.generate(Bubble.factory)
      .limit(10)
      .forEach(System.out::println);
  }
}
/* Output:
Bubble(0)
Bubble(1)
Bubble(2)
Bubble(3)
Bubble(4)
Bubble(5)
Bubble(6)
Bubble(7)
Bubble(8)
Bubble(9)
*/
