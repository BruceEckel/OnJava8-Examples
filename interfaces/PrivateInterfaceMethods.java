// interfaces/PrivateInterfaceMethods.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 9

interface Old {
  default void fd() {
    System.out.println("Old::fd()");
  }
  static void fs() {
    System.out.println("Old::fs()");
  }
  default void f() {
    fd();
  }
  static void g() {
    fs();
  }
}

class ImplOld implements Old {}

interface JDK9 {
  private void fd() { // Automatically default
    System.out.println("JDK9::fd()");
  }
  private static void fs() {
    System.out.println("JDK9::fs()");
  }
  default void f() {
    fd();
  }
  static void g() {
    fs();
  }
}

class ImplJDK9 implements JDK9 {}

public class PrivateInterfaceMethods {
  public static void main(String[] args) {
    new ImplOld().f();
    Old.g();
    new ImplJDK9().f();
    JDK9.g();
  }
}
/* Output:
Old::fd()
Old::fs()
JDK9::fd()
JDK9::fs()
*/
