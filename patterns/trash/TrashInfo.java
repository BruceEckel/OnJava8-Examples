// patterns/trash/TrashInfo.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Messenger class carrying Trash creation data.
package patterns.trash;

public class TrashInfo {
  public final String type;
  public final double data;
  public TrashInfo(String type, double data) {
    this.type = type;
    this.data = data;
  }
  @Override public String toString() {
    return "TrashInfo(" + type + ", " + data + ")";
  }
}
