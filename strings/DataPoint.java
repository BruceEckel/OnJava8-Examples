// strings/DataPoint.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 15

public class DataPoint {
  private String location;
  private Double temperature;
  public DataPoint(String loc, Double temp) {
    location = loc;
    temperature = temp;
  }
  @Override public String toString() {
    return """
    Location: %s
    Temperature: %.2f
    """.formatted(location, temperature);
  }
  public static void main(String[] args) {
    var hill = new DataPoint("Hill", 45.2);
    var dale = new DataPoint("Dale", 65.2);
    System.out.print(hill);
    System.out.print(dale);
  }
}
/* Output:
Location: Hill
Temperature: 45.20
Location: Dale
Temperature: 65.20
*/
