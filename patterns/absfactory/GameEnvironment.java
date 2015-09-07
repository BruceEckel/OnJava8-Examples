// patterns/absfactory/GameEnvironment.java
// ©2015 MindView LLC: see Copyright.txt
// An example of the Abstract Factory pattern.
package patterns.absfactory;

interface Obstacle {
  void action();
}

interface Player {
  void interactWith(Obstacle o);
}

class Kitty implements Player {
  @Override
  public void interactWith(Obstacle ob) {
    System.out.print("Kitty has encountered a ");
    ob.action();
  }
}

class KungFuGuy implements Player {
  @Override
  public void interactWith(Obstacle ob) {
    System.out.print("KungFuGuy now battles a ");
    ob.action();
  }
}

class Puzzle implements Obstacle {
  @Override
  public void action() {
    System.out.println("Puzzle");
  }
}

class NastyWeapon implements Obstacle {
  @Override
  public void action() {
    System.out.println("NastyWeapon");
  }
}

// The Abstract Factory:
interface GameElementFactory {
  Player makePlayer();
  Obstacle makeObstacle();
}

// Concrete factories:
class KittiesAndPuzzles
implements GameElementFactory {
  @Override
  public Player makePlayer() {
    return new Kitty();
  }
  @Override
  public Obstacle makeObstacle() {
    return new Puzzle();
  }
}

class KillAndDismember
implements GameElementFactory {
  @Override
  public Player makePlayer() {
    return new KungFuGuy();
  }
  @Override
  public Obstacle makeObstacle() {
    return new NastyWeapon();
  }
}

public class GameEnvironment {
  private Player p;
  private Obstacle ob;
  public GameEnvironment(
    GameElementFactory factory) {
    p = factory.makePlayer();
    ob = factory.makeObstacle();
  }
  public void play() {
    p.interactWith(ob);
  }
  public static void main(String args[]) {
    GameElementFactory
      kp = new KittiesAndPuzzles(),
      kd = new KillAndDismember();
    GameEnvironment
      g1 = new GameEnvironment(kp),
      g2 = new GameEnvironment(kd);
    g1.play();
    g2.play();
  }
}
/* Output:
Kitty has encountered a Puzzle
KungFuGuy now battles a NastyWeapon
*/
