// patterns/TemplateMethod.java
// ©2015 MindView LLC: see Copyright.txt
// Simple demonstration of Template Method.

abstract class ApplicationFramework {
  public ApplicationFramework() {
    templateMethod();
  }
  abstract void customize1();
  abstract void customize2();
  // "private" means automatically "final":
  private void templateMethod() {
    for(int i = 0; i < 5; i++) {
      customize1();
      customize2();
    }
  }
}

// Create a new "application":
class MyApp extends ApplicationFramework {
  @Override
  void customize1() {
    System.out.print("Hello ");
  }
  @Override
  void customize2() {
    System.out.println("World!");
  }
}

public class TemplateMethod {
  public static void main(String args[]) {
    new MyApp();
  }
}
/* Output:
Hello World!
Hello World!
Hello World!
Hello World!
Hello World!
*/
