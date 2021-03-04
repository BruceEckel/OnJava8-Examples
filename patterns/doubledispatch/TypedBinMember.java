// patterns/doubledispatch/TypedBinMember.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Adapts the double-dispatching
// method into the trash hierarchy without
// modifying the original hierarchy.
package patterns.doubledispatch;
import java.util.*;

public interface TypedBinMember {
  boolean addToBin(List<TypedBin> bins);
}
