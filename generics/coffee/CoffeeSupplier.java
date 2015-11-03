// generics/coffee/CoffeeSupplier.java
// Generate different types of Coffee:
package generics.coffee;
import java.util.*;
import java.util.function.*;

public class CoffeeSupplier
implements Supplier<Coffee>, Iterable<Coffee> {
  private Class<?>[] types = { Latte.class, Mocha.class,
    Cappuccino.class, Americano.class, Breve.class, };
  private static Random rand = new Random(47);
  public CoffeeSupplier() {}
  // For iteration:
  private int size = 0;
  public CoffeeSupplier(int sz) { size = sz; }
  @Override
  public Coffee get() {
    try {
      return (Coffee)
        types[rand.nextInt(types.length)].newInstance();
      // Report programmer errors at run time:
    } catch(InstantiationException |
            IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
  class CoffeeIterator implements Iterator<Coffee> {
    int count = size;
    @Override
    public boolean hasNext() { return count > 0; }
    @Override
    public Coffee next() {
      count--;
      return CoffeeSupplier.this.get();
    }
    @Override
    public void remove() { // Not implemented
      throw new UnsupportedOperationException();
    }
  }
  @Override
  public Iterator<Coffee> iterator() {
    return new CoffeeIterator();
  }
  public static void main(String[] args) {
    CoffeeSupplier gen = new CoffeeSupplier();
    for(int i = 0; i < 5; i++)
      System.out.println(gen.get());
    for(Coffee c : new CoffeeSupplier(5))
      System.out.println(c);
  }
}
/* Output:
Americano 0
Latte 1
Americano 2
Mocha 3
Mocha 4
Breve 5
Americano 6
Latte 7
Cappuccino 8
Cappuccino 9
*/
