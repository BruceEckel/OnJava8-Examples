// preferences/PreferencesDemo.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.prefs.*;

public class PreferencesDemo {
  public static void
  main(String[] args) throws Exception {
    Preferences prefs = Preferences
      .userNodeForPackage(PreferencesDemo.class);
    prefs.put("Location", "Oz");
    prefs.put("Footwear", "Ruby Slippers");
    prefs.putInt("Companions", 4);
    prefs.putBoolean("Are there witches?", true);
    int usageCount = prefs.getInt("UsageCount", 0);
    usageCount++;
    prefs.putInt("UsageCount", usageCount);
    for(String key : prefs.keys())
      System.out.println(key + ": "
        + prefs.get(key, null));
    // You must always provide a default value:
    System.out.println(
      "How many companions does Dorothy have? " +
      prefs.getInt("Companions", 0));
  }
}
/* Output:
Location: Oz
Footwear: Ruby Slippers
Companions: 4
Are there witches?: true
UsageCount: 191
How many companions does Dorothy have? 4
___[ Error Output ]___
Jul 27, 2016 10:50:50 AM java.util.prefs.WindowsPreferences
<init>
WARNING: Could not open/create prefs root node
Software\JavaSoft\Prefs at root 0x80000002. Windows
RegCreateKeyEx(...) returned error code 5.
*/
