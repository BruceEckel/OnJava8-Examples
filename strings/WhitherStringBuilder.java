// strings/WhitherStringBuilder.java
// ©2015 MindView LLC: see Copyright.txt

public class WhitherStringBuilder {
  public String implicit(String[] fields) {
    String result = "";
    for(String field : fields) {
      result += field;
    }
    return result;
  }
  public String explicit(String[] fields) {
    StringBuilder result = new StringBuilder();
    for(String field : fields) {
      result.append(field);
    }
    return result.toString();
  }
}
