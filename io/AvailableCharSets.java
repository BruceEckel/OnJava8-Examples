//: io/AvailableCharSets.java
// ©2015 MindView LLC: see Copyright.txt
// Displays Charsets and aliases
import java.nio.charset.*;
import java.util.*;
import static net.mindview.util.Print.*;

public class AvailableCharSets {
  public static void main(String[] args) {
    SortedMap<String,Charset> charSets =
      Charset.availableCharsets();
    for(String csName : charSets.keySet()) {
      printnb(csName);
      Iterator aliases = charSets.get(csName)
        .aliases().iterator();
      if(aliases.hasNext())
        printnb(": ");
      while(aliases.hasNext()) {
        printnb(aliases.next());
        if(aliases.hasNext())
          printnb(", ");
      }
      print();
    }
  }
} /* Output: (First 7 Lines)
Big5: csBig5
Big5-HKSCS: big5-hkscs:unicode3.0, Big5_HKSCS, big5-hkscs, big5hkscs, big5hk
EUC-JP: eucjis, Extended_UNIX_Code_Packed_Format_for_Japanese, x-eucjp, eucjp, csEUCPkdFmtjapanese, euc_jp, x-euc-jp
EUC-KR: 5601, ksc5601-1987, ksc5601_1987, euckr, ksc5601, ksc_5601, ks_c_5601-1987, euc_kr, csEUCKR
GB18030: gb18030-2000
GB2312: euc-cn, x-EUC-CN, gb2312-1980, gb2312, gb2312-80, euccn, EUC_CN
GBK: CP936, windows-936
...
*///:~
