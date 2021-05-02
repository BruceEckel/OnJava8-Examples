// reuse/Beetle.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// The full process of initialization

class Insect {
  private int i = 9; // order3 order4
  protected int j; // order3
  Insect() { // order5
    System.out.println("i = " + i + ", j = " + j);
    j = 39;
  }
   final int f = 11;
  // order1
  private static int x1 =
    printInit("static Insect.x1 initialized");
  static int printInit(String s) {
    System.out.println(s);
    return 47;
  }
}

public class Beetle extends Insect {
  private int k = printInit("Beetle.k initialized"); // order3 order6
  public Beetle() { // order7
    System.out.println("k = " + k);
    System.out.println("j = " + j);
  }
  static final int ff = 22;
  // order2
  private static int x2 =
    printInit("static Beetle.x2 initialized");
  public static void main(String[] args) {
    System.out.println("Beetle constructor");
    Beetle b = new Beetle();
  }
}
/* Output:
static Insect.x1 initialized
static Beetle.x2 initialized
Beetle constructor
i = 9, j = 0
Beetle.k initialized
k = 47
j = 39
*/


