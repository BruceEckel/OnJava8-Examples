//: generics/SimpleHolder.java
// ©2015 MindView LLC: see Copyright.txt

public class SimpleHolder {
  private Object obj;
  public void set(Object obj) { this.obj = obj; }
  public Object get() { return obj; }
  public static void main(String[] args) {
    SimpleHolder holder = new SimpleHolder();
    holder.set("Item");
    String s = (String)holder.get();
  }
} /* Output: (None) *///:~
