// patterns/CommandPattern2.java
// ©2015 MindView LLC: see Copyright.txt
// Using the Runnable functional interface
import java.util.*;

class Command2 {
  public Runnable execute;
}

class Hello2 extends Command2 {
  Hello2() {
    execute = () -> System.out.print("Hello ");
  }
}

class World2 extends Command2 {
  World2() {
    execute = () -> System.out.print("World! ");
  }
}

class IAm2 extends Command2 {
  IAm2() {
    execute = () ->
      System.out.print("I'm the command pattern!");
  }
}

class Macro2 extends Command2 {
  private List<Command2> commands =
    new ArrayList<>();
  public void add(Command2 c) { commands.add(c); }
  Macro2() {
    execute = () -> {
      for(Command2 c: commands) c.execute.run();
    };
  }
}

public class CommandPattern2 {
  public static void main(String args[]) {
    Macro2 macro = new Macro2();
    macro.add(new Hello2());
    macro.add(new World2());
    macro.add(new IAm2());
    macro.execute.run();
  }
}
/* Output:
Hello World! I'm the command pattern!
*/
