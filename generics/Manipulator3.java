// generics/Manipulator3.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.

class Manipulator3 {
  private HasF obj;
  public Manipulator3(HasF x) { obj = x; }
  public void manipulate() { obj.f(); }
}
