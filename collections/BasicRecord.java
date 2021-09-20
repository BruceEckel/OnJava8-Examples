// collections/BasicRecord.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 16
import java.util.*;

record Employee(String name, int id) {}

public class BasicRecord {
  public static void main(String[] args) {
    var bob = new Employee("Bob Dobbs", 11);
    var dot = new Employee("Dorothy Gale", 9);
    // bob.id = 12; // Error:
    // id has private access in Employee
    System.out.println(bob.name()); // Accessor
    System.out.println(bob.id()); // Accessor
    System.out.println(bob); // toString()
    // Employee works as the key in a Map:
    var map = Map.of(bob, "A", dot, "B");
    System.out.println(map);
  }
}
/* Output:
Bob Dobbs
11
Employee[name=Bob Dobbs, id=11]
{Employee[name=Dorothy Gale, id=9]=B, Employee[name=Bob Dobbs, id=11]=A}
*/
