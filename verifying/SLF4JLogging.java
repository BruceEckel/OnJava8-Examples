// verifying/SLF4JLogging.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import org.slf4j.*;

public class SLF4JLogging {
  private static final Logger logger =
    LoggerFactory.getLogger(SLF4JLogging.class);
  public static void main(String[] args) {
    logger.info("hello logging");
  }
}
/* Output:
17:58:42.798 [main] INFO SLF4JLogging - hello logging
*/
