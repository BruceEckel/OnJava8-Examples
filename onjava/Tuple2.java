// onjava/Tuple2.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
package onjava;

public class Tuple2<A, B> {
  public final A _1;
  public final B _2;
  public Tuple2(A a, B b) { _1 = a; _2 = b; }
  public String rep() { return  _1 + ", " + _2; }
  @Override
  public String toString() {
    return "(" + rep() + ")";
  }
}
