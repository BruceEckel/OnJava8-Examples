// patterns/CommandPattern.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.*;

class Command {
  public final String msg;
  public Command(String msg) {
    this.msg = msg;
  }
}

public class CommandPattern {
  public static void show(Command cmd) {
    System.out.println(cmd.msg);
  }
  public static void main(String[] args) {
    show(new Command("First Command"));
    show(new Command("Second Command"));
  }
}
/* Output:
First Command
Second Command
*/
