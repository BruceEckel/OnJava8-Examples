// patterns/trashvisitor/Visitable.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// An interface to add visitor functionality to the
// Trash hierarchy without modifying the base class
package patterns.trashvisitor;

interface Visitable {
  // The new method:
  void accept(Visitor v);
}
