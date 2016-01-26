// annotations/UseCaseTracker.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.lang.reflect.*;
import java.util.*;

public class UseCaseTracker {
  public static void
  trackUseCases(List<Integer> useCases, Class<?> cl) {
    for(Method m : cl.getDeclaredMethods()) {
      UseCase uc = m.getAnnotation(UseCase.class);
      if(uc != null) {
        System.out.println("Found Use Case:" + uc.id() +
          " " + uc.description());
        useCases.remove(new Integer(uc.id()));
      }
    }
    for(int i : useCases) {
      System.out.println(
        "Warning: Missing use case-" + i);
    }
  }
  public static void main(String[] args) {
    // <* Can't use Arrays.asList() for some reason *>
    List<Integer> useCases = new ArrayList<>();
    Collections.addAll(useCases, 47, 48, 49, 50);
    trackUseCases(useCases, PasswordUtils.class);
  }
}
/* Output:
Found Use Case:49 New passwords can't equal previously used
ones
Found Use Case:47 Passwords must contain at least one
numeric
Found Use Case:48 no description
Warning: Missing use case-50
*/
