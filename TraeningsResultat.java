import java.time.LocalDate;
public class TraeningsResultat{
   private double tid;
   private LocalDate dato;
   private int medlemID;
   
   public TraeningsResultat(double tid, String datoString){
      this.tid = tid;
      LocalDate dato = LocalDate.parse(datoString);
   }
}