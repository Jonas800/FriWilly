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

}