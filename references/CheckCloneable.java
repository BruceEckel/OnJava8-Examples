//: references/CheckCloneable.java
// Checking to see if a reference can be cloned.

// Can't clone this because it doesn't override clone():
class Ordinary {}

// Overrides clone, but doesn't implement Cloneable:
class WrongClone extends Ordinary {
  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone(); // Throws exception
  }
}

// Does all the right things for cloning:
class IsCloneable extends Ordinary implements Cloneable {
  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}

// Turn off cloning by throwing the exception:
class NoMore extends IsCloneable {
  @Override
  public Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
  }
}

class TryMore extends NoMore {
  @Override
  public Object clone() throws CloneNotSupportedException {
    // Calls NoMore.clone(), throws exception:
    return super.clone();
  }
}

class BackOn extends NoMore {
  private BackOn duplicate(BackOn b) {
    // Somehow make a copy of b and return that copy.
    // This is a dummy copy, just to make the point:
    return new BackOn();
  }
  @Override
  public Object clone() {
    // Doesn't call NoMore.clone():
    return duplicate(this);
  }
}

// You can't inherit from this, so you can't override
// the clone method as you can in BackOn:
final class ReallyNoMore extends NoMore {}

public class CheckCloneable {
  public static Ordinary tryToClone(Ordinary ord) {
    String id = ord.getClass().getName();
    System.out.println("Attempting " + id);
    Ordinary x = null;
    if(ord instanceof Cloneable) {
      try {
        x = (Ordinary)((IsCloneable)ord).clone();
        System.out.println("Cloned " + id);
      } catch(CloneNotSupportedException e) {
        System.err.println("Could not clone " + id);
      }
    } else {
      System.out.println("Doesn't implement Cloneable");
    }
    return x;
  }
  public static void main(String[] args) {
    // Upcasting:
    Ordinary[] ord = {
      new IsCloneable(),
      new WrongClone(),
      new NoMore(),
      new TryMore(),
      new BackOn(),
      new ReallyNoMore(),
    };
    Ordinary x = new Ordinary();
    // This won't compile; clone() is protected in Object:
    //! x = (Ordinary)x.clone();
    // Checks first to see if a class implements Cloneable:
    for (Ordinary ord1 : ord) {
      tryToClone(ord1);
    }
  }
} ///:~
