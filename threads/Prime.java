// threads/Prime.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.*;
import java.util.stream.*;
import static java.util.stream.LongStream.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;

public class Prime {
  static final int COUNT = 100_000;
  public static boolean isPrime(long n) {
    return rangeClosed(2, (long)Math.sqrt(n))
      .noneMatch(i -> n % i == 0);
  }
  public static void main(String[] args)
    throws IOException {
    long start = System.currentTimeMillis();
    List<String> primes =
      iterate(2, i -> i + 1)
        .parallel()              // [1]
        .filter(Prime::isPrime)
        .limit(COUNT)
        .mapToObj(Long::toString)
        .collect(Collectors.toList());
    System.out.println(
      System.currentTimeMillis() - start);
    Files.write(Paths.get("primes.txt"), primes,
      StandardOpenOption.CREATE);
  }
}
