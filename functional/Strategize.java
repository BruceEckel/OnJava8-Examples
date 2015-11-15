// functional/Strategize.java
// ©2016 MindView LLC: see Copyright.txt

interface Strategy {
  String variant(String arg);
}

class Loud implements Strategy {
  public String variant(String arg) {
    return arg.toUpperCase() + "!";
  }
}

class Soft implements Strategy {
  public String variant(String arg) {
    return arg.toLowerCase() + "?";
  }
}

public class Strategize {
  private Strategy strategy;
  public Strategize(Strategy strat) { strategy = strat; }
  public void communicate(String msg) {
    System.out.println(strategy.variant(msg));
  }
  public static void main(String[] args) {
    String msg = "Hello there";
    Strategize[] approaches = {
      new Strategize(new Loud()),
      new Strategize(new Soft()),
      new Strategize(new Strategy() {
        public String variant(String arg) {
          return arg.substring(0, 5);
        }
      })
    };
    for(Strategize approach : approaches)
      approach.communicate(msg);
  }
}
/* Output:
HELLO THERE!
hello there?
Hello
*/
