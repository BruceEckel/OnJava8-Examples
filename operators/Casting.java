// operators/Casting.java
// ©2015 MindView LLC: see Copyright.txt

public class Casting {
  public static void main(String[] args) {
    int i = 200;
    long lng = (long)i;
    lng = i; // "Widening," so a cast is not required
    long lng2 = (long)200;
    lng2 = 200;
    // A "narrowing conversion":
    i = (int)lng2; // Cast required
  }
}
/* Output: (None) */
