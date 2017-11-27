import java.time.LocalDate;
import java.util.*;
public class StaevneResultat extends Resultat{
   private String staevne; 
   
   public StaevneResultat(int medlemID, String disciplin, double tid, String datoString, String staevne) {
      super(medlemID, tid, datoString, disciplin);
      this.staevne = staevne;
   }
 
   public String toString(){
      return super.getMedlemID() + " " + super.getDisciplin() + " " + super.getTid() + " " + staevne + " " + super.getDato();
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