// operators/PassObject.java
// ©2015 MindView LLC: see Copyright.txt
// Passing objects to methods might not be
// what you're used to.
import static com.mindviewinc.util.Print.*;

class Letter {
  char c;
}

public class PassObject {
  static void f(Letter y) {
    y.c = 'z';
  }
  public static void main(String[] args) {
    Letter x = new Letter();
    x.c = 'a';
    print("1: x.c: " + x.c);
    f(x);
    print("2: x.c: " + x.c);
  }
}
/* Output:
1: x.c: a
2: x.c: z
*/
