// strings/Immutable.java
// ©2016 MindView LLC: see Copyright.txt

public class Immutable {
  public static String upcase(String s) {
    return s.toUpperCase();
  }
  public static void main(String[] args) {
    String q = "howdy";
    System.out.println(q); // howdy
    String qq = upcase(q);
    System.out.println(qq); // HOWDY
    System.out.println(q); // howdy
  }
}
/* Output:
howdy
HOWDY
howdy
*/
