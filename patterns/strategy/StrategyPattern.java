//: patterns/strategy/StrategyPattern.java
package patterns.strategy;

// The strategy interface:
interface FindMinima {
  // Line is a sequence of points:
  double[] algorithm(double[] line);
}

// The various strategies:
class LeastSquares implements FindMinima {
  @Override
  public double[] algorithm(double[] line) {
    return new double[] { 1.1, 2.2 }; // Dummy
  }
}

class Perturbation implements FindMinima {
  @Override
  public double[] algorithm(double[] line) {
    return new double[] { 3.3, 4.4 }; // Dummy
  }
}

class Bisection implements FindMinima {
  @Override
  public double[] algorithm(double[] line) {
    return new double[] { 5.5, 6.6 }; // Dummy
  }
}

// The "Context" controls the strategy:
class MinimaSolver {
  private FindMinima strategy;
  public MinimaSolver(FindMinima strat) {
    strategy = strat;
  }
  double[] minima(double[] line) {
    return strategy.algorithm(line);
  }
  void changeAlgorithm(FindMinima newAlgorithm) {
    strategy = newAlgorithm;
  }
}

public class StrategyPattern {
  public static void printArray(double[] array) {
    for(int i = 0; i < array.length; i++) {
      System.out.print(array[i]);
      if(i != array.length -1)
        System.out.print(", ");
    }
    System.out.println();
  }    
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
} ///:~
