// onjava/Tuple4.java
package onjava;

public class Tuple4<A, B, C, D>
  extends Tuple3<A, B, C> {
  public final D _4;
  public Tuple4(A a, B b, C c, D d) {
    super(a, b, c);
    _4 = d;
  }
  @Override
  public String rep() {
    return super.rep() + ", " + _4;
  }
}
