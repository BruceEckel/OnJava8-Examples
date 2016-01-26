// onjava/atunit/ClassNameFinder.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
package onjava.atunit;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import onjava.*;

public class ClassNameFinder {
  public static String thisClass(byte[] classBytes) {
    Map<Integer,Integer> offsetTable = new HashMap<>();
    Map<Integer,String> classNameTable = new HashMap<>();
    try {
      DataInputStream data = new DataInputStream(
        new ByteArrayInputStream(classBytes));
      int magic = data.readInt();  // 0xcafebabe
      int minorVersion = data.readShort();
      int majorVersion = data.readShort();
      int constant_pool_count = data.readShort();
      int[] constant_pool = new int[constant_pool_count];
      for(int i = 1; i < constant_pool_count; i++) {
        int tag = data.read();
        // int tableSize;
        switch(tag) {
          case 1: // UTF
            int length = data.readShort();
            char[] bytes = new char[length];
            for(int k = 0; k < bytes.length; k++)
              bytes[k] = (char)data.read();
            String className = new String(bytes);
            classNameTable.put(i, className);
            break;
          case 5: // LONG
          case 6: // DOUBLE
            data.readLong(); // discard 8 bytes
            i++; // Special skip necessary
            break;
          case 7: // CLASS
            int offset = data.readShort();
            offsetTable.put(i, offset);
            break;
          case 8: // STRING
            data.readShort(); // discard 2 bytes
            break;
          case 3:  // INTEGER
          case 4:  // FLOAT
          case 9:  // FIELD_REF
          case 10: // METHOD_REF
          case 11: // INTERFACE_METHOD_REF
          case 12: // NAME_AND_TYPE
          case 18: // Invoke Dynamic
            data.readInt(); // discard 4 bytes;
            break;
          case 15: // Method Handle
            data.readByte();
            data.readShort();
            break;
          case 16: // Method Type
            data.readShort();
            break;
          default:
            throw new RuntimeException("Bad tag " + tag);
        }
      }
      short access_flags = data.readShort();
      String access = (access_flags & 0x0001) == 0 ?
        "nonpublic:" : "public:";
      int this_class = data.readShort();
      int super_class = data.readShort();
      return access + classNameTable.get(
        offsetTable.get(this_class)).replace('/', '.');
    } catch(IOException | RuntimeException e) {
      throw new RuntimeException(e);
    }
  }
  // Demonstration:
  public static void
  main(String[] args) throws Exception {
    PathMatcher matcher = FileSystems.getDefault()
      .getPathMatcher("glob:**/*.class");
    // Walk the entire tree:
    Files.walk(Paths.get("."))
      .filter(matcher::matches)
      //.peek(System.out::println)
      .map(p -> {
          try {
            return thisClass(Files.readAllBytes(p));
          } catch(Exception e) {
            throw new RuntimeException(e);
          }
        })
      .filter(s -> s.startsWith("public:"))
      // .filter(s -> s.indexOf('$') >= 0)
      .map(s -> s.split(":")[1])
      .filter(s -> !s.startsWith("enums."))
      .filter(s -> s.contains("."))
      .forEach(System.out::println);
  }
}
/* Output:
onjava.atunit.AtUnit$TestMethods
onjava.atunit.AtUnit
onjava.atunit.ClassNameFinder
onjava.atunit.Test
onjava.atunit.TestObjectCleanup
onjava.atunit.TestObjectCreate
onjava.atunit.TestProperty
*/
