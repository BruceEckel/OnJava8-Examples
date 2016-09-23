// enums/menu/Meal.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {java enums.menu.Meal}
package enums.menu;

public class Meal {
  public static void main(String[] args) {
    for(int i = 0; i < 5; i++) {
      for(Course course : Course.values()) {
        Food food = course.randomSelection();
        System.out.println(food);
      }
      System.out.println("---");
    }
  }
}
/* Output:
SALAD
VINDALOO
GELATO
CAPPUCCINO
---
SPRING_ROLLS
BURRITO
GELATO
HERB_TEA
---
SPRING_ROLLS
BURRITO
TIRAMISU
CAPPUCCINO
---
SPRING_ROLLS
BURRITO
BLACK_FOREST_CAKE
LATTE
---
SALAD
PAD_THAI
GELATO
TEA
---
*/
