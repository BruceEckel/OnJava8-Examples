//: patterns/trash/ParseTrash.java
// ©2015 MindView LLC: see Copyright.txt
// Open a file and parse its contents into
// Trash objects, placing each into an ArrayList.
package patterns.trash;
import java.util.*;
import java.io.*;

public class ParseTrash {
  public static <T extends Trash> void
  fillBin(String filename, Fillable<T> bin) {
    try {
      try (BufferedReader data = new BufferedReader(
             new FileReader(filename))) {
        String buf;
        while((buf = data.readLine())!= null) {
          if(buf.trim().length() == 0)
            continue; // Skip empty lines
          String type = buf.substring(0,
                  buf.indexOf(':')).trim();
          double weight = Double.valueOf(
                  buf.substring(buf.indexOf(':') + 1)
                          .trim());
          bin.addTrash(Trash.factory(
            new Trash.Info(type, weight)));
        }
      }
    } catch(IOException |
            NumberFormatException |
            Trash.PrototypeNotFoundException |
            Trash.CannotCreateTrashException e) {
      e.printStackTrace();
    }
  }
  // Special case to handle ArrayList:
  public static <T extends Trash> void
  fillBin(String filename, ArrayList<T> bin) {
    fillBin(filename, new FillableList<>(bin));
  }
} ///:~
