//: patterns/trashvisitor/Visitor.java
// The base interface for visitors.
package patterns.trashvisitor;
import patterns.trash.*;

interface Visitor {
  void visit(VAluminum a);
  void visit(VPaper p);
  void visit(VGlass g);
  void visit(VCardboard c);
} ///:~
