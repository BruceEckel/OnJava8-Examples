// innerclasses/RunnableMethodReference.java
// ©2015 MindView LLC: see Copyright.txt
// Demonstrates method references
import static com.mindviewinc.util.Print.*;

class External {
  static void go() { print("External.go()"); }
}

public class RunnableMethodReference {
  Runnable robject, rstatic, rexstatic;
  void f() { print("f()"); }
  static void g() { print("g()"); }
  public void assign() {
    robject = this::f;
    rstatic = RunnableMethodReference::g;
    rexstatic = External::go;
  }
  public void call() {
    robject.run();
    rstatic.run();
    rexstatic.run();
  }
  public static void main(String[] args) {
    RunnableMethodReference rmr =
      new RunnableMethodReference();
    rmr.assign();
    rmr.call();
    rmr.robject = rmr::f;
    rmr.robject.run();
  }
}
/* Output:
f()
g()
External.go()
f()
*/
