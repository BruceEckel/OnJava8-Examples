// compression/ZipCompress.java
// Uses Zip compression to compress any
// number of files given on the command line.
// {Args: ZipCompress.java}
import java.util.zip.*;
import java.io.*;
import java.util.*;

public class ZipCompress {
  public static void main(String[] args)
  throws IOException {
    FileOutputStream f = new FileOutputStream("test.zip");
    CheckedOutputStream csum =
      new CheckedOutputStream(f, new Adler32());
     ZipOutputStream zos = new ZipOutputStream(csum);
    try(BufferedOutputStream out =
      new BufferedOutputStream(zos)) {
      zos.setComment("A test of Java Zipping");
      // No corresponding getComment(), though.
      for(String arg : args) {
        System.out.println("Writing file " + arg);
        try(InputStream in = new BufferedInputStream(
                new FileInputStream(arg))) {
          zos.putNextEntry(new ZipEntry(arg));
          int c;
          while((c = in.read()) != -1)
            out.write(c);
        }
        out.flush();
      }
    }
    // Checksum valid only after the file is closed!
    System.out.println("Checksum: " + csum.getChecksum().getValue());
    // Now extract the files:
    System.out.println("Reading file");
    FileInputStream fi = new FileInputStream("test.zip");
    CheckedInputStream csumi =
      new CheckedInputStream(fi, new Adler32());
    ZipInputStream in2 = new ZipInputStream(csumi);
    try(BufferedInputStream bis =
        new BufferedInputStream(in2)) {
      ZipEntry ze;
      while((ze = in2.getNextEntry()) != null) {
        System.out.println("Reading file " + ze);
        int x;
        while((x = bis.read()) != -1)
          System.out.write(x);
      }
      if(args.length == 1)
        System.out.println("Checksum: "+csumi.getChecksum().getValue());
    }
    // Alternative way to open and read Zip files:
    ZipFile zf = new ZipFile("test.zip");
    Enumeration e = zf.entries();
    while(e.hasMoreElements()) {
      ZipEntry ze2 = (ZipEntry)e.nextElement();
      System.out.println("File: " + ze2);
      // ... and extract the data as before
    }
    /* if(args.length == 1) */
  }
}
/* Output: (Execute to see) */
