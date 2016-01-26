// patterns/trash/Fillable.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Any object that can be filled with Trash
package patterns.trash;

public interface Fillable<T extends Trash> {
  void addTrash(T t);
}
