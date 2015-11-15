// exceptions/NeverCaught.java
// ©2016 MindView LLC: see Copyright.txt
// Ignoring RuntimeExceptions.
// {ThrowsException}

public class NeverCaught {
  static void f() {
    throw new RuntimeException("From f()");
  }
  static void g() {
    f();
  }
  public static void main(String[] args) {
    g();
  }
}
/* Output:
___[ Error Output ]___
Exception in thread "main" java.lang.RuntimeException: From
f()
        at NeverCaught.f(NeverCaught.java:7)
        at NeverCaught.g(NeverCaught.java:10)
        at NeverCaught.main(NeverCaught.java:13)
___[ Exception is Expected ]___
*/
