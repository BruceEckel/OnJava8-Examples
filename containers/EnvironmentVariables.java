// containers/EnvironmentVariables.java
// ©2015 MindView LLC: see Copyright.txt
import java.util.*;

public class EnvironmentVariables {
  public static void main(String[] args) {
    for(Map.Entry entry: System.getenv().entrySet()) {
      System.out.println(entry.getKey() + ": " +
        entry.getValue());
    }
  }
}
/* Output: (Execute to see) */
