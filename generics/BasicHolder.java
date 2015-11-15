// generics/BasicHolder.java
// ©2016 MindView LLC: see Copyright.txt

public class BasicHolder<T> {
  T element;
  void set(T arg) { element = arg; }
  T get() { return element; }
  void f() {
    System.out.println(element.getClass().getSimpleName());
  }
}
