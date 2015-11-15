// containers/UniqueWords.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;
import onjava.*;

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
