//: patterns/trashvisitor/Visitor.java
// ©2015 MindView LLC: see Copyright.txt
// The base interface for visitors.
package patterns.trashvisitor;

interface Visitor {
  void visit(VAluminum a);
  void visit(VPaper p);
  void visit(VGlass g);
  void visit(VCardboard c);
} ///:~
