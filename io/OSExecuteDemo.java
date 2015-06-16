//: io/OSExecuteDemo.java
// ©2015 MindView LLC: see Copyright.txt
// Demonstrates standard I/O redirection.
import com.mindviewinc.util.*;

public class OSExecuteDemo {
  public static void main(String[] args) {
    OSExecute.command("javap OSExecuteDemo");
  }
} /* Output:
Compiled from "OSExecuteDemo.java"
public class OSExecuteDemo {
  public OSExecuteDemo();
  public static void main(java.lang.String[]);
}
*///:~
