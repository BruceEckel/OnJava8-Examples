// patterns/chain/ChainOfResponsibility2.java
// ©2015 MindView LLC: see Copyright.txt
// Using the Functional interface.
package patterns.chain;
import java.util.*;
import java.util.function.*;
import static com.mindviewinc.util.PrintArray.*;

class FindMinima2 {
  public static Result leastSquares(double[] line) {
    System.out.println("LeastSquares.algorithm");
    boolean weSucceed = false;
    if(weSucceed) // Actual test/calculation here
      return new Result(new double[] { 1.1, 2.2 });
    else // Try the next one in the chain:
      return new Fail();
  }
  public static Result perturbation(double[] line) {
    System.out.println("Perturbation.algorithm");
    boolean weSucceed = false;
    if(weSucceed) // Actual test/calculation here
      return new Result(new double[] { 3.3, 4.4 });
    else
      return new Fail();
  }
  public static Result bisection(double[] line) {
    System.out.println("Bisection.algorithm");
    boolean weSucceed = true;
    if(weSucceed) // Actual test/calculation here
      return new Result(new double[] { 5.5, 6.6 });
    else
      return new Fail();
  }
  static List<Function<double[], Result>> algorithms =
    Arrays.asList(
      FindMinima2::leastSquares,
      FindMinima2::perturbation,
      FindMinima2::bisection
    );
  public static Result minima(double[] line) {
    for (Function<double[], Result> alg : algorithms) {
      Result result = alg.apply(line);
      if(result.success)
        return result;
    }
    return new Fail();
  }
}

public class ChainOfResponsibility2 {
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
}
/* Output:
LeastSquares.algorithm
Perturbation.algorithm
Bisection.algorithm
5.5, 6.6
*/
