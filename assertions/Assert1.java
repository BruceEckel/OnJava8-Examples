//: assertions/Assert1.java
// Non-informative style of assert
// Compile with: javac -source 1.4 Assert1.java
// {JVMArgs: -ea} // Must run with -ea
// {ThrowsException}

public class Assert1 {
  public static void main(String[] args) {
    assert false;
  }
} ///:~
