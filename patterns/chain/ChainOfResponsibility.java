//: patterns/chain/ChainOfResponsibility.java
package patterns.chain;

class FindMinima {
  private FindMinima successor = null;
  public void add(FindMinima succ) {
    FindMinima end = this;
    while(end.successor != null)
      end = end.successor; // Traverse list
    end.successor = succ;
  }
  public double[] algorithm(double[] line) {
    if(successor != null)
      return successor.algorithm(line);
    else // Try the next one in the chain:
      return new double[] {};
  }
}

class LeastSquares extends FindMinima {
  @Override
  public double[] algorithm(double[] line) {
    System.out.println("LeastSquares.algorithm");
    boolean weSucceed = false;
    if(weSucceed) // Actual test/calculation here
      return new double[] { 1.1, 2.2 }; // Dummy
    else // Try the next one in the chain:
      return super.algorithm(line);
  }
}

class Perturbation extends FindMinima {
  @Override
  public double[] algorithm(double[] line) {
    System.out.println("Perturbation.algorithm");
    boolean weSucceed = false;
    if(weSucceed) // Actual test/calculation here
      return new double[] { 3.3, 4.4 }; // Dummy
    else // Try the next one in the chain:
      return super.algorithm(line);
  }
}

class Bisection extends FindMinima {
  @Override
  public double[] algorithm(double[] line) {
    System.out.println("Bisection.algorithm");
    boolean weSucceed = true;
    if(weSucceed) // Actual test/calculation here
      return new double[] { 5.5, 6.6 }; // Dummy
    else
      return super.algorithm(line);
  }
}

// The "Handler" proxies to the first functor:
class MinimaSolver {
  private FindMinima chain = new FindMinima();
  void add(FindMinima newAlgorithm) {
    chain.add(newAlgorithm);
  }
  // Make the call to the top of the chain:
  double[] minima(double[] line) {
    return chain.algorithm(line);
  }
}

public class ChainOfResponsibility {
  public static void printArray(double[] array) {
    for(int i = 0; i < array.length; i++) {
      System.out.print(array[i]);
      if(i != array.length -1)
        System.out.print(", ");
    }
    System.out.println();
  }    
  public static void main(String args[]) {
    MinimaSolver solver = new MinimaSolver();
    solver.add(new LeastSquares());
    solver.add(new Perturbation());
    solver.add(new Bisection());
    double[] line = { 
      1.0, 2.0, 1.0, 2.0, -1.0, 
      3.0, 4.0, 5.0, 4.0 };
    printArray(solver.minima(line));
  }
} ///:~
