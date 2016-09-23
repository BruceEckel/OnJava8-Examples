// strings/InfiniteRecursion.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Accidental recursion
// {ThrowsException}
// {ValidateByHand}
import java.util.*;
import java.util.stream.*;

public class InfiniteRecursion {
  @Override
  public String toString() {
    return " InfiniteRecursion address: " + this + "\n";
  }
  public static void main(String[] args) {
    Stream.generate(InfiniteRecursion::new)
      .limit(10)
      .forEach(System.out::println);
  }
}
