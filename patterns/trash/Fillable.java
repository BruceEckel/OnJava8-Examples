// patterns/trash/Fillable.java
// Any object that can be filled with Trash.
package patterns.trash;

public interface Fillable<T extends Trash> {
  void addTrash(T t);
}
