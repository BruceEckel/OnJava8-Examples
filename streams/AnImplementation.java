// streams/AnImplementation.java
// ©2016 MindView LLC: see Copyright.txt

public class AnImplementation implements AnInterface {
  public void firstMethod() {
    System.out.println("firstMethod");
  }
  public void secondMethod() {
    System.out.println("secondMethod");
  }
  public static void main(String[] args) {
    AnInterface i = new AnImplementation();
    i.firstMethod();
    i.secondMethod();
  }
}
/* Output:
firstMethod
secondMethod
*/
