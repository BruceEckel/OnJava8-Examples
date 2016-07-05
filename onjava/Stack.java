// onjava/Stack.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// A Stack class built with an ArrayDeque
package onjava;
import java.util.Deque;
import java.util.ArrayDeque;

public class Stack<T> {
  private Deque<T> storage = new ArrayDeque<>();
  public void push(T v) { storage.push(v); }
  public T peek() { return storage.peek(); }
  public T pop() { return storage.pop(); }
  public boolean isEmpty() { return storage.isEmpty(); }
  @Override
  public String toString() {
    return storage.toString();
  }
}
