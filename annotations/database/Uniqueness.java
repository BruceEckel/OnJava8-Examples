// annotations/database/Uniqueness.java
// ©2016 MindView LLC: see Copyright.txt
// Sample of nested annotations
package annotations.database;

public @interface Uniqueness {
  Constraints constraints()
    default @Constraints(unique=true);
}
