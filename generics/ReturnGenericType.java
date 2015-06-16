//: generics/ReturnGenericType.java
// ©2015 MindView LLC: see Copyright.txt

class ReturnGenericType<T extends HasF> {
  private T obj;
  public ReturnGenericType(T x) { obj = x; }
  public T get() { return obj; }
} ///:~
