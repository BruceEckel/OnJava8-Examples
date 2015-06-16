//: patterns/strategy/StrategyPattern.java
// ©2015 MindView LLC: see Copyright.txt
package patterns.strategy;
import java.util.function.*;
import static com.mindviewinc.util.PrintArray.*;

// The common strategy base type:
class FindMinima {
  Function<double[], double[]> algorithm;
}

// The various strategies:
class LeastSquares extends FindMinima {
  LeastSquares() {
    // Line is a sequence of points (Dummy data):
    algorithm = (line) -> new double[] { 1.1, 2.2 };
  }
}

class Perturbation extends FindMinima {
  Perturbation() {
    algorithm = (line) -> new double[] { 3.3, 4.4 };
  }
}

class Bisection extends FindMinima {
  Bisection() {
    algorithm = (line) -> new double[] { 5.5, 6.6 };
  }
}

// The "Context" controls the strategy:
class MinimaSolver {
  private FindMinima strategy;
  public MinimaSolver(FindMinima strat) {
    strategy = strat;
  }
  double[] minima(double[] line) {
    return strategy.algorithm.apply(line);
  }
  void changeAlgorithm(FindMinima newAlgorithm) {
    strategy = newAlgorithm;
  }
}

public class StrategyPattern {
  public static void main(String args[]) {
    MinimaSolver solver =
      new MinimaSolver(new LeastSquares());
    double[] line = {
      1.0, 2.0, 1.0, 2.0, -1.0,
      3.0, 4.0, 5.0, 4.0 };
    printArray(solver.minima(line));
    solver.changeAlgorithm(new Bisection());
    printArray(solver.minima(line));
  }
} /* Output:
1.1, 2.2
5.5, 6.6
*///:~
