//: typeinfo/packageaccess/HiddenC.java
// ©2015 MindView LLC: see Copyright.txt
package typeinfo.packageaccess;
import typeinfo.interfacea.*;
import static com.mindviewinc.util.Print.*;

class C implements A {
  @Override
  public void f() { print("public C.f()"); }
  public void g() { print("public C.g()"); }
  void u() { print("package C.u()"); }
  protected void v() { print("protected C.v()"); }
  private void w() { print("private C.w()"); }
}

public class HiddenC {
  public static A makeA() { return new C(); }
} ///:~
