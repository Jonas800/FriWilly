import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.io.*;

public class Medlem{
   
   private int id;
   private String navn;
   private LocalDate fdato;
   private double kontigent;
   private boolean aktivitetsform;
   
   public Medlem(int id, String navn, String fdatoString, boolean aktivitetsform){
      LocalDate fdato = LocalDate.parse(fdatoString);
      this.id = id;
      this.navn = navn;
      this.kontigent = getKontigent();
      this.aktivitetsform = aktivitetsform;
   }
   public void saveToFile(String filename)throws Exception{
      PrintStream output = new PrintStream(new File(filename));
      output.print(toString() + "\r\n");
   }
   public String toString(){
      return id + navn + fdato + kontigent + aktivitetsform;
   }

   public int getID(){
      return id;
   }
   public String getNavn(){
      return navn;
   }
   public LocalDate getFdato(){
      return fdato;
   }
   public int getAlder(){
      int alder = (int) ChronoUnit.YEARS.between(fdato, LocalDate.now());
      return alder;
   }
   
   public double getKontigent(){
      int alder = getAlder();
      double kontigent = 0;
      if(aktivitetsform = true){
         if (alder < 18){
            kontigent = 1000;
            
         }
         else if (alder >= 18 && alder < 60){
            kontigent = 1600;
         
         }  
         else{
            kontigent = (double) 1600*0.75;
         }
      }
      else{
         kontigent = 500;
      }
      return kontigent;
   }
   public boolean getAktivitetsform(){
      return aktivitetsform;
   }
   public void setID(int id){
      this.id = id;
   }
   public void setNavn(String navn){
      this.navn = navn;
   }
   public void setFdato(String fdatoString){
      LocalDate fdato = LocalDate.parse(fdatoString);
   }
   public void setKontigent(double kontigent){
      this.kontigent = kontigent;
   }
   public void setAktivitetsform(boolean aktivitetsform){
      this.aktivitetsform = aktivitetsform;
   }
}