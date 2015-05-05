//: assertions/Assert2.java
// Assert with an informative message
// {JVMArgs: -ea}
// {ThrowsException}

public class Assert2 {
  public static void main(String[] args) {
    assert false: "Here's a message saying what happened";
  }
} ///:~
