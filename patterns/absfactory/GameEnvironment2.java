//: patterns/absfactory/GameEnvironment2.java
// Using the Supplier<> Functional Interface.
package patterns.absfactory;
import java.util.function.*;

class GameElementFactory2 {
  Supplier<Player> player;
  Supplier<Obstacle> obstacle;
}

// Concrete factories:
class KittiesAndPuzzles2
extends GameElementFactory2 {
  KittiesAndPuzzles2() {
    player = Kitty::new;
    obstacle = Puzzle::new;
  }
}

class KillAndDismember2
extends GameElementFactory2 {
  KillAndDismember2() {
    player = KungFuGuy::new;
    obstacle = NastyWeapon::new;
  }
}

public class GameEnvironment2 {
  private Player p;
  private Obstacle ob;
  public GameEnvironment2(
    GameElementFactory2 factory) {
    p = factory.player.get();
    ob = factory.obstacle.get();
  }
  public void play() {
    p.interactWith(ob);
  }
  public static void main(String args[]) {
    GameElementFactory2
      kp = new KittiesAndPuzzles2(),
      kd = new KillAndDismember2();
    GameEnvironment2
      g1 = new GameEnvironment2(kp),
      g2 = new GameEnvironment2(kd);
    g1.play();
    g2.play();
  }
} ///:~
