// polymorphism/PrivateOverride2.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Detecting a mistaken override using @Override
// {CompileTimeError} (Won't compile)
package polymorphism;

public class PrivateOverride2 {
  private void f() { System.out.println("private f()"); }
  public static void main(String[] args) {
    PrivateOverride2 po = new Derived2();
    po.f();
  }
}

class Derived2 extends PrivateOverride2 {
  @Override
  public void f() { System.out.println("public f()"); }
}
