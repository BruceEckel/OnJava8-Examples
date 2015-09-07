// polymorphism/PrivateOverride.java
// ©2015 MindView LLC: see Copyright.txt
// Trying to override a private method.
package polymorphism;
import static com.mindviewinc.util.Print.*;

public class PrivateOverride {
  private void f() { print("private f()"); }
  public static void main(String[] args) {
    PrivateOverride po = new Derived();
    po.f();
  }
}

class Derived extends PrivateOverride {
  public void f() { print("public f()"); }
}
/* Output:
private f()
*/
