// patterns/doubledispatch/TypedBinMember.java
// ©2015 MindView LLC: see Copyright.txt
// An interface for adding the double dispatching
// method to the trash hierarchy without
// modifying the original hierarchy.
package patterns.doubledispatch;

interface TypedBinMember {
  // The new method:
  boolean addToBin(TypedBin[] tb);
}
