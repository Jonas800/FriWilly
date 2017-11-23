import java.time.LocalDate;
public class StaevneResultat{
   private int medlemID;
   private String disciplin;
   private double tid;
   private LocalDate dato;
   private String staevne; 
   
   public StaevneResultat(int medlemID, String disciplin, double tid, String datoString, String staevne){
      this.dato = LocalDate.parse(datoString);
      this.medlemID = medlemID;
      this.disciplin = disciplin;
      this.tid = tid;
      this.staevne = staevne;
   }
   
   public String toString(){
      return medlemID + " " + disciplin + " " + tid + " " + staevne + " " + dato;
   }
}