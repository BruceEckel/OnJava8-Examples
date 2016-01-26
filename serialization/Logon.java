// serialization/Logon.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Demonstrates the "transient" keyword
import java.util.concurrent.*;
import java.io.*;
import java.util.*;

public class Logon implements Serializable {
  private Date date = new Date();
  private String username;
  private transient String password;
  public Logon(String name, String pwd) {
    username = name;
    password = pwd;
  }
  @Override
  public String toString() {
    return "logon info: \n   username: " + username +
      "\n   date: " + date + "\n   password: " + password;
  }
  public static void
  main(String[] args) throws Exception {
    Logon a = new Logon("Hulk", "myLittlePony");
    System.out.println("logon a = " + a);
    try(ObjectOutputStream o = new ObjectOutputStream(
          new FileOutputStream("Logon.dat"))) {
      o.writeObject(a);
    }
    TimeUnit.SECONDS.sleep(1); // Delay
    // Now get them back:
    try(ObjectInputStream in = new ObjectInputStream(
          new FileInputStream("Logon.dat"))) {
      System.out.println(
        "Recovering object at " + new Date());
      a = (Logon)in.readObject();
    }
    System.out.println("logon a = " + a);
  }
}
/* Output:
logon a = logon info:
   username: Hulk
   date: Tue Dec 08 16:09:48 PST 2015
   password: myLittlePony
Recovering object at Tue Dec 08 16:09:49 PST 2015
logon a = logon info:
   username: Hulk
   date: Tue Dec 08 16:09:48 PST 2015
   password: null
*/
