import java.time.LocalDate;
import java.util.*;
import java.io.*;
public class Resultat{
   private int id;
   private double tid;
   private LocalDate dato;
   private int medlemID;
   private String disciplin;
   
   //https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
   //Comparator (som er et field(med en metode til opbyggelse indeni))
   //Bruges til at sortere i tiden i en Collections.sort() metode. Tiden bliver sorteret faldende.
   public static Comparator<Resultat> TidComparator = new Comparator<Resultat>(){
      public int compare(Resultat r1, Resultat r2){
         //nullværdi håndtering - vi antager at null er lig med uendeligt langsom svoemning
         //0 = de to værdier er ens
         if(r1 == null && r2 == null){
            return 0;
         }
         //1 = r1 er null, derfor højere end r2
         else if(r1 == null){
            return 1;
         }
         //-1 = r2 er null, derfor højere end r1
         else if(r2 == null){
            return -1;
         }
         else{
            Double double1 = r1.getTid();
            Double double2 = r2.getTid();
            return double1.compareTo(double2);
         }
      }
   };
   
   
   public Resultat(int id, int medlemID, double tid, String datoString, String disciplin){
      this.id = id;
      this.tid = tid;
      this.medlemID = medlemID;
      this.dato = LocalDate.parse(datoString);
      this.disciplin = disciplin;
   }
   public int getID(){
      return id;
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
   public void setTid(double tid){
      this.tid = tid;
   }
   
   public static int containsIDreturnIndex(ArrayList<Resultat> list, int id){
      for(Resultat r : list){
         if(r.getMedlemID() == id){
            return list.indexOf(r);
         }
      }
      return -1;
   }
   public static void gemResultater(ArrayList<Resultat> alleResultater) throws FileNotFoundException{
      String traeningsResultater = "";
      String staevneResultater = "";
      
      for(Resultat r : alleResultater){
         if(r instanceof TraeningsResultat){
            traeningsResultater += ((TraeningsResultat) r).toString() + "\r\n";
         }
         else if(r instanceof StaevneResultat){
            staevneResultater += ((StaevneResultat) r).toString() + "\r\n";
         }
      }
      
      PrintStream output = new PrintStream(new File("staevneresultater.txt"));
      output.print(staevneResultater);
      output.close();

      output = new PrintStream(new File("traeningsresultater.txt"));
      output.print(traeningsResultater);
      output.close();

   } 
}