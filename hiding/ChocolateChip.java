// hiding/ChocolateChip.java
// ©2016 MindView LLC: see Copyright.txt
// Can't use package-access member from another package.
import hiding.dessert.*;

public class ChocolateChip extends Cookie {
  public ChocolateChip() {
   System.out.println("ChocolateChip constructor");
  }
  public void chomp() {
    //! bite(); // Can't access bite
  }
  public static void main(String[] args) {
    ChocolateChip x = new ChocolateChip();
    x.chomp();
  }
}
/* Output:
Cookie constructor
ChocolateChip constructor
*/
