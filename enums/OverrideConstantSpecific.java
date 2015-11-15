// enums/OverrideConstantSpecific.java
// ©2016 MindView LLC: see Copyright.txt

public enum OverrideConstantSpecific {
  NUT, BOLT,
  WASHER {
    @Override
    void f() { System.out.println("Overridden method"); }
  };
  void f() { System.out.println("default behavior"); }
  public static void main(String[] args) {
    for(OverrideConstantSpecific ocs : values()) {
      System.out.print(ocs + ": ");
      ocs.f();
    }
  }
}
/* Output:
NUT: default behavior
BOLT: default behavior
WASHER: Overridden method
*/
