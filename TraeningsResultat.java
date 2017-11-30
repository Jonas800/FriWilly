import java.time.LocalDate;
public class TraeningsResultat extends Resultat{
   
   public TraeningsResultat(int id, int medlemID, String disciplin, double tid, String datoString){
      super(id, medlemID, tid, datoString, disciplin);
   }
   
   public String toString(){
      return super.getID() + " " + super.getMedlemID() + " " + super.getDisciplin() + " " + super.getTid() + " " + super.getDato();
   }
}