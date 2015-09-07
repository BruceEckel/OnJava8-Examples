// hiding/Cake.java
// ©2015 MindView LLC: see Copyright.txt
// Accesses a class in a separate compilation unit.

class Cake {
  public static void main(String[] args) {
    Pie x = new Pie();
    x.f();
  }
}
/* Output:
Pie.f()
*/
