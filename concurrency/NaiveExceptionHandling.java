//: concurrency/NaiveExceptionHandling.java
// ©2015 MindView LLC: see Copyright.txt
// {ValidateByHand}
// {ThrowsException}
import java.util.concurrent.*;

public class NaiveExceptionHandling {
  public static void main(String[] args) {
    try {
      ExecutorService exec =
        Executors.newCachedThreadPool();
      exec.execute(new ExceptionThread());
    } catch(RuntimeException ue) {
      // This statement will NOT execute!
      System.out.println("Exception was handled!");
    }
  }
} ///:~
