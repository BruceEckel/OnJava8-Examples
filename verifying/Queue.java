// verifying/Queue.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Demonstration of Design by Contract (DbC)
package verifying;
import java.util.*;

public class Queue {
  private Object[] data;
  private int
    in = 0, // Next available storage space
    out = 0; // Next gettable object
  // Has it wrapped around the circular queue?
  private boolean wrapped = false;
  public static class
  QueueException extends RuntimeException {
    public QueueException(String why) { super(why); }
  }
  public Queue(int size) {
    data = new Object[size];
    // Must be true after construction:
    assert invariant();
  }
  public boolean empty() {
    return !wrapped && in == out;
  }
  public boolean full() {
    return wrapped && in == out;
  }
  public boolean isWrapped() { return wrapped; }
  public void put(Object item) {
    precondition(item != null, "put() null item");
    precondition(!full(), "put() into full Queue");
    assert invariant();
    data[in++] = item;
    if(in >= data.length) {
      in = 0;
      wrapped = true;
    }
    assert invariant();
  }
  public Object get() {
    precondition(!empty(), "get() from empty Queue");
    assert invariant();
    Object returnVal = data[out];
    data[out] = null;
    out++;
    if(out >= data.length) {
      out = 0;
      wrapped = false;
    }
    assert postcondition(
      returnVal != null, "Null item in Queue");
    assert invariant();
    return returnVal;
  }
  // Design-by-contract support methods:
  private static void
  precondition(boolean cond, String msg) {
    if(!cond) throw new QueueException(msg);
  }
  private static boolean
  postcondition(boolean cond, String msg) {
    if(!cond) throw new QueueException(msg);
    return true;
  }
  private boolean invariant() {
    // Guarantee that no null values are in the
    // region of 'data' that holds objects:
    for(int i = out; i != in; i = (i + 1) % data.length)
      if(data[i] == null)
        throw new QueueException("null in queue");
    // Guarantee that only null values are outside the
    // region of 'data' that holds objects:
    if(full()) return true;
    for(int i = in; i != out; i = (i + 1) % data.length)
      if(data[i] != null)
        throw new QueueException(
          "non-null outside of queue range: " + dump());
    return true;
  }
  public String dump() {
    return "in = " + in +
      ", out = " + out +
      ", full() = " + full() +
      ", empty() = " + empty() +
      ", queue = " + Arrays.asList(data);
  }
