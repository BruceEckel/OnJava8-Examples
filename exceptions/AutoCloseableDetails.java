// exceptions/AutoCloseableDetails.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.

class Reporter implements AutoCloseable {
  String name = getClass().getSimpleName();
  public Reporter() {
    System.out.println("Creating " + name);
  }
  public void close() {
    System.out.println("Closing " + name);
  }
}

class First extends Reporter {}
class Second extends Reporter {}

public class AutoCloseableDetails {
  public static void main(String[] args) {
    try(First f = new First();
        Second s = new Second()) {
    }
  }
}
/* Output:
Creating First
Creating Second
Closing Second
Closing First
*/
