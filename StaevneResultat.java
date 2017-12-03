import java.time.LocalDate;
import java.util.*;
public class StaevneResultat extends Resultat{
   private String staevne; 
   
   public StaevneResultat(int id, int medlemID, String disciplin, double tid, String datoString, String staevne) {
      super(id, medlemID, tid, datoString, disciplin);
      this.staevne = staevne;
   }
 
   public String toString(){
      return super.getID() + " " + super.getMedlemID() + " " + super.getDisciplin() + " " + super.getTid() + " " + super.getDato() + " " + staevne;
   }
   public String getStaevne(){
      return staevne;
   }
}