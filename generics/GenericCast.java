// generics/GenericCast.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.stream.*;

class FixedSizeStack<T> {
  private int index = 0;
  private Object[] storage;
  public FixedSizeStack(int size) {
    storage = new Object[size];
  }
  public void push(T item) { storage[index++] = item; }
  @SuppressWarnings("unchecked")
  public T pop() { return (T)storage[--index]; }
}

public class GenericCast {
  public static final int SIZE = 10;
  public static void main(String[] args) {
    FixedSizeStack<String> strings =
      new FixedSizeStack<>(SIZE);
    Stream.of("A B C D E F G H I J".split(" "))
      .forEach(s -> strings.push(s));
    for(int i = 0; i < SIZE; i++)
      System.out.print(strings.pop() + " ");
  }
}
/* Output:
J I H G F E D C B A
*/
