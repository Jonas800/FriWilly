import java.util.*;
import java.time.LocalDate;
public class delTest {
   public static void main(String[] args) throws Exception{
      KonkurrenceSvoemmer svoemmer1 = new KonkurrenceSvoemmer(1, "Umaruuu", "1990-09-09", true, "Taihei");
      
      ArrayList<StaevneResultat> SRList = svoemmer1.getStaevneResultater();
      System.out.println(svoemmer1);
      for(StaevneResultat sr : SRList){
         System.out.println(sr);
      }

      LocalDate d = LocalDate.parse("1990-09-09");
      int alder = (int) java.time.temporal.ChronoUnit.YEARS.between(d, LocalDate.now());
      System.out.println(alder);
      System.out.println(LocalDate.now());
   }
}