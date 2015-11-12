// onjava/Directory.java
// Produce a sequence of File objects that match a
// regular expression in either a local directory,
// or by walking a directory tree.
package onjava;
import java.util.regex.*;
import java.io.*;
import java.util.*;

public final class Directory {
  public static File[]
  local(File dir, final String regex) {
    return dir.listFiles(new FilenameFilter() {
      private Pattern pattern = Pattern.compile(regex);
      @Override
      public boolean accept(File dir, String name) {
        return pattern.matcher(
          new File(name).getName()).matches();
      }
    });
  }
  public static File[]
  local(String path, final String regex) { // Overloaded
    return local(new File(path), regex);
  }
  // A two-tuple for returning a pair of objects:
  public static class TreeInfo implements Iterable<File> {
    public List<File> files = new ArrayList<>();
    public List<File> dirs = new ArrayList<>();
    // The default iterable element is the file list:
    @Override
    public Iterator<File> iterator() {
      return files.iterator();
    }
    void addAll(TreeInfo other) {
      files.addAll(other.files);
      dirs.addAll(other.dirs);
    }
    @Override
    public String toString() {
      return "dirs: " + PPrint.pformat(dirs) +
        "\n\nfiles: " + PPrint.pformat(files);
    }
  }
  public static TreeInfo
  walk(String start, String regex) { // Begin recursion
    return recurseDirs(new File(start), regex);
  }
  public static TreeInfo
  walk(File start, String regex) { // Overloaded
    return recurseDirs(start, regex);
  }
  public static TreeInfo walk(File start) { // Everything
    return recurseDirs(start, ".*");
  }
  public static TreeInfo walk(String start) {
    return recurseDirs(new File(start), ".*");
  }
  static TreeInfo recurseDirs(File startDir, String regex){
    TreeInfo result = new TreeInfo();
    for(File item : startDir.listFiles()) {
      if(item.isDirectory()) {
        result.dirs.add(item);
        result.addAll(recurseDirs(item, regex));
      } else // Regular file
        if(item.getName().matches(regex))
          result.files.add(item);
    }
    return result;
  }
  // Simple validation test:
  public static void main(String[] args) {
    if(args.length == 0)
      System.out.println(walk("."));
    else
      for(String arg : args)
       System.out.println(walk(arg));
  }
}
/* Output: (First 20 Lines)
dirs: []
files: [
  .\BasicSupplier.class
  .\BasicSupplier.java
  .\BinaryFile.class
  .\BinaryFile.java
  .\CollectionData.class
  .\CollectionData.java
  .\ContainerMethodDifferences-erroroutput.txt
  .\ContainerMethodDifferences-output.txt
  .\ContainerMethodDifferences.class
  .\ContainerMethodDifferences.java
  .\ConvertTo.class
  .\ConvertTo.java
  .\CountingSupplier$Boolean.class
  .\CountingSupplier$Byte.class
  .\CountingSupplier$Character.class
  .\CountingSupplier$Double.class
  .\CountingSupplier$Float.class
                  ...
*/
