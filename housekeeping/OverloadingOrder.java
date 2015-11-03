// housekeeping/OverloadingOrder.java
// Overloading based on the order of the arguments.

public class OverloadingOrder {
  static void f(String s, int i) {
    System.out.println("String: " + s + ", int: " + i);
  }
  static void f(int i, String s) {
    System.out.println("int: " + i + ", String: " + s);
  }
  public static void main(String[] args) {
    f("String first", 11);
    f(99, "Int first");
  }
}
/* Output:
String: String first, int: 11
int: 99, String: Int first
*/
