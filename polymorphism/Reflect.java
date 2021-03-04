// polymorphism/Reflect.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {ThrowsException}

class Useful {
  public void f() {}
  public void g() {}
}

class MoreUseful extends Useful {
  @Override public void f() {}
  @Override public void g() {}
  public void u() {}
  public void v() {}
  public void w() {}
}

public class Reflect {
  public static void main(String[] args) {
    Useful[] x = {
      new Useful(),
      new MoreUseful()
    };
    x[0].f();
    x[1].g();
    // Compile time: method not found in Useful:
    //- x[1].u();
    ((MoreUseful)x[1]).u(); // Downcast/Reflect
    ((MoreUseful)x[0]).u(); // Exception thrown
  }
}
/* Output:
___[ Error Output ]___
Exception in thread "main"
java.lang.ClassCastException: Useful cannot be cast to
MoreUseful
        at Reflect.main(Reflect.java:28)
*/
