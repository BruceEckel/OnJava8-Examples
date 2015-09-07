// patterns/trash/Fillable.java
// ©2015 MindView LLC: see Copyright.txt
// Any object that can be filled with Trash.
package patterns.trash;

public interface Fillable<T extends Trash> {
  void addTrash(T t);
}
