// generics/WatercolorSets.java
// (c)2021 MindView LLC: see Copyright.txt
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
COBALT_BLUE_HUE, PERMANENT_GREEN, VIRIDIAN_HUE,
SAP_GREEN, YELLOW_OCHRE, BURNT_SIENNA, RAW_UMBER,
BURNT_UMBER]
union(set1, set2): [MAGENTA, COBALT_BLUE_HUE, VIOLET,
VIRIDIAN_HUE, BURNT_SIENNA, ULTRAMARINE,
CERULEAN_BLUE_HUE, BURNT_UMBER, BRILLIANT_RED,
PHTHALO_BLUE, YELLOW_OCHRE, SAP_GREEN, CRIMSON,
ROSE_MADDER, RAW_UMBER, PERMANENT_GREEN]
intersection(set1, set2): [COBALT_BLUE_HUE,
VIRIDIAN_HUE, ULTRAMARINE, CERULEAN_BLUE_HUE,
PHTHALO_BLUE, PERMANENT_GREEN]
difference(set1, subset): [CRIMSON, MAGENTA, VIOLET,
ROSE_MADDER, BRILLIANT_RED]
difference(set2, subset): [BURNT_SIENNA, BURNT_UMBER,
YELLOW_OCHRE, RAW_UMBER, SAP_GREEN]
complement(set1, set2): [MAGENTA, VIOLET, BURNT_SIENNA,
BURNT_UMBER, BRILLIANT_RED, YELLOW_OCHRE, SAP_GREEN,
CRIMSON, ROSE_MADDER, RAW_UMBER]
*/
