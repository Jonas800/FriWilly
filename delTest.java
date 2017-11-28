import java.util.*;
import java.time.LocalDate;
import java.io.*;
public class delTest {
   public static void main(String[] args) throws Exception{
      

      ArrayList<Medlem> mList = new ArrayList<Medlem>();
      udfyldMedlemmer(mList);
      while(true){
      visMedlemmer(mList);
      
      visKonkurrenceSvoemmere(mList);
      
      registrerMedlemmer(mList);
      }
// 
//       LocalDate d = LocalDate.parse("1990-09-09");
//       int alder = (int) java.time.temporal.ChronoUnit.YEARS.between(d, LocalDate.now());
//       System.out.println(alder);
//       System.out.println(LocalDate.now());
   }
   public static void registrerMedlemmer(ArrayList<Medlem> mList) throws Exception{
      Scanner console = new Scanner(System.in);
      
      System.out.println("Indtast fornavn");
      String fornavn = console.next();
      System.out.println("Indtast efternavn");
      String efternavn = console.next();
      System.out.println("Indtast title");
      String titel = console.next();
      System.out.println("Indtast fødselsdato (yyyy-MM-dd)");
      String fdato = console.next();
      System.out.println("Vælg om medlemmet er 1: aktivt eller 2: passivt");
      int aktivitetsformValg = console.nextInt();
      boolean aktivitetsform = aktivitetsformValg == 1 ? true : false; //ternary operation: er aktivitetsform = 1? hvis ja, return true, else return false
      System.out.println("Vælg om medlemmet er 1: motionist eller 2: konkurrencesvømmer");
      boolean erMotionist = console.nextInt() == 1 ? true : false;
      
      if(erMotionist){
         Medlem medlem = new Medlem(mList.size() + 1, fornavn, efternavn, titel, fdato, aktivitetsform, null, erMotionist);
         mList.add(medlem);
         
         gemMedlem(mList);
      }
      else{
         System.out.println("Indtast trænernavn");
         String traenerNavn = console.next();
         Medlem m = new Medlem(mList.size() + 1, fornavn, efternavn, titel, fdato, aktivitetsform, traenerNavn, erMotionist);
         mList.add(m);
         
         gemMedlem(mList);
      }
   }
   public static void gemKonkurrenceSvoemmere(ArrayList<KonkurrenceSvoemmer> list) throws Exception{
      String s = "";
      for(KonkurrenceSvoemmer ks : list){
            s += ks.getID() + " " + ks.getFornavn() + " " + ks.getEfternavn() + " " + ks.getTitel() + " " + ks.getFdato() + " " + ks.getAktivitetsform() + " " + ks.getTraener() + "\r\n";
      }
      PrintStream output = new PrintStream(new File("konkurrencesvoemmer.txt"));
      output.print(s);
      output.close();
   }
   public static void gemMedlem(ArrayList<Medlem> list) throws Exception{
      String s = "";
      for(Medlem m : list){
            s += m.getID() + " " + m.getFornavn() + " " + m.getEfternavn() + " " + m.getTitel() + " " + " " + m.getFdato() + " " + m.getAktivitetsform() + " " + m.getTraener() + " " + m.erMotionist() + "\r\n";
      }
      PrintStream output = new PrintStream(new File("medlemmer.txt"));
      output.print(s);
      output.close();
   }
   
   public static void visMedlemmer(ArrayList<Medlem> mList){
      for(Medlem m : mList){
         String af = m.getAktivitetsform() == true ? "Aktivt" : "Passivt";
         System.out.println(m.getID() + " " + m.getFornavn() + " " + m.getEfternavn() + " " + m.getTitel() + " " + m.getFdato() + " " + af + " " + m.getTraener());
      }
//       for(KonkurrenceSvoemmer ks: ksList){
//          String af = ks.getAktivitetsform() == true ? "Aktivt" : "Passivt";
//          System.out.println(ks.getID() + " " + ks.getFornavn() + " " + ks.getEfternavn() + " " + ks.getTitel() + " " + ks.getFdato() + " " + af + " " + ks.getTraener());
//       }
   }
   
   public static void udfyldMedlemmer(ArrayList<Medlem> list) throws Exception{
      Scanner scanner = new Scanner(new File("medlemmer.txt"));

      while(scanner.hasNextLine()){
      
         String line = scanner.nextLine();
         Scanner data = new Scanner(line);
         int id = data.nextInt();
         String fornavn = data.next();
         String efternavn = data.next();
         String titel = data.next();
         String fdato = data.next();
         boolean aktivitetsform = data.nextBoolean();
         String traener = data.next();
         boolean erMotionist = data.nextBoolean();
         Medlem medlem = new Medlem(id, fornavn, efternavn, titel, fdato, aktivitetsform, traener, erMotionist);
         list.add(medlem);
      }
   }
   public static void visKonkurrenceSvoemmere(ArrayList<Medlem> mList) throws Exception{
      System.out.println("KS'ers");
      ArrayList<Resultat> resultatList = new ArrayList<Resultat>();

      for(Medlem m : mList){
         if(!m.erMotionist()){
            KonkurrenceSvoemmer ks = new KonkurrenceSvoemmer(m);
            ArrayList<StaevneResultat> srList = ks.getStaevneResultater();
            if(srList.size() > 0){
               System.out.println(ks);
               Collections.sort(srList, Resultat.TidComparator);
               
               for(StaevneResultat sr : srList){
                  System.out.println(sr);
               }
               //get bedste tid per svoemmer
               
               resultatList.add(srList.get(0));
            }
            ArrayList<TraeningsResultat> trList = ks.getTraeningsResultater();
            if(trList.size() > 0){
               Collections.sort(trList, Resultat.TidComparator);
               
               int index = Resultat.containsIDreturnIndex(resultatList, trList.get(0).getMedlemID());
               if(index >= 0){
                  //tjek for objecternes tid, fjern højeste
                  if(trList.get(0).getTid() < resultatList.get(index).getTid()){
                     resultatList.remove(index);
                     resultatList.add(trList.get(0));
                  }
               }
            }
         }
      }
      //mangler endnu en sort, men for doven. TODO. anime først! også filtrering af discipliner
      Collections.sort(resultatList, Resultat.TidComparator);
      
      System.out.println("top5");

      
      Resultat[] top5brystSvomning = new Resultat[5];
      Resultat[] top5hundeSvomning = new Resultat[5];
      Resultat[] top5rygcrawl = new Resultat[5];
      Resultat[] top5crawl = new Resultat[5];
      Resultat[] top5butterfly = new Resultat[5];
      
      int bs = 0;
      int rc = 0;
      int bf = 0;
      int c = 0;
      int hs = 0;
      for(Resultat r : resultatList){
         if(r.getDisciplin().equals("Brystsvomning") && bs < 5){
            top5brystSvomning[bs] = r;
            bs++;
         }
         if(r.getDisciplin().equals("Rygcrawl") && rc < 5){
            top5rygcrawl[rc] = r;
            rc++;
         }
         if(r.getDisciplin().equals("Butterfly") && bf < 5){
            top5butterfly[bf] = r;
            bf++;
         }
         if(r.getDisciplin().equals("Crawl") && c < 5){
            top5crawl[c] = r;
            c++;
         }
         if(r.getDisciplin().equals("Hundesvomning") && hs < 5){
            top5hundeSvomning[hs] = r;
            hs++;
         }
      }
      Arrays.sort(top5hundeSvomning, Resultat.TidComparator);
      Arrays.sort(top5rygcrawl, Resultat.TidComparator);
      Arrays.sort(top5butterfly, Resultat.TidComparator);
      Arrays.sort(top5crawl, Resultat.TidComparator);
      Arrays.sort(top5brystSvomning, Resultat.TidComparator);

      for(Resultat r : top5hundeSvomning){
         if(r != null){
            System.out.println(r);
         }
      }
      for(Resultat r : top5brystSvomning){
         if(r != null){
            System.out.println(r);
         }
      }
   }
   public static void visStaevneResultaterForEnSvoemmer(){
      
   }
   
}