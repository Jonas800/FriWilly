import java.util.*;
import java.io.*;
public class KonkurrenceSvoemmer extends Medlem{
   private ArrayList<TraeningsResultat> trList;
   private ArrayList<StaevneResultat> stList;
   private String traener;
  
   public KonkurrenceSvoemmer(int id, String navn, String fdatoString, boolean aktivitetsform, String traener){
      super(id, navn, fdatoString, aktivitetsform);
      this.traener = traener;
      this.stList = new ArrayList<StaevneResultat>();
      this.trList = new ArrayList<TraeningsResultat>();
      udfyldStList(stList);
      udfyldTrList(trList);
   }
   
   private void udfyldStList(ArrayList<StaevneResultat> list){
      Scanner scanner = new Scanner(new File("staevneresultater.txt"));
      
   }
   private void udfyldTrList(ArrayList<TraeningsResultat> list){
      Scanner scanner = new Scanner(new File("traeningsresultater.txt"));

   }
   
}