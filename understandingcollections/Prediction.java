// understandingcollections/Prediction.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Predicting the weather with groundhogs
import java.util.*;

public class Prediction {
  private static SplittableRandom rand = new SplittableRandom(47);
  private boolean shadow = rand.nextDouble() > 0.5;
  @Override
  public String toString() {
    if(shadow)
      return "Six more weeks of Winter!";
    else
      return "Early Spring!";
  }
}
