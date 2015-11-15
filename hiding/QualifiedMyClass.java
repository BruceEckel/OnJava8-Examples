// hiding/QualifiedMyClass.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.

public class QualifiedMyClass {
  public static void main(String[] args) {
    hiding.mypackage.MyClass m =
      new hiding.mypackage.MyClass();
  }
}
/* Output: (None) */
