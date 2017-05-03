// generics/WatercolorSets.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
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
union(set1, set2): [BURNT_UMBER, MAGENTA, RAW_UMBER,
VIOLET, BURNT_SIENNA, ULTRAMARINE, CRIMSON,
PERMANENT_GREEN, YELLOW_OCHRE, BRILLIANT_RED, PHTHALO_BLUE,
CERULEAN_BLUE_HUE, SAP_GREEN, COBALT_BLUE_HUE, ROSE_MADDER,
VIRIDIAN_HUE]
intersection(set1, set2): [ULTRAMARINE, PERMANENT_GREEN,
PHTHALO_BLUE, COBALT_BLUE_HUE, CERULEAN_BLUE_HUE,
VIRIDIAN_HUE]
difference(set1, subset): [MAGENTA, VIOLET, CRIMSON,
BRILLIANT_RED, ROSE_MADDER]
difference(set2, subset): [SAP_GREEN, BURNT_UMBER,
RAW_UMBER, BURNT_SIENNA, YELLOW_OCHRE]
complement(set1, set2): [BURNT_UMBER, MAGENTA, RAW_UMBER,
VIOLET, BURNT_SIENNA, CRIMSON, YELLOW_OCHRE, BRILLIANT_RED,
SAP_GREEN, ROSE_MADDER]
*/
