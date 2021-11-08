// housekeeping/ForTypeInference.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 11

public class ForTypeInference {
  public static void main(String[] args) {
    for(var s : Spiciness.values())
      System.out.println(s);
  }
}
/* Output:
NOT
MILD
MEDIUM
HOT
FLAMING
*/
