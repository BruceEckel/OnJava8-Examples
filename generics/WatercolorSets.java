// generics/WatercolorSets.java
import generics.watercolors.*;
import java.util.*;
import static onjava.Sets.*;
import static generics.watercolors.Watercolors.*;

public class WatercolorSets {
  public static void main(String[] args) {
    Set<Watercolors> set1 =
      EnumSet.range(BRILLIANT_RED, VIRIDIAN_HUE);
    Set<Watercolors> set2 =
      EnumSet.range(CERULEAN_BLUE_HUE, BURNT_UMBER);
    System.out.println("set1: " + set1);
    System.out.println("set2: " + set2);
    System.out.println("union(set1, set2): " + union(set1, set2));
    Set<Watercolors> subset = intersection(set1, set2);
    System.out.println("intersection(set1, set2): " + subset);
    System.out.println("difference(set1, subset): " +
      difference(set1, subset));
    System.out.println("difference(set2, subset): " +
      difference(set2, subset));
    System.out.println("complement(set1, set2): " +
      complement(set1, set2));
  }
}
/* Output:
set1: [BRILLIANT_RED, CRIMSON, MAGENTA, ROSE_MADDER,
VIOLET, CERULEAN_BLUE_HUE, PHTHALO_BLUE, ULTRAMARINE,
COBALT_BLUE_HUE, PERMANENT_GREEN, VIRIDIAN_HUE]
set2: [CERULEAN_BLUE_HUE, PHTHALO_BLUE, ULTRAMARINE,
COBALT_BLUE_HUE, PERMANENT_GREEN, VIRIDIAN_HUE, SAP_GREEN,
YELLOW_OCHRE, BURNT_SIENNA, RAW_UMBER, BURNT_UMBER]
union(set1, set2): [CERULEAN_BLUE_HUE, PHTHALO_BLUE,
VIOLET, YELLOW_OCHRE, RAW_UMBER, COBALT_BLUE_HUE,
BURNT_UMBER, ROSE_MADDER, VIRIDIAN_HUE, MAGENTA, SAP_GREEN,
BURNT_SIENNA, PERMANENT_GREEN, BRILLIANT_RED, CRIMSON,
ULTRAMARINE]
intersection(set1, set2): [CERULEAN_BLUE_HUE, PHTHALO_BLUE,
PERMANENT_GREEN, COBALT_BLUE_HUE, VIRIDIAN_HUE,
ULTRAMARINE]
difference(set1, subset): [MAGENTA, VIOLET, BRILLIANT_RED,
CRIMSON, ROSE_MADDER]
difference(set2, subset): [SAP_GREEN, YELLOW_OCHRE,
RAW_UMBER, BURNT_SIENNA, BURNT_UMBER]
complement(set1, set2): [VIOLET, YELLOW_OCHRE, RAW_UMBER,
BURNT_UMBER, ROSE_MADDER, MAGENTA, SAP_GREEN, BURNT_SIENNA,
BRILLIANT_RED, CRIMSON]
*/
