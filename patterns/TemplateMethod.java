// patterns/TemplateMethod.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Basic Template Method pattern.
import java.util.stream.*;

abstract class ApplicationFramework {
  ApplicationFramework() {
    templateMethod();
  }
  abstract void customize1(int n);
  abstract void customize2(int n);
  // "private" means automatically "final":
  private void templateMethod() {
    IntStream.range(0, 5).forEach(
      n -> { customize1(n); customize2(n); });
  }
}

// Create a new application:
class MyApp extends ApplicationFramework {
  @Override void customize1(int n) {
    System.out.print("customize1 " + n);
  }
  @Override void customize2(int n) {
    System.out.println(" customize2 " + n);
  }
}

public class TemplateMethod {
  public static void main(String[] args) {
    new MyApp();
  }
}
/* Output:
customize1 0 customize2 0
customize1 1 customize2 1
customize1 2 customize2 2
customize1 3 customize2 3
customize1 4 customize2 4
*/

// my code
abstract class MyTemplate {
  MyTemplate() {
    init();
  }
  // template method are private
  private void init() {
    foo();
    bar();
    System.out.println("App has initialized");
  }
  abstract void foo();
  abstract void bar();
}

class App extends MyTemplate {

  @Override
  void foo() {
    System.out.println("foo method");
  }

  @Override
  void bar() {
    System.out.println("bar method");
  }

  public static void main(String[] args) {
    new App();
  }
}
