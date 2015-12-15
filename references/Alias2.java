// references/Alias2.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Method calls implicitly alias their arguments.

public class Alias2 {
  private int i;
  public Alias2(int ii) { i = ii; }
  public static void f(Alias2 reference) { reference.i++; }
  public static void main(String[] args) {
    Alias2 x = new Alias2(7);
    System.out.println("x: " + x.i);
    System.out.println("Calling f(x)");
    f(x);
    System.out.println("x: " + x.i);
  }
}
/* Output:
x: 7
Calling f(x)
x: 8
*/
