// validating/SLF4JLogging.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import org.slf4j.*;

public class SLF4JLogging {
  private static final Logger log =
    LoggerFactory.getLogger(SLF4JLogging.class);
  public static void main(String[] args) {
    log.info("hello logging");
  }
}
/* Output:
2016-08-22T14:42:44.177
[main] INFO  SLF4JLogging - hello logging
*/
