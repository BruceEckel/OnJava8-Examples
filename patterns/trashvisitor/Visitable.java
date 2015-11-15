// patterns/trashvisitor/Visitable.java
// ©2016 MindView LLC: see Copyright.txt
// An interface to add visitor functionality to
// the Trash hierarchy without modifying the
// base class.
package patterns.trashvisitor;

interface Visitable {
  // The new method:
  void accept(Visitor v);
}
