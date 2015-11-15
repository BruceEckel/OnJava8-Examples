// streams/Implementation2.java
// ©2016 MindView LLC: see Copyright.txt

public class Implementation2
implements InterfaceWithDefault {
  public void firstMethod() {
    System.out.println("firstMethod");
  }
  public void secondMethod() {
    System.out.println("secondMethod");
  }
  public static void main(String[] args) {
    InterfaceWithDefault i =
      new Implementation2();
    i.firstMethod();
    i.secondMethod();
    i.newMethod();
  }
}
/* Output:
firstMethod
secondMethod
newMethod
*/
