// concurrent/Philosopher.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// A dining philosopher
import java.util.*;
import onjava.Nap;

public class Philosopher implements Runnable {
  private Chopstick left;
  private Chopstick right;
  private final int id;
  private final int ponderFactor;
  private SplittableRandom rand = new SplittableRandom(47);
  private void pause() {
    if(ponderFactor == 0) return;
    new Nap(rand.nextInt(ponderFactor * 250));
  }
  public Philosopher(Chopstick left, Chopstick right,
    int ident, int ponder) {
    this.left = left;
    this.right = right;
    id = ident;
    ponderFactor = ponder;
  }
  @Override
  public void run() {
    try {
      while(!Thread.interrupted()) {
        System.out.println(this + " " + "thinking");
        pause();
        // Philosopher becomes hungry
        System.out.println(this + " " + "grabbing right");
        right.take();
        System.out.println(this + " " + "grabbing left");
        left.take();
        System.out.println(this + " " + "eating");
        pause();
        right.drop();
        left.drop();
      }
    } catch(InterruptedException e) {
      System.out.println(
        this + " " + "exiting via interrupt");
    }
  }
  @Override
  public String toString() { return "Philosopher " + id; }
}
