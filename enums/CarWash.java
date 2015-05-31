//: enums/CarWash.java
// ©2015 MindView LLC: see Copyright.txt
import java.util.*;
import static net.mindview.util.Print.*;

public class CarWash {
  public enum Cycle {
    UNDERBODY {
      @Override
      void action() { print("Spraying the underbody"); }
    },
    WHEELWASH {
      @Override
      void action() { print("Washing the wheels"); }
    },
    PREWASH {
      @Override
      void action() { print("Loosening the dirt"); }
    },
    BASIC {
      @Override
      void action() { print("The basic wash"); }
    },
    HOTWAX {
      @Override
      void action() { print("Applying hot wax"); }
    },
    RINSE {
      @Override
      void action() { print("Rinsing"); }
    },
    BLOWDRY {
      @Override
      void action() { print("Blowing dry"); }
    };
    abstract void action();
  }
  EnumSet<Cycle> cycles =
    EnumSet.of(Cycle.BASIC, Cycle.RINSE);
  public void add(Cycle cycle) { cycles.add(cycle); }
  public void washCar() {
    for(Cycle c : cycles)
      c.action();
  }
  @Override
  public String toString() { return cycles.toString(); }
  public static void main(String[] args) {
    CarWash wash = new CarWash();
    print(wash);
    wash.washCar();
    // Order of addition is unimportant:
    wash.add(Cycle.BLOWDRY);
    wash.add(Cycle.BLOWDRY); // Duplicates ignored
    wash.add(Cycle.RINSE);
    wash.add(Cycle.HOTWAX);
    print(wash);
    wash.washCar();
  }
} /* Output:
[BASIC, RINSE]
The basic wash
Rinsing
[BASIC, HOTWAX, RINSE, BLOWDRY]
The basic wash
Applying hot wax
Rinsing
Blowing dry
*///:~
