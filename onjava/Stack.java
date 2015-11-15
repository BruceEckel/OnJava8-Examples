// onjava/Stack.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Making a stack from a LinkedList.
package onjava;
import java.util.LinkedList;

public class Stack<T> {
  private LinkedList<T> storage = new LinkedList<>();
  public void push(T v) { storage.addFirst(v); }
  public T peek() { return storage.getFirst(); }
  public T pop() { return storage.removeFirst(); }
  public boolean empty() { return storage.isEmpty(); }
  @Override
  public String toString() { return storage.toString(); }
}
