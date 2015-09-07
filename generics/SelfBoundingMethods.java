// generics/SelfBoundingMethods.java
// ©2015 MindView LLC: see Copyright.txt

public class SelfBoundingMethods {
  static <T extends SelfBounded<T>> T f(T arg) {
    return arg.set(arg).get();
  }
  public static void main(String[] args) {
    A a = f(new A());
  }
}
/* Output: (None) */
