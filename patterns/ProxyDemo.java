// patterns/ProxyDemo.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Basic demonstration of the Proxy pattern.

interface ProxyBase {
  void f();
  void g();
  void h();
}

class Proxy implements ProxyBase {
  private ProxyBase implementation =
    new Implementation();
  // Pass method calls to the implementation:
  @Override
  public void f() { implementation.f(); }
  @Override
  public void g() { implementation.g(); }
  @Override
  public void h() { implementation.h(); }
}

class Implementation implements ProxyBase {
  @Override public void f() {
    System.out.println("Implementation.f()");
  }
  @Override public void g() {
    System.out.println("Implementation.g()");
  }
  @Override public void h() {
    System.out.println("Implementation.h()");
  }
}

public class ProxyDemo {
  public static void main(String[] args) {
    Proxy p = new Proxy();
    p.f();
    p.g();
    p.h();
  }
}
/* Output:
Implementation.f()
Implementation.g()
Implementation.h()
*/

// my code
interface MyBaseProxy {
  void foo();
  void bar();
}

class MyProxy implements MyBaseProxy {

  private MyImpl impl;
  MyProxy() { impl = new MyImpl(); }
  @Override
  public void foo() {
    impl.foo();
  }

  @Override
  public void bar() {
    impl.bar();
  }
}

class MyImpl implements MyBaseProxy {

  @Override
  public void foo() {
    System.out.println("impl foo()");
  }

  @Override
  public void bar() {
    System.out.println("impl bar()");
  }

  public static void main(String[] args) {
    MyProxy proxy = new MyProxy();
    proxy.bar();
    proxy.foo();
  }
}
