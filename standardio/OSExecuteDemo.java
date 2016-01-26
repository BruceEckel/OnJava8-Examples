// standardio/OSExecuteDemo.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Demonstrates standard I/O redirection
import onjava.*;

public class OSExecuteDemo {
  public static void main(String[] args) {
    OSExecute.command("javap OSExecuteDemo");
  }
}
/* Output:
Compiled from "OSExecuteDemo.java"
public class OSExecuteDemo {
  public OSExecuteDemo();
  public static void main(java.lang.String[]);
}
*/
