//: references/Stringer.java
// ©2015 MindView LLC: see Copyright.txt

public class Stringer {
  public static String upcase(String s) {
    return s.toUpperCase();
  }
  public static void main(String[] args) {
    String q = new String("howdy");
    System.out.println(q); // howdy
    String qq = upcase(q);
    System.out.println(qq); // HOWDY
    System.out.println(q); // howdy
  }
} /* Output:
howdy
HOWDY
howdy
*///:~
