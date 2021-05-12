// concurrent/ParallelPrime.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.LongStream.iterate;
import static java.util.stream.LongStream.rangeClosed;

public class ParallelPrime {
  static final int COUNT = 100_000;
  public static boolean isPrime(long n) {
    return rangeClosed(2, (long)Math.sqrt(n))
      .noneMatch(i -> n % i == 0);
  }
  public static void main(String[] args) throws IOException {
    long statTime = System.nanoTime();
    List<String> primes =
      iterate(2, i -> i + 1)
        .parallel()                       // [1]
        .filter(ParallelPrime::isPrime)
        .limit(COUNT)
        .mapToObj(Long::toString)
        .collect(Collectors.toList());
    long endTime = System.nanoTime();
    System.out.println(endTime - statTime);
    Files.write(Paths.get("primes.txt"), primes,
      StandardOpenOption.CREATE);
  }
}
/* Output:
1635
*/
