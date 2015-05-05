//: logging/ConfigureLogging.java
// {JVMArgs: -Djava.util.logging.config.file=log.prop}
// {Clean: java0.log,java0.log.lck}
import java.util.logging.*;

public class ConfigureLogging {
  static Logger lgr = Logger.getLogger("com"),
    lgr2 = Logger.getLogger("com.bruceeckel"),
    util = Logger.getLogger("com.bruceeckel.util"),
    test = Logger.getLogger("com.bruceeckel.test"),
    rand = Logger.getLogger("random");
  public ConfigureLogging() {
    /* Set Additional formatters, Filters and Handlers for
       the loggers here. You cannot specify the Handlers
       for loggers except the root logger from the
       configuration file. */
  }
  public static void main(String[] args) {
    sendLogMessages(lgr);
    sendLogMessages(lgr2);
    sendLogMessages(util);
    sendLogMessages(test);
    sendLogMessages(rand);
  }
  private static void sendLogMessages(Logger logger) {
    System.out.println(" Logger Name : "
      + logger.getName() + " Level: " + logger.getLevel());
    logger.finest("Finest");
    logger.finer("Finer");
    logger.fine("Fine");
    logger.config("Config");
    logger.info("Info");
    logger.warning("Warning");
    logger.severe("Severe");
  }
} ///:~
