// preferences/PreferencesDemo.java
import java.util.prefs.*;

public class PreferencesDemo {
  public static void main(String[] args) throws Exception {
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
      System.out.println(key + ": "+ prefs.get(key, null));
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
UsageCount: 33
How many companions does Dorothy have? 4
*/
