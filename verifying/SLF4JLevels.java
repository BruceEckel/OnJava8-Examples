// verifying/SLF4JLevels.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import org.slf4j.*;

public class SLF4JLevels {
  private static final Logger logger =
    LoggerFactory.getLogger(SLF4JLevels.class);
  public static void main(String[] args) {
    logger.trace("level: trace");
    logger.debug("level: debug");
    logger.info("level: info");
    logger.warn("level: warn");
    logger.error("level: error");
  }
}
/* Output:
20:09:34.499 [main] DEBUG SLF4JLevels - level: debug
20:09:34.502 [main] INFO SLF4JLevels - level: info
20:09:34.502 [main] WARN SLF4JLevels - level: warn
20:09:34.502 [main] ERROR SLF4JLevels - level: error
*/
