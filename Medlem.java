import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.io.*;

public class Medlem{
   
   private int id;
   private String navn;
   private int alder;
   private double kontigent;
   private boolean aktivitetsform;
   private LocalDate fdato;
   
   public Medlem(int id, String navn, String fdatoString, boolean aktivitetsform) throws Exception{
      this.alder = setAlder(fdatoString);
      this.fdato = LocalDate.parse(fdatoString);
      this.id = id;
      this.navn = navn;
      this.aktivitetsform = aktivitetsform;
      this.kontigent = getKontigent();
   }
   public void saveToFile(String filename)throws Exception{
      PrintStream output = new PrintStream(new File(filename));
      output.print(toString() + "\r\n");
   }
   public String toString(){
      return id + navn + alder + kontigent + aktivitetsform + fdato;
   }

   public int getID(){
      return id;
   }
   public String getNavn(){
      return navn;
   }
   public int setAlder(String fdatoString){
      LocalDate fdato = LocalDate.parse(fdatoString);
      int alder = (int) ChronoUnit.YEARS.between(fdato, LocalDate.now());
      return alder;
   }
   
   public double getKontigent() throws Exception{
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
   public void setKontigent(double kontigent){
      this.kontigent = kontigent;
   }
   public void setAktivitetsform(boolean aktivitetsform){
      this.aktivitetsform = aktivitetsform;
   }
   

}