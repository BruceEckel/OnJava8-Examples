// innerclasses/DotNew.java
// ©2015 MindView LLC: see Copyright.txt
// Creating an inner class directly using the .new syntax.

public class DotNew {
  public class Inner {}
  public static void main(String[] args) {
    DotNew dn = new DotNew();
    DotNew.Inner dni = dn.new Inner();
  }
}
/* Output: (None) */
