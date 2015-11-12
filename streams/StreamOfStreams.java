// streams/StreamOfStreams.java
import java.util.stream.*;

public class StreamOfStreams {
  public static void main(String[] args) {
    Stream.of(1, 2, 3)
      .map(i -> Stream.of("Gonzo", "Kermit", "Beaker"))
      .map(e-> e.getClass().getName())
      .forEach(System.out::println);
  }
}
/* Output:
java.util.stream.ReferencePipeline$Head
java.util.stream.ReferencePipeline$Head
java.util.stream.ReferencePipeline$Head
*/
