//: control/WhileTest.java
// ©2015 MindView LLC: see Copyright.txt
// Demonstrates the while loop.
// {OutputFirstAndLast: 5 Lines}

public class WhileTest {
  static boolean condition() {
    boolean result = Math.random() < 0.99;
    System.out.print(result + ", ");
    return result;
  }
  public static void main(String[] args) {
    while(condition())
      System.out.println("Inside 'while'");
    System.out.println("Exited 'while'");
  }
} ///:~
