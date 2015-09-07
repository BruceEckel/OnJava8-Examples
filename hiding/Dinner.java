// hiding/Dinner.java
// ©2015 MindView LLC: see Copyright.txt
// Uses the library.
import hiding.dessert.*;

public class Dinner {
  public static void main(String[] args) {
    Cookie x = new Cookie();
    //! x.bite(); // Can't access
  }
}
/* Output:
Cookie constructor
*/
