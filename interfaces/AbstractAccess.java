// interfaces/AbstractAccess.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.

abstract class AbstractAccess {
  private void m1() {}
  // private abstract void m1a(); // illegal
  protected void m2() {}
  protected abstract void m2a();
  void m3() {}
  abstract void m3a();
  public void m4() {}
  abstract public void m4a();
}
