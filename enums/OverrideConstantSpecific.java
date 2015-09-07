// enums/OverrideConstantSpecific.java
// ©2015 MindView LLC: see Copyright.txt
import static com.mindviewinc.util.Print.*;

public enum OverrideConstantSpecific {
  NUT, BOLT,
  WASHER {
    @Override
    void f() { print("Overridden method"); }
  };
  void f() { print("default behavior"); }
  public static void main(String[] args) {
    for(OverrideConstantSpecific ocs : values()) {
      printnb(ocs + ": ");
      ocs.f();
    }
  }
}
/* Output:
NUT: default behavior
BOLT: default behavior
WASHER: Overridden method
*/
