import java.util.*;
import java.io.*;
public class KonkurrenceSvoemmer extends Medlem{
   private ArrayList<TraeningsResultat> TRList;
   private ArrayList<StaevneResultat> SRList;
   private String traener;
  
   public KonkurrenceSvoemmer(int id, String fornavn, String efternavn, String fdatoString, boolean aktivitetsform, String traener) throws Exception{
      super(id, fornavn, efternavn, fdatoString, aktivitetsform);
      this.traener = traener;
      this.SRList = new ArrayList<StaevneResultat>();
      this.TRList = new ArrayList<TraeningsResultat>();
      udfyldSRList(SRList);
      udfyldTRList(TRList);
   }
   
   private void udfyldSRList(ArrayList<StaevneResultat> list) throws FileNotFoundException{
      Scanner scanner = new Scanner(new File("staevneresultater.txt")).useLocale(Locale.GERMANY);
      while(scanner.hasNextLine()){
         int medlemID = scanner.nextInt();
         if(medlemID == super.getID()){
            StaevneResultat sr = new StaevneResultat(medlemID, scanner.next(), scanner.nextDouble(), scanner.next(), scanner.next());
            list.add(sr);
         }
         else{
            scanner.nextLine();
         }
      }
   }
   private void udfyldTRList(ArrayList<TraeningsResultat> list) throws FileNotFoundException{
      Scanner scanner = new Scanner(new File("traeningsresultater.txt"));
      while(scanner.hasNextLine()){
         int medlemID = scanner.nextInt();
         if(medlemID == super.getID()){
            TraeningsResultat tr = new TraeningsResultat(medlemID, scanner.nextDouble(), scanner.next());
            list.add(tr);
         }
         else{
            scanner.nextLine();
         }
      }
   }
   
   public ArrayList<StaevneResultat> getStaevneResultater(){
      return SRList;
   }
   public ArrayList<TraeningsResultat> getTraeningsResultater(){
      return TRList;
   }
   public String getTraener(){
      return traener;
   }
}