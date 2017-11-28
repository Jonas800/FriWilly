import java.time.LocalDate;
public class TraeningsResultat extends Resultat{
   
   public TraeningsResultat(int medlemID, String disciplin, double tid, String datoString){
      super(medlemID, tid, datoString, disciplin);
   }
   
   public String toString(){
      return super.getMedlemID() + " " + super.getDisciplin() + " " + super.getTid() + " " + super.getDato();
   }
}