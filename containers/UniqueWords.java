// containers/UniqueWords.java
// ©2015 MindView LLC: see Copyright.txt
import java.util.*;
import com.mindviewinc.util.*;

public class UniqueWords {
  public static void main(String[] args) {
    Set<String> words = new TreeSet<>(
      new TextFile("SetOperations.java", "\\W+"));
    System.out.println(words);
  }
}
/* Output:
[A, B, C, Collections, D, E, F, G, H, HashSet, I, J, K, L,
M, N, Output, Print, Set, SetOperations, String, X, Y, Z,
add, addAll, added, args, class, com, containers, contains,
containsAll, false, from, import, in, java, main,
mindviewinc, new, print, public, remove, removeAll,
removed, set1, set2, split, static, to, true, util, void]
*/
