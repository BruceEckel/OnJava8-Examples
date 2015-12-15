// generics/WatercolorSets.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
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
    System.out.println(
      "union(set1, set2): " + union(set1, set2));
    Set<Watercolors> subset = intersection(set1, set2);
    System.out.println(
      "intersection(set1, set2): " + subset);
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
union(set1, set2): [BRILLIANT_RED, BURNT_UMBER,
CERULEAN_BLUE_HUE, PHTHALO_BLUE, VIOLET, COBALT_BLUE_HUE,
CRIMSON, BURNT_SIENNA, ULTRAMARINE, PERMANENT_GREEN,
RAW_UMBER, VIRIDIAN_HUE, MAGENTA, SAP_GREEN, YELLOW_OCHRE,
ROSE_MADDER]
intersection(set1, set2): [ULTRAMARINE, PERMANENT_GREEN,
CERULEAN_BLUE_HUE, PHTHALO_BLUE, VIRIDIAN_HUE,
COBALT_BLUE_HUE]
difference(set1, subset): [BRILLIANT_RED, CRIMSON, MAGENTA,
ROSE_MADDER, VIOLET]
difference(set2, subset): [BURNT_SIENNA, BURNT_UMBER,
RAW_UMBER, SAP_GREEN, YELLOW_OCHRE]
complement(set1, set2): [BRILLIANT_RED, BURNT_UMBER,
VIOLET, CRIMSON, BURNT_SIENNA, RAW_UMBER, MAGENTA,
SAP_GREEN, YELLOW_OCHRE, ROSE_MADDER]
*/
