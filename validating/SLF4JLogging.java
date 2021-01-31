// validating/SLF4JLogging.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import org.slf4j.*;

public class SLF4JLogging {
  private static Logger log =
    LoggerFactory.getLogger(SLF4JLogging.class);
  public static void main(String[] args) {
    log.info("hello logging");
  }
}
/* Output:
2021-01-24T08:49:38.496
[main] INFO  SLF4JLogging - hello logging
*/
