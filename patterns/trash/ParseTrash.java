// patterns/trash/ParseTrash.java
// ©2015 MindView LLC: see Copyright.txt
// Open a file and parse its contents into
// Trash objects, placing each into an ArrayList.
package patterns.trash;
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class ParseTrash {
  public static <T extends Trash> void
  fillBin(String dir, String fname, Fillable<T> bin) {
    try {
      Path p = FileSystems.getDefault()
               .getPath("..", dir, fname);
      try(BufferedReader data = Files.newBufferedReader(p)){
        String buf;
        while((buf = data.readLine())!= null) {
          if(buf.trim().length() == 0)
            continue; // Skip empty lines
          if(buf.startsWith("//"))
            continue; // Skip comments
          String type = buf.substring(0,
            buf.indexOf(':')).trim();
          double weight = Double.valueOf(
            buf.substring(buf.indexOf(':') + 1).trim());
          bin.addTrash(Trash.factory(
            new Trash.Info(type, weight)));
        }
      }
    } catch(IOException |
            NumberFormatException |
            Trash.PrototypeNotFoundException |
            Trash.CannotCreateTrashException e) {
      throw new RuntimeException(e);
    }
  }
  // Special case to handle ArrayList:
  public static <T extends Trash> void
  fillBin(String dir, String fname, ArrayList<T> bin) {
    fillBin(dir, fname, new FillableList<>(bin));
  }
}
