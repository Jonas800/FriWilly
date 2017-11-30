import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.io.*;

public class Medlem{
   
   private int id;
   private String fornavn;
   private String efternavn;
   private String titel;
   private int alder;
   private double kontigent;
   private boolean aktivitetsform;
   private boolean erMotionist;
   private LocalDate fdato;
   
   public Medlem(int id, String fornavn, String efternavn, String titel, String fdatoString, boolean aktivitetsform, boolean erMotionist) throws Exception{
      this.alder = setAlder(fdatoString);
      this.fdato = LocalDate.parse(fdatoString);
      this.id = id;
      this.fornavn = fornavn;
      this.efternavn = efternavn;
      this.titel = titel;
      this.aktivitetsform = aktivitetsform;
      this.erMotionist = erMotionist;
      setKontigent();
   }
   public String toString(){
      return id + fornavn + efternavn + alder + kontigent + aktivitetsform + fdato;
   }

   public int getID(){
      return id;
   }
   public String getFornavn(){
      return fornavn;
   }
   public String getEfternavn(){
      return efternavn;
   }
   public LocalDate getFdato(){
      return fdato;
   }
   public String getTitel(){
      return titel;
   }

   public boolean erMotionist(){
      return erMotionist;
   }
   public int setAlder(String fdatoString){
      LocalDate fdato = LocalDate.parse(fdatoString);
      int alder = (int) ChronoUnit.YEARS.between(fdato, LocalDate.now());
      return alder;
   }
   
   public void setKontigent() throws Exception{
      double kontigent = 0;
      if(aktivitetsform == true){
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
      this.kontigent = kontigent;
   }
   
   public boolean getAktivitetsform(){
      return aktivitetsform;
   }
   public void setID(int id){
      this.id = id;
   }
   public void setFornavn(String fornavn){
      this.fornavn = fornavn;
   }
   public void setEfternavn(String efternavn){
      this.efternavn = efternavn;
   }
   public void setKontigent(double kontigent){
      this.kontigent = kontigent;
   }
   public void setAktivitetsform(boolean aktivitetsform){
      this.aktivitetsform = aktivitetsform;
   }
   

}