// innerclasses/Sequence.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Holds a sequence of Objects

interface Selector {
  boolean end();
  Object current();
  void next();
}

public class Sequence {
  private Object[] items;
  private int next = 0;
  public Sequence(int size) {
    items = new Object[size];
  }
  public void add(Object x) {
    if(next < items.length)
      items[next++] = x;
  }
  private class SequenceSelector implements Selector {
    private int i = 0;
    @Override
    public boolean end() { return i == items.length; }
    @Override
    public Object current() { return items[i]; }
    @Override
    public void next() { i++; }
  }
  public Selector selector() {
    return new SequenceSelector();
  }
  public static void main(String[] args) {
    Sequence sequence = new Sequence(10);
    for(int i = 0; i < 10; i++)
      sequence.add(Integer.toString(i));
    Selector selector = sequence.selector();
    while(!selector.end()) {
      System.out.print(selector.current() + " ");
      selector.next();
    }
  }
}
/* Output:
0 1 2 3 4 5 6 7 8 9
*/

// My simulation code
interface Iterator {
  boolean end();
  Object current();
  void next();
}
class Container {
  private Object[] items;
  private int next = 0;
  public Container(int size) { items = new Object[size]; }
  public void add(Object x) {
    if (next < items.length)
      items[next++] = x;
  }
  private class ContainerIterator implements Iterator {
    private int i = 0;
    public boolean end() { return i == items.length; }
    public Object current() { return items[i]; }
    public void next() { i++; }
  }
  public ContainerIterator iterator() { return new ContainerIterator(); }

  public static void main(String[] args) {
    Container container = new Container(10);
    for (int i = 10; i > 0; i--)
      container.add(i);
    ContainerIterator iterator = container.iterator();
    while (!iterator.end()) {
      System.out.print(iterator.current() + " ");
      iterator.next();
    }
  }
}