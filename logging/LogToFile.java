//: logging/LogToFile.java
// ©2015 MindView LLC: see Copyright.txt
import java.util.logging.*;

public class LogToFile {
  private static Logger logger =
    Logger.getLogger("LogToFile");
  public static void
  main(String[] args) throws Exception {
    logger.addHandler(
      new FileHandler("LogToFile.xml"));
    logger.info("A message logged to the file");
  }
} /* Output:
May 14, 2015 3:29:53 PM LogToFile main
INFO: A message logged to the file
*///:~
