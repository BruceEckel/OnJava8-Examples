// serialization/Logon.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Demonstrates the "transient" keyword

import java.io.*;
import java.util.Date;

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
        return "logon info: \n   username: " +
            username + "\n   date: " + date +
            "\n   password: " + password;
    }

    public static void main(String[] args) {
        Logon a = new Logon("Hulk", "myLittlePony");
        System.out.println("logon a = " + a);
        try (
            ObjectOutputStream o =
                new ObjectOutputStream(
                    new FileOutputStream("Logon.txt"))
        ) {
            o.writeObject(a);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//    new Nap(1);
        // Now get them back:
        try (
            ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("Logon.txt"))
        ) {
            System.out.println(
                "Recovering object at " + new Date());
            a = (Logon) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("logon a = " + a);
    }
}
/* Output:
logon a = logon info:
   username: Hulk
   date: Sun Jan 24 08:49:30 MST 2021
   password: myLittlePony
Recovering object at Sun Jan 24 08:49:31 MST 2021
logon a = logon info:
   username: Hulk
   date: Sun Jan 24 08:49:30 MST 2021
   password: null
*/

// My code
class TransientTest implements Serializable {
    transient private int age;
    private String name;
    transient private String password;

    public TransientTest(Integer age, String name, String psw) {
        this.age = age;
        this.name = name;
        this.password = psw;
    }

    public String toString() {
        return "{ age = " + age + ", name = " + name + ", psw = " + password + " }";
    }

    public static void main(String[] args) {
        TransientTest t = new TransientTest(24, "yangwu", "185530");
        System.out.println("Original: " + t);
        try (
            ObjectOutputStream o =
                new ObjectOutputStream(
                    new FileOutputStream("person.txt"))
        ) {
            o.writeObject(t);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Recovering from disk");
        try (
            ObjectInputStream in =
                new ObjectInputStream(
                    new FileInputStream("person.txt"))
        ) {
            t = (TransientTest) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Recovering: " + t);
    }
}
