// patterns/CommandPattern.java
import java.util.*;

public class CommandPattern {
  public static void main(String args[]) {
    List<Runnable> macro = Arrays.asList(
      () -> System.out.print("Hello "),
      () -> System.out.print("World! "),
      () -> System.out.print("I'm the command pattern!")
    );
    macro.forEach(Runnable::run);
  }
}
/* Output:
Hello World! I'm the command pattern!
*/
