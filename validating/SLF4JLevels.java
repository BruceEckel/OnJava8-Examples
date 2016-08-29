// validating/SLF4JLevels.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import org.slf4j.*;

public class SLF4JLevels {
  private static final Logger log =
    LoggerFactory.getLogger(SLF4JLevels.class);
  public static void main(String[] args) {
    log.trace("Hello");
    log.debug("Logging");
    log.info("Using");
    log.warn("the SLF4J");
    log.error("Facade");
  }
}
/* Output:
2016-08-22T14:33:01.951
[main] TRACE SLF4JLevels - Hello
2016-08-22T14:33:01.954
[main] DEBUG SLF4JLevels - Logging
2016-08-22T14:33:01.954
[main] INFO  SLF4JLevels - Using
2016-08-22T14:33:01.955
[main] WARN  SLF4JLevels - the SLF4J
2016-08-22T14:33:01.955
[main] ERROR SLF4JLevels - Facade
*/
