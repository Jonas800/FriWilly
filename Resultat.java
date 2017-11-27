import java.time.LocalDate;
public class Resultat{
   private double tid;
   private LocalDate dato;
   private int medlemID;
   private String disciplin;
   
   public Resultat(int medlemID, double tid, String datoString, String disciplin){
      this.tid = tid;
      this.medlemID = medlemID;
      this.dato = LocalDate.parse(datoString);
      this.disciplin = disciplin;
   }
   public double getTid(){
      return tid;
   }
   public int getMedlemID(){
      return medlemID;
   }
   public LocalDate getDato(){
      return dato;
   }
   public String getDisciplin(){
      return disciplin;
   }
}