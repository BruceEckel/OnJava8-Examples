// patterns/chain/Result.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Carries the result or indicates failure.
package patterns.chain;
import java.util.*;

public class Result {
  public final boolean success;
  public final List<Double> line;
  public Result(List<Double> data) {
    success = true;
    line = data;
  }
  private Result() {
    success = false;
    line = Collections.<Double>emptyList();
  }
  public static final Result fail = new Result();
}
