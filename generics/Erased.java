// generics/Erased.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// {CompileTimeError} (Won't compile)

public class Erased<T> {
  private final int SIZE = 100;
  public static void f(Object arg) {
    if(arg instanceof T) {}          // Error
    T var = new T();                 // Error
    T[] array = new T[SIZE];         // Error
    T[] array = (T)new Object[SIZE]; // Unchecked warning
  }
}
