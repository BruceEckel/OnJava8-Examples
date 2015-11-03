// generics/BankTeller.java
// A very simple bank teller simulation.
import java.util.*;
import java.util.function.*;

class Customer {
  private static long counter = 1;
  private final long id = counter++;
  private Customer() {}
  @Override
  public String toString() { return "Customer " + id; }
  // A method to produce Supplier objects:
  public static Supplier<Customer> generator() {
    return Customer::new; // Constructor reference
  }
}

class Teller {
  private static long counter = 1;
  private final long id = counter++;
  private Teller() {}
  @Override
  public String toString() { return "Teller " + id; }
  // A single Supplier object:
  public static Supplier<Teller> generator = Teller::new;
}

public class BankTeller {
  public static void serve(Teller t, Customer c) {
    System.out.println(t + " serves " + c);
  }
  public static void main(String[] args) {
    Random rand = new Random(47);
    Queue<Customer> line = new LinkedList<>();
    Suppliers.fill(line, Customer.generator(), 15);
    List<Teller> tellers = new ArrayList<>();
    Suppliers.fill(tellers, Teller.generator, 4);
    for(Customer c : line)
      serve(tellers.get(rand.nextInt(tellers.size())), c);
  }
}
/* Output:
Teller 3 serves Customer 1
Teller 2 serves Customer 2
Teller 3 serves Customer 3
Teller 1 serves Customer 4
Teller 1 serves Customer 5
Teller 3 serves Customer 6
Teller 1 serves Customer 7
Teller 2 serves Customer 8
Teller 3 serves Customer 9
Teller 3 serves Customer 10
Teller 2 serves Customer 11
Teller 4 serves Customer 12
Teller 2 serves Customer 13
Teller 1 serves Customer 14
Teller 1 serves Customer 15
*/
