import java.io.*;

public class Ansatte{
   private String titel;
   private String forNavn;
   private String efterNavn;
   
   
   public Ansatte(String titel, String forNavn, String efterNavn{
      this.title = titel;
      this.forNavn = forNavn;
      this.lastName = lastName;
   }
   
   public void gemIFil(String filename)throws Exception{
      PrintStream output = new PrintStream(new FileOutputStream(filename));
      output.print(toString() + "\r\n"); 
   }
   
   public String toString(){
      return titel + " " + forNavn + " " + efterNavn;
   }
   
   public void setEfterNavn(String efterNavn){
      this.efterNavn = efterNavn;
   }
   
   public void setTitel(String titel){
      this.titel = titel;
   }
   
   public String getTitel(){
      return titel;
   }
   
   public String getForNavn(){
      return forNavn;
   }
   
   public String getEfterNavn(){
      return efterNavn;
   }


   
}