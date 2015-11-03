// containers/UniqueWordsAlphabetic.java
// Producing an alphabetic listing.
import java.util.*;
import com.mindviewinc.util.*;

public class UniqueWordsAlphabetic {
  public static void main(String[] args) {
    Set<String> words =
      new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
    words.addAll(
      new TextFile("SetOperations.java", "\\W+"));
    System.out.println(words);
  }
}
/* Output:
[A, add, addAll, added, args, B, C, class, Collections,
com, containers, contains, containsAll, D, E, F, false,
from, G, H, HashSet, I, import, in, J, java, K, L, M, main,
mindviewinc, N, new, Output, Print, public, remove,
removeAll, removed, Set, set1, set2, SetOperations, split,
static, String, to, true, util, void, X, Y, Z]
*/
