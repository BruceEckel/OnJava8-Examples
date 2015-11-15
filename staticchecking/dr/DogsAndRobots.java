// staticchecking/dr/DogsAndRobots.java
// ©2016 MindView LLC: see Copyright.txt
package staticchecking.dr;

interface Speaks { void talk(); }

class Dog implements Speaks {
  public void talk() {
    System.out.println("Woof!");
  }
  public void reproduce() { }
}

class Robot implements Speaks {
  public void talk() {
    System.out.println("Click!");
  }
  public void oilChange() { }
}

class Communicate {
  public static <T extends Speaks>
  void speak(T speaker) {
    speaker.talk();
  }
}

public class DogsAndRobots {
  public static void main(String[] args) {
    Dog d = new Dog();
    Robot r = new Robot();
    Communicate.speak(d);
    Communicate.speak(r);
  }
}
/* Output:
Woof!
Click!
*/
