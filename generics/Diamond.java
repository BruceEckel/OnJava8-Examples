// generics/Diamond.java
// ©2016 MindView LLC: see Copyright.txt

class Bob {}

public class Diamond<T> {
  public static void main(String[] args) {
    GenericHolder<Bob> h3 = new GenericHolder<>();
    h3.set(new Bob());
  }
}
/* Output: (None) */
