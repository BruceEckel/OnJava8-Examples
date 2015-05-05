//: innerclasses/LambdaExpressions.java

interface Description {
  String brief();
}

public class LambdaExpressions {
  Description desc = new Description() {
    @Override
    public String brief() { 
      return "Short info"; 
    }
  };
  Description desc2 = () -> "Short info";  
} ///:~
