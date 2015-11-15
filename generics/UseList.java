// generics/UseList.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// {CompileTimeError} (Will not compile)

public class UseList<W,T> {
  void f(List<T> v) {}
  void f(List<W> v) {}
}
