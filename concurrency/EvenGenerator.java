//: concurrency/EvenGenerator.java
// ©2015 MindView LLC: see Copyright.txt
// When threads collide.

public class EvenGenerator extends IntGenerator {
  private int currentEvenValue = 0;
  @Override
  public int next() {
    ++currentEvenValue; // Danger point here!
    ++currentEvenValue;
    return currentEvenValue;
  }
  public static void main(String[] args) {
    EvenChecker.test(new EvenGenerator());
  }
} /* Output:
Press Control-C to exit
1649 not even!
1657 not even!
1655 not even!
1653 not even!
1651 not even!
*///:~
