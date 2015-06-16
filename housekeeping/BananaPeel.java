//: housekeeping/BananaPeel.java
// ©2015 MindView LLC: see Copyright.txt

class Banana { void peel(int i) { /* ... */ } }

public class BananaPeel {
  public static void main(String[] args) {
    Banana a = new Banana(),
           b = new Banana();
    a.peel(1);
    b.peel(2);
  }
} /* Output: (None) *///:~
