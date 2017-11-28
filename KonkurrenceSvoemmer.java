import java.util.*;
import java.io.*;
public class KonkurrenceSvoemmer extends Medlem{
   private ArrayList<TraeningsResultat> TRList;
   private ArrayList<StaevneResultat> SRList;
  
   public KonkurrenceSvoemmer(int id, String fornavn, String efternavn, String title, String fdatoString, boolean aktivitetsform, String traener, boolean erMotionist) throws Exception{
      super(id, fornavn, efternavn, title, fdatoString, aktivitetsform, traener, erMotionist);
      this.SRList = new ArrayList<StaevneResultat>();
      this.TRList = new ArrayList<TraeningsResultat>();
      udfyldSRList(SRList);
      udfyldTRList(TRList);
   }
   public KonkurrenceSvoemmer(Medlem m) throws Exception{
      super(m.getID(), m.getFornavn(), m.getEfternavn(), m.getTitel(), (m.getFdato()).toString(), m.getAktivitetsform(), m.getTraener(), m.erMotionist());
      this.SRList = new ArrayList<StaevneResultat>();
      this.TRList = new ArrayList<TraeningsResultat>();
      udfyldSRList(SRList);
      udfyldTRList(TRList);
   }
   
   private void udfyldSRList(ArrayList<StaevneResultat> list) throws FileNotFoundException{
      Scanner scanner = new Scanner(new File("staevneresultater.txt")).useLocale(Locale.GERMANY);
      while(scanner.hasNextLine()){
         String line = scanner.nextLine();
         Scanner data = new Scanner(line);
         int medlemID = data.nextInt();
         if(medlemID == super.getID()){
            StaevneResultat sr = new StaevneResultat(medlemID, data.next(), data.nextDouble(), data.next(), data.next());
            list.add(sr);
         }
      }
   }
   private void udfyldTRList(ArrayList<TraeningsResultat> list) throws FileNotFoundException{
      Scanner scanner = new Scanner(new File("traeningsresultater.txt"));
      while(scanner.hasNextLine()){
         String line = scanner.nextLine();
         Scanner data = new Scanner(line);
         int medlemID = data.nextInt();
         if(medlemID == super.getID()){
            TraeningsResultat tr = new TraeningsResultat(medlemID, data.next(), data.nextDouble(), data.next());
            list.add(tr);
         }
      }
   }
   
   public ArrayList<StaevneResultat> getStaevneResultater(){
      return SRList;
   }
   public ArrayList<TraeningsResultat> getTraeningsResultater(){
      return TRList;
   }
}