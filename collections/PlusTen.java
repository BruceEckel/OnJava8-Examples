// collections/PlusTen.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 16

record PlusTen(int x) {
  PlusTen {
    x += 10;
  }
  // Adjustment to field can only happen in
  // the constructor. Still not legal:
  // void mutate() { x += 10; }
  public static void main(String[] args) {
    System.out.println(new PlusTen(10));
  }
}
/* Output:
PlusTen[x=20]
*/
