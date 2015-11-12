// streams/Peeking.java

class Peeking  {
  public static void main(String[] args) throws Exception {
    FileToWords.stream("Cheese.dat")
      .skip(21)
      .limit(4)
      .map(w -> w + " ")
      .peek(System.out::print)
      .map(String::toUpperCase)
      .peek(System.out::print)
      .map(String::toLowerCase)
      .forEach(System.out::print);
  }
}
/* Output:
Well WELL well it's IT'S it's so SO so clean CLEAN clean
*/
