// exceptions/ExceptionSilencer.java
// ©2016 MindView LLC: see Copyright.txt

public class ExceptionSilencer {
  public static void main(String[] args) {
    try {
      throw new RuntimeException();
    } finally {
      // Using 'return' inside the finally block
      // will silence any thrown exception.
      return;
    }
  }
}
/* Output: (None) */
