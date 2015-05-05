//: logging/MultipleHandlers.java
// {Clean: MultipleHandlers.xml,MultipleHandlers.xml.lck}
import java.util.logging.*;

public class MultipleHandlers {
  private static Logger logger =
    Logger.getLogger("MultipleHandlers");
  public static void main(String[] args) throws Exception {
    FileHandler logFile =
      new FileHandler("MultipleHandlers.xml");
    logger.addHandler(logFile);
    logger.addHandler(new ConsoleHandler());
    logger.warning("Output to multiple handlers");
  }
} ///:~
