// references/Alias1.java
// ©2015 MindView LLC: see Copyright.txt
// Aliasing two references to one object.

public class Alias1 {
  private int i;
  public Alias1(int ii) { i = ii; }
  public static void main(String[] args) {
    Alias1 x = new Alias1(7);
    Alias1 y = x; // Assign the reference
    System.out.println("x: " + x.i);
    System.out.println("y: " + y.i);
    System.out.println("Incrementing x");
    x.i++;
    System.out.println("x: " + x.i);
    System.out.println("y: " + y.i);
  }
}
/* Output:
x: 7
y: 7
Incrementing x
x: 8
y: 8
*/
