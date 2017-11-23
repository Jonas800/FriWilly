import java.time.LocalDate;
public class TraeningsResultat{
   private double tid;
   private LocalDate dato;
   private int medlemID;
   
   public TraeningsResultat(int medlemID, double tid, String datoString){
      this.tid = tid;
      this.medlemID = medlemID;
      this.dato = LocalDate.parse(datoString);
   }
}