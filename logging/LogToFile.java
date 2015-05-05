//: logging/LogToFile.java
// {Clean: LogToFile.xml,LogToFile.xml.lck}
import java.util.logging.*;

public class LogToFile {
  private static Logger logger =
    Logger.getLogger("LogToFile");
  public static void main(String[] args) throws Exception {
    logger.addHandler(new FileHandler("LogToFile.xml"));
    logger.info("A message logged to the file");
  }
} ///:~
