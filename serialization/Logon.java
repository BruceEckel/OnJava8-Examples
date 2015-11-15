// serialization/Logon.java
// ©2016 MindView LLC: see Copyright.txt
// Demonstrates the "transient" keyword.
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
  public static void main(String[] args) throws Exception {
    Logon a = new Logon("Hulk", "myLittlePony");
    System.out.println("logon a = " + a);
    try(ObjectOutputStream o = new ObjectOutputStream(
         new FileOutputStream("Logon.out"))) {
      o.writeObject(a);
    }
    TimeUnit.SECONDS.sleep(1); // Delay
    // Now get them back:
    ObjectInputStream in = new ObjectInputStream(
      new FileInputStream("Logon.out"));
    System.out.println("Recovering object at " + new Date());
    a = (Logon)in.readObject();
    System.out.println("logon a = " + a);
  }
}
/* Output:
logon a = logon info:
   username: Hulk
   date: Mon Jun 15 15:47:51 PDT 2015
   password: myLittlePony
Recovering object at Mon Jun 15 15:47:52 PDT 2015
logon a = logon info:
   username: Hulk
   date: Mon Jun 15 15:47:51 PDT 2015
   password: null
*/
