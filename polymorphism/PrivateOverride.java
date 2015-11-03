// polymorphism/PrivateOverride.java
// Trying to override a private method.
package polymorphism;

public class PrivateOverride {
  private void f() { System.out.println("private f()"); }
  public static void main(String[] args) {
    PrivateOverride po = new Derived();
    po.f();
  }
}

class Derived extends PrivateOverride {
  public void f() { System.out.println("public f()"); }
}
/* Output:
private f()
*/
