// enumerations/SmartCasting.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 16

public class SmartCasting {
  static void dumb(Object x) {
    if(x instanceof String) {
      String s = (String)x;
      if(s.length() > 0) {
        System.out.format(
          "%d %s%n", s.length(), s.toUpperCase());
      }
    }
  }
  static void smart(Object x) {
    if(x instanceof String s && s.length() > 0) {
      System.out.format(
        "%d %s%n", s.length(), s.toUpperCase());
    }
  }
  static void wrong(Object x) {
    // "Or" never works:
    // if(x instanceof String s || s.length() > 0) {}
    // error: cannot find symbol   ^
  }
  public static void main(String[] args) {
    dumb("dumb");
    smart("smart");
  }
}
/* Output:
4 DUMB
5 SMART
*/
