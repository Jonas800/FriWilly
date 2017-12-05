import java.util.*;
import java.io.*;
public class KonkurrenceSvoemmer extends Medlem{
   private ArrayList<TraeningsResultat> TRList;
   private ArrayList<StaevneResultat> SRList;
   private String traener;
  
   public KonkurrenceSvoemmer(int id, String fornavn, String efternavn, String title, String fdatoString, boolean aktivitetsform, boolean erMotionist, boolean harBetalt, String traener, ArrayList<Resultat> alleResultater) throws Exception{
      super(id, fornavn, efternavn, title, fdatoString, aktivitetsform, erMotionist, harBetalt);
      this.traener = traener;
      this.SRList = new ArrayList<StaevneResultat>();
      this.TRList = new ArrayList<TraeningsResultat>();
      udfyldSRList(SRList, alleResultater);
      udfyldTRList(TRList, alleResultater);
   }
//    public KonkurrenceSvoemmer(Medlem m) throws Exception{
//       super(m.getID(), m.getFornavn(), m.getEfternavn(), m.getTitel(), (m.getFdato()).toString(), m.getAktivitetsform(), m.erMotionist());
//       this.SRList = new ArrayList<StaevneResultat>();
//       this.TRList = new ArrayList<TraeningsResultat>();
//       udfyldSRList(SRList, alleResultater);
//       udfyldTRList(TRList, alleResultater);
//    }
   
   private void udfyldSRList(ArrayList<StaevneResultat> list, ArrayList<Resultat> alleResultater) throws FileNotFoundException{
   
      for(Resultat r : alleResultater){
         if(r.getMedlemID() == super.getID()){
            if(r instanceof StaevneResultat){
               list.add((StaevneResultat)r);
            }
         }
      }
   }
   private void udfyldTRList(ArrayList<TraeningsResultat> list, ArrayList<Resultat> alleResultater) throws FileNotFoundException{
      
      for(Resultat r : alleResultater){
         if(r.getMedlemID() == super.getID()){
            if(r instanceof TraeningsResultat){
               list.add((TraeningsResultat)r);
            }
         }
      }
   }
   public void addToStaevneResultater(StaevneResultat sr){
      this.SRList.add(sr);
   }
   public void addToTraeningsResultater(TraeningsResultat tr){
      this.TRList.add(tr);
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