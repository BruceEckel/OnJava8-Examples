// innerclasses/BigEgg.java
// An inner class cannot be overriden like a method.

class Egg {
  private Yolk y;
  protected class Yolk {
    public Yolk() { System.out.println("Egg.Yolk()"); }
  }
  public Egg() {
    System.out.println("New Egg()");
    y = new Yolk();
  }
}

public class BigEgg extends Egg {
  public class Yolk {
    public Yolk() { System.out.println("BigEgg.Yolk()"); }
  }
  public static void main(String[] args) {
    new BigEgg();
  }
}
/* Output:
New Egg()
Egg.Yolk()
*/
