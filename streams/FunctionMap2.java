// streams/FunctionMap2.java
// Different input and output types
import java.util.*;
import java.util.stream.*;

class Numbered {
  final int n;
  Numbered(int n) { this.n = n; }
  @Override
  public String toString() {
    return "Numbered(" + n + ")";
  }
}

class FunctionMap2 {
  public static void main(String[] args) {
    Stream.of(1, 5, 7, 9, 11, 13)
      .map(Numbered::new)
      .forEach(System.out::println);
  }
}
/* Output:
Numbered(1)
Numbered(5)
Numbered(7)
Numbered(9)
Numbered(11)
Numbered(13)
*/
