import java.time.LocalDate;
import java.util.*;
public class StaevneResultat{
   private int medlemID;
   private String disciplin;
   private double tid;
   private LocalDate dato;
   private String staevne; 
   
   public StaevneResultat(int medlemID, String disciplin, double tid, String datoString, String staevne) {
      this.dato = LocalDate.parse(datoString);
      this.medlemID = medlemID;
      this.disciplin = disciplin;
      this.tid = tid;
      this.staevne = staevne;
   }
   public double getTid(){
      return tid;
   }
   public String toString(){
      return medlemID + " " + disciplin + " " + tid + " " + staevne + " " + dato;
   }
   //https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
   //Comparator til at sortere i tiden i en Collections.sort() metode. Tiden bliver sorteret faldende.
   public static Comparator<StaevneResultat> TidComparator = new Comparator<StaevneResultat>(){
      public int compare(StaevneResultat st1, StaevneResultat st2){
         Double double1 = st1.getTid();
         Double double2 = st2.getTid();
         
         return double2.compareTo(double1);
      }
   };
}