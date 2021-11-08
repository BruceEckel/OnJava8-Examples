// enumerations/OddScoping.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 16

public class OddScoping {
  static void f(Object o) {
    if(!(o instanceof String s)) {
      System.out.println("Not a String");
      throw new RuntimeException();
    }
    // s is in scope here!
    System.out.println(s.toUpperCase());  // [1]
  }
  public static void main(String[] args) {
    f("Curiouser and Curiouser");
    f(null);
  }
}
/* Output:
CURIOUSER AND CURIOUSER
Not a String
Exception in thread "main" java.lang.RuntimeException
        at OddScoping.f(OddScoping.java:8)
        at OddScoping.main(OddScoping.java:15)
*/
