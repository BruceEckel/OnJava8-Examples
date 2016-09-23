// enums/Burrito.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {java enums.Burrito}
package enums;
import static enums.Spiciness.*;

public class Burrito {
  Spiciness degree;
  public Burrito(Spiciness degree) {
    this.degree = degree;
  }
  @Override
  public String toString() {
    return "Burrito is "+ degree;
  }
  public static void main(String[] args) {
    System.out.println(new Burrito(NOT));
    System.out.println(new Burrito(MEDIUM));
    System.out.println(new Burrito(HOT));
  }
}
/* Output:
Burrito is NOT
Burrito is MEDIUM
Burrito is HOT
*/
