// patterns/trash/ParseTrash.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Opens a file and parses its contents into
// Trash objects, placing each into a Fillable.
// {java patterns.trash.ParseTrash}
package patterns.trash;
import java.util.*;
import java.util.stream.*;
import java.io.*;
import java.nio.file.*;
import java.nio.file.Files;

public class ParseTrash {
  public static String source = "Trash.dat";
  public static <T extends Trash> void
  fillBin(String packageName, Fillable<T> bin) {
    DynaFactory factory =
      new DynaFactory(packageName);
    try {
      Files.lines(Paths.get("trash", source))
        // Remove comments and empty lines:
        .filter(line -> line.trim().length() != 0)
        .filter(line -> !line.startsWith("//"))
        .forEach(line -> {
          String type = line.substring(
              0, line.indexOf(':')).trim();
          double weight = Double.valueOf(
            line.substring(line.indexOf(':') + 1)
            .trim());
          bin.addTrash(factory.create(
            new TrashInfo(type, weight)));
        });
    } catch(IOException | NumberFormatException e) {
      throw new RuntimeException(e);
    }
  }
  // Special case to handle a List:
  public static <T extends Trash> void
  fillBin(String packageName, List<T> bin) {
    fillBin(packageName, new FillableList<>(bin));
  }
  // Basic test:
  public static void main(String[] args) {
    List<Trash> bin = new ArrayList<>();
    fillBin("trash", bin);
    bin.forEach(System.out::println);
  }
}
/* Output:
Loading patterns.trash.Cardboard
Loading patterns.trash.Paper
Loading patterns.trash.Aluminum
Loading patterns.trash.Glass
Cardboard weight: 4.40 * price: 0.11 = 0.48
Paper weight: 8.00 * price: 0.10 = 0.80
Aluminum weight: 1.80 * price: 1.67 = 3.01
Glass weight: 5.40 * price: 0.23 = 1.24
Aluminum weight: 3.40 * price: 1.67 = 5.68
Cardboard weight: 2.20 * price: 0.11 = 0.24
Glass weight: 4.30 * price: 0.23 = 0.99
Cardboard weight: 1.20 * price: 0.11 = 0.13
Paper weight: 6.60 * price: 0.10 = 0.66
Aluminum weight: 2.70 * price: 1.67 = 4.51
Paper weight: 9.10 * price: 0.10 = 0.91
Glass weight: 3.60 * price: 0.23 = 0.83
*/
