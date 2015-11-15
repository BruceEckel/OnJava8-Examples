// onjava/Tuple3.java
// ©2016 MindView LLC: see Copyright.txt
package onjava;

public class Tuple3<A,B,C> extends Tuple2<A, B> {
  public final C _3;
  public Tuple3(A a, B b, C c) {
    super(a, b);
    _3 = c;
  }
  @Override
  public String rep() {
    return super.rep() + ", " + _3;
  }
}
