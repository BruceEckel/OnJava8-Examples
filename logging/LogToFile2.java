//: logging/LogToFile2.java
// {Clean: LogToFile2.txt,LogToFile2.txt.lck}
import java.util.logging.*;

public class LogToFile2 {
  private static Logger logger =
    Logger.getLogger("LogToFile2");
  public static void main(String[] args) throws Exception {
    FileHandler logFile= new FileHandler("LogToFile2.txt");
    logFile.setFormatter(new SimpleFormatter());
    logger.addHandler(logFile);
    logger.info("A message logged to the file");
  }
} ///:~
