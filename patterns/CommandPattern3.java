// patterns/CommandPattern3.java
// ©2015 MindView LLC: see Copyright.txt
// Just implement the Runnable interface!
import java.util.*;

class Hello3 implements Runnable {
  public void run() { System.out.print("Hello "); }
}

class World3 implements Runnable {
  public void run() { System.out.print("World! "); }
}

class IAm3 implements Runnable {
  public void run() {
    System.out.print("I'm the command pattern!");
  }
}

class Macro3 implements Runnable {
  public List<Runnable> commands = new ArrayList<>();
  public void run() {
    for(Runnable c: commands) c.run();
  }
}

public class CommandPattern3 {
  public static void main(String args[]) {
    Macro3 macro = new Macro3();
    macro.commands.add(new Hello3());
    macro.commands.add(new World3());
    macro.commands.add(new IAm3());
    macro.run();
  }
}
/* Output:
Hello World! I'm the command pattern!
*/
