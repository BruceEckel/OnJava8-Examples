// generics/Manipulation.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {CompileTimeError} (Will not compile)

class Manipulator<T> {
  private T obj;
  public Manipulator(T x) { obj = x; }
  // Error: cannot find symbol: method f():
  public void manipulate() { obj.f(); }
}

public class Manipulation {
  public static void main(String[] args) {
    HasF hf = new HasF();
    Manipulator<HasF> manipulator =
      new Manipulator<>(hf);
    manipulator.manipulate();
  }
}
