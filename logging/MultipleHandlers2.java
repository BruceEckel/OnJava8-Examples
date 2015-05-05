//: logging/MultipleHandlers2.java
// {Clean: MultipleHandlers2.xml,MultipleHandlers2.xml.lck}
import java.util.logging.*;

public class MultipleHandlers2 {
  private static Logger logger =
    Logger.getLogger("MultipleHandlers2");
  public static void main(String[] args) throws Exception {
    FileHandler logFile =
      new FileHandler("MultipleHandlers2.xml");
    logger.addHandler(logFile);
    logger.addHandler(new ConsoleHandler());
    logger.setUseParentHandlers(false);
    logger.warning("Output to multiple handlers");
  }
} ///:~
