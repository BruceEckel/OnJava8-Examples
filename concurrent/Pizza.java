// concurrent/Pizza.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import static java.util.concurrent.TimeUnit.*;

public class Pizza {
  public enum Step {
    DOUGH(4), ROLLED(1), SAUCED(1), CHEESED(2),
    TOPPED(5), BAKED(2), SLICED(1), BOXED(0);
    int effort; // Needed to get to the next step
    Step(int effort) { this.effort = effort; }
    Step forward() {
      if(equals(BOXED)) return BOXED;
      try {
        MILLISECONDS.sleep(effort * 100);
      } catch(InterruptedException e) {
        throw new RuntimeException(e);
      }
      return values()[ordinal() + 1];
    }
  }
  private Step step = Step.DOUGH;
  private final int id;
  public Pizza(int id) { this.id = id; }
  public void next() {
    step = step.forward();
    System.out.println("Pizza " + id + ": " + step);
  }
  public void next(Step previousStep) {
    if(!step.equals(previousStep))
      throw new IllegalStateException("Expected " +
        previousStep + " but found " + step);
    next();
  }
  public void roll() { next(Step.DOUGH); }
  public void sauce() { next(Step.ROLLED); }
  public void cheese() { next(Step.SAUCED); }
  public void toppings() { next(Step.CHEESED); }
  public void bake() { next(Step.TOPPED); }
  public void slice() { next(Step.BAKED); }
  public void box() { next(Step.SLICED); }
  public boolean complete() {
    return step.equals(Step.BOXED);
  }
}
