// generics/DogsAndRobotMethodReferences.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// "Assisted Latent Typing"
import typeinfo.pets.*;
import java.util.function.*;

class PerformingDog_ extends Dog {
  public void speak() { System.out.println("Woof!"); }
  public void sit() { System.out.println("Sitting"); }
  public void reproduce() {}
}

class Robot_ {
  public void speak() { System.out.println("Click!"); }
  public void sit() { System.out.println("Clank!"); }
  public void oilChange() {}
}

class Communicate_ {
  public static <P> void perform(P performer,
    Consumer<P> action1, Consumer<P> action2) {
    action1.accept(performer);
    action2.accept(performer);
  }
}

public class DogsAndRobotMethodReferences {
  public static void main(String[] args) {
    Communicate_.perform(new PerformingDog_(),
      PerformingDog_::speak, PerformingDog_::sit);
    Communicate_.perform(new Robot_(),
      Robot_::speak, Robot_::sit);
    Communicate_.perform(new Mime(),
      Mime::walkAgainstTheWind, Mime::pushInvisibleWalls);
  }
}
/* Output:
Woof!
Sitting
Click!
Clank!
*/
