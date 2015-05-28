//: patterns/CommandPattern.java
import java.util.*;

interface Command {
  void execute();
}

class Hello implements Command {
  @Override
  public void execute() {
    System.out.print("Hello ");
  }
}

class World implements Command {
  @Override
  public void execute() {
    System.out.print("World! ");
  }
}

class IAm implements Command {
  @Override
  public void execute() {
    System.out.print("I'm the command pattern!");
  }
}

// A Command object that holds commands:
class Macro implements Command {
  private ArrayList<Command> commands =
    new ArrayList<>();
  public void add(Command c) { commands.add(c); }
  @Override
  public void execute() {
    commands.forEach(Command::execute);
  }
}

public class CommandPattern {
  public static void main(String args[]) {
    Macro macro = new Macro();
    macro.add(new Hello());
    macro.add(new World());
    macro.add(new IAm());
    macro.execute();
  }
} ///:~
