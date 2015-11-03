// functions/CtorReference.java
// Demonstrates java.util.function
import java.util.function.*;

public class CtorReference {
  public CtorReference() {
    System.out.println("Inside CtorReference()");
  }
  public CtorReference(int i) {
    System.out.println("Inside CtorReference(i)");
  }
  public CtorReference(int i, double d) {
    System.out.println("Inside CtorReference(i, d)");
  }
  public static void main(String[] args) {
    Supplier<CtorReference> cr0 =
      CtorReference::new;
    CtorReference r0 = cr0.get();

    Function<Integer, CtorReference> cr1 =
      CtorReference::new;
    CtorReference r1 = cr1.apply(1);

    BiFunction<Integer, Double, CtorReference> cr2 =
      CtorReference::new;
    CtorReference r2 = cr2.apply(1, 2.0);
  }
}
/* Output:
Inside CtorReference()
Inside CtorReference(i)
Inside CtorReference(i, d)
*/
