//: initialization/MethodInit2.java
// ©2015 MindView LLC: see Copyright.txt
public class MethodInit2 {
  int i = f();
  int j = g(i);
  int f() { return 11; }
  int g(int n) { return n * 10; }
} ///:~
