// patterns/Resource.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.

public interface Resource<T> {
  T get();
  void set(T x);
}
