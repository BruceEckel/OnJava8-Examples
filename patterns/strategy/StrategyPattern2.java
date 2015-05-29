//: patterns/strategy/StrategyPattern2.java
// ©2015 MindView LLC: see Copyright.txt
package patterns.strategy;
import java.util.function.*;  
import static net.mindview.util.PrintArray.*;

// "Context" is now incorporated:
class FindMinima2 {
  Function<double[], double[]> algorithm;
  FindMinima2() { leastSquares(); } // default
  // The various strategies:
  void leastSquares() {
    algorithm = (line) -> new double[] { 1.1, 2.2 };
  }
  void perturbation() {
    algorithm = (line) -> new double[] { 3.3, 4.4 };
  }
  void bisection() {
    algorithm = (line) -> new double[] { 5.5, 6.6 };
  }
  double[] minima(double[] line) {
    return algorithm.apply(line);
  }
}

public class StrategyPattern2 {
  public static void main(String args[]) {
    FindMinima2 solver = new FindMinima2();
    double[] line = { 
      1.0, 2.0, 1.0, 2.0, -1.0, 
      3.0, 4.0, 5.0, 4.0 };
    printArray(solver.minima(line));
    solver.bisection();
    printArray(solver.minima(line));
  }
} ///:~
