//: patterns/chain/ChainOfResponsibility.java
// ©2015 MindView LLC: see Copyright.txt
package patterns.chain;
import java.util.*;
import static net.mindview.util.PrintArray.*;

class Result {
  boolean success;
  double[] line;
  public Result(double[] data) {
    success = true;
    line = data;
  }
  public Result() {
    success = false;
    line = new double[] {};
  }
}

class Fail extends Result {}

interface Algorithm {
  Result algorithm(double[] line);
}

class LeastSquares implements Algorithm {
  public Result algorithm(double[] line) {
    System.out.println("LeastSquares.algorithm");
    boolean weSucceed = false;
    if(weSucceed) // Actual test/calculation here
      return new Result(new double[] { 1.1, 2.2 });
    else // Try the next one in the chain:
      return new Fail();
  }
}

class Perturbation implements Algorithm {
  public Result algorithm(double[] line) {
    System.out.println("Perturbation.algorithm");
    boolean weSucceed = false;
    if(weSucceed) // Actual test/calculation here
      return new Result(new double[] { 3.3, 4.4 });
    else
      return new Fail();
  }
}

class Bisection implements Algorithm {
  public Result algorithm(double[] line) {
    System.out.println("Bisection.algorithm");
    boolean weSucceed = true;
    if(weSucceed) // Actual test/calculation here
      return new Result(new double[] { 5.5, 6.6 });
    else
      return new Fail();
  }
}

class FindMinima {
  List<Algorithm> algorithms = Arrays.asList(
    new LeastSquares(),
    new Perturbation(),
    new Bisection()
  );
  public Result minima(double[] line) {
    for (Algorithm alg : algorithms) {
      Result result = alg.algorithm(line);
      if(result.success)
        return result;
    }
    return new Fail();
  }
}

public class ChainOfResponsibility {
  public static void main(String args[]) {
    FindMinima solver = new FindMinima();
    double[] line = { 
      1.0, 2.0, 1.0, 2.0, -1.0, 
      3.0, 4.0, 5.0, 4.0 };
    Result result = solver.minima(line);
    if(result.success)
      printArray(result.line);
    else
      System.out.println("No algorithm found");
  }
} ///:~
