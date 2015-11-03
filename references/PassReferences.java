// references/PassReferences.java
// Passing references around.

public class PassReferences {
  public static void f(PassReferences h) {
    System.out.println("h inside f(): " + h);
  }
  public static void main(String[] args) {
    PassReferences p = new PassReferences();
    System.out.println("p inside main(): " + p);
    f(p);
  }
}
/* Output:
p inside main(): PassReferences@19e0bfd
h inside f(): PassReferences@19e0bfd
*/
