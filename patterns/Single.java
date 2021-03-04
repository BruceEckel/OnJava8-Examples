// patterns/Single.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.

@SuppressWarnings("unchecked")
public final class Single<T> {
  private static Object single;       // [1]
  public Single(T val) {
    if(single != null)
      throw new RuntimeException(
        "Attempt to reassign Single<" +
        val.getClass().getSimpleName() + ">"
      );
    single = val;
  }
  public T get() { return (T)single; }
}
