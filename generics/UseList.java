//: generics/UseList.java
// {CompileTimeError} (Will not compile)
import java.util.*;

public class UseList<W,T> {
  void f(List<T> v) {}
  void f(List<W> v) {}
} ///:~