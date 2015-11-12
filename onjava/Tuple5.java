// onjava/Tuple5.java
package onjava;

public class Tuple5<A, B, C, D, E>
extends Tuple4<A, B, C, D> {
  public final E _5;
  public Tuple5(A a, B b, C c, D d, E e) {
    super(a, b, c, d);
    _5 = e;
  }
  @Override
  public String rep() {
    return super.rep() + ", " + _5;
  }
}
