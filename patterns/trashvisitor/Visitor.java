// patterns/trashvisitor/Visitor.java
// ©2016 MindView LLC: see Copyright.txt
// The base interface for visitors.
package patterns.trashvisitor;

interface Visitor {
  void visit(Aluminum a);
  void visit(Paper p);
  void visit(Glass g);
  void visit(Cardboard c);
  void total();
}
