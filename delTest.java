import java.util.*;
import java.time.LocalDate;
import java.io.*;
public class delTest {
   public static void main(String[] args) throws Exception{
      KonkurrenceSvoemmer svoemmer1 = new KonkurrenceSvoemmer(1, "Umaruuu", "Kondo", "1990-09-09", true, "Taihei");
      
      ArrayList<StaevneResultat> srList = svoemmer1.getStaevneResultater();
      System.out.println(svoemmer1);
      for(StaevneResultat sr : srList){
         System.out.println(sr);
      }
            ArrayList<KonkurrenceSvoemmer> ksList = new ArrayList<KonkurrenceSvoemmer>();

      while(true){
      registrerMedlemmer(ksList);
      
      for(KonkurrenceSvoemmer ks : ksList){
         System.out.println(ks);
      }}
      

// 
//       LocalDate d = LocalDate.parse("1990-09-09");
//       int alder = (int) java.time.temporal.ChronoUnit.YEARS.between(d, LocalDate.now());
//       System.out.println(alder);
//       System.out.println(LocalDate.now());
   }
   public static void registrerMedlemmer(ArrayList<KonkurrenceSvoemmer> list) throws Exception{
      Scanner console = new Scanner(System.in);
      
      System.out.println("Indtast fornavn");
      String fornavn = console.next();
      System.out.println("Indtast efternavn");
      String efternavn = console.next();
      System.out.println("Indtast fødselsdato (yyyy-MM-dd)");
      String fdato = console.next();
      System.out.println("Vælg om medlemmet er 1: aktivt eller 2: passivt");
      int aktivitetsformValg = console.nextInt();
      boolean aktivitetsform = aktivitetsformValg > 1 ? false : true; //ternary operation: er aktivitetsform = 1? hvis ja, return true, else return false
      System.out.println("Vælg om medlemmet er 1: motionist eller 2: konkurrencesvømmer");
      int medlemType = console.nextInt();
      
      if(medlemType == 1){
         Medlem medlem = new Medlem(1, fornavn, efternavn, fdato, aktivitetsform);
      }
      else{
         System.out.println("Indtast trænernavn");
         String traenerNavn = console.next();
         KonkurrenceSvoemmer ks = new KonkurrenceSvoemmer(1, fornavn, efternavn, fdato, aktivitetsform, traenerNavn);
         list.add(ks);
         
         saveKonkurrenceSvoemmere(list);
         
      }
      
      
   }
   public static void saveKonkurrenceSvoemmere(ArrayList<KonkurrenceSvoemmer> list) throws Exception{
      
      String s = "";
      for(KonkurrenceSvoemmer ks : list){
            s += ks.getID() + " " + ks.getFornavn() + " " + ks.getEfternavn() + " " + ks.getFdato() + " " + ks.getAktivitetsform() + "\r\n";
      }
      
      PrintStream output = new PrintStream(new File("konkurrencesvoemmer.txt"));
      output.print(s);
      output.close();
   }
}