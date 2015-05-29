//: generics/Manipulator2.java
// ©2015 MindView LLC: see Copyright.txt

class Manipulator2<T extends HasF> {
  private T obj;
  public Manipulator2(T x) { obj = x; }
  public void manipulate() { obj.f(); }
} ///:~
