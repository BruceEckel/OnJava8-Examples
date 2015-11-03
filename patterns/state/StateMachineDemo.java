// patterns/state/StateMachineDemo.java
// Demonstrates StateMachine pattern
// and Template method.
package patterns.state;

interface State {
  void run();
}

abstract class StateMachine {
  protected State currentState;
  abstract protected boolean changeState();
  // Template method:
  protected final void runAll() {
    while(changeState()) // Customizable
      currentState.run();
  }
}

// A different subclass for each state:

class Wash implements State {
  @Override
  public void run() {
    System.out.println("Washing");
    try {
      Thread.sleep(500);
    } catch(InterruptedException e) {}
  }
}

class Spin implements State {
  @Override
  public void run() {
    System.out.println("Spinning");
    try {
      Thread.sleep(500);
    } catch(InterruptedException e) {}
  }
}

class Rinse implements State {
  @Override
  public void run() {
    System.out.println("Rinsing");
    try {
      Thread.sleep(500);
    } catch(InterruptedException e) {}
  }
}

class Washer extends StateMachine {
  private int i = 0;
  // The state table:
  private State states[] = {
    new Wash(), new Spin(),
    new Rinse(), new Spin(),
  };
  public Washer() { runAll(); }
  @Override
  public boolean changeState() {
    if(i < states.length) {
      // Change the state by setting the
      // surrogate reference to a new object:
      currentState = states[i++];
      return true;
    } else
      return false;
  }
}

public class StateMachineDemo {
  public static void main(String args[]) {
    new Washer();
  }
}
/* Output:
Washing
Spinning
Rinsing
Spinning
*/
