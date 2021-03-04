// patterns/chain/ChainOfResponsibility.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {java patterns.chain.ChainOfResponsibility}
package patterns.chain;
import java.util.*;
import java.util.function.*;

interface Algorithm {
  Result algorithm(List<Double> line);
}

class FindMinima {
  public static Result test(
    boolean success, String id, double d1, double d2) {
    System.out.println(id);
    if(success) // Actual test/calculation here
      return new Result(Arrays.asList(d1, d2));
    else // Try the next one in the chain:
      return Result.fail;
  }
  public static Result leastSquares(List<Double> line) {
    return test(false, "LeastSquares", 1.1, 2.2);
  }
  public static Result perturbation(List<Double> line) {
    return test(false, "Perturbation", 3.3, 4.4);
  }
  public static Result bisection(List<Double> line) {
    return test(true, "Bisection", 5.5, 6.6);
  }
  static List<Function<List<Double>, Result>>
    algorithms = Arrays.asList(
      FindMinima::leastSquares,
      FindMinima::perturbation,
      FindMinima::bisection
    );
  public static Result minima(List<Double> line) {
    for(Function<List<Double>, Result> alg :
        algorithms) {
      Result result = alg.apply(line);
      if(result.success)
        return result;
    }
    return Result.fail;
  }
}

public class ChainOfResponsibility {
  public static void main(String[] args) {
    FindMinima solver = new FindMinima();
    List<Double> line = Arrays.asList(
      1.0, 2.0, 1.0, 2.0, -1.0,
      3.0, 4.0, 5.0, 4.0);
    Result result = solver.minima(line);
    if(result.success)
      System.out.println(result.line);
    else
      System.out.println("No algorithm found");
  }
}
/* Output:
LeastSquares
Perturbation
Bisection
[5.5, 6.6]
*/
