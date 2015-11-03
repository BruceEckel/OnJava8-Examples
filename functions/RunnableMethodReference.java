// functions/RunnableMethodReference.java
// Demonstrates method references

class External {
  static void go() { System.out.println("External.go()"); }
}

public class RunnableMethodReference {
  Runnable robject, rstatic, rexstatic;
  void f() { System.out.println("f()"); }
  static void g() { System.out.println("g()"); }
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
