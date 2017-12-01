import java.util.*;
import java.time.LocalDate;
import java.io.*;
public class delTest {
   public static void main(String[] args) throws Exception{
      
      ArrayList<Resultat> alleResultater = new ArrayList<Resultat>();
      udfyldResultater(alleResultater);
      ArrayList<Medlem> mList = new ArrayList<Medlem>();
      udfyldMedlemmer(mList, alleResultater);

      
      // for(Resultat r : alleResultater){
//       if(r instanceof StaevneResultat){
//          System.out.println(((StaevneResultat) r).getStaevne());
//       }}
      while(true){
      visMedlemmer(mList);
      
      visKonkurrenceSvoemmere(mList);
      
      registrerMedlemmer(mList, alleResultater);
      }
// 
//       LocalDate d = LocalDate.parse("1990-09-09");
//       int alder = (int) java.time.temporal.ChronoUnit.YEARS.between(d, LocalDate.now());
//       System.out.println(alder);
//       System.out.println(LocalDate.now());
   }
   public static void udfyldResultater(ArrayList<Resultat> list) throws Exception{
      Scanner scanner = new Scanner(new File("staevneresultater.txt"));
      while(scanner.hasNextLine()){
         String line = scanner.nextLine();
         Scanner data = new Scanner(line).useLocale(Locale.GERMANY);
         int medlemID = data.nextInt();
         StaevneResultat sr = new StaevneResultat(list.size() + 1, medlemID, data.next(), data.nextDouble(), data.next(), data.next());
         list.add(sr);
      }
      scanner = new Scanner(new File("traeningsresultater.txt"));
      while(scanner.hasNextLine()){
         String line = scanner.nextLine();
         Scanner data = new Scanner(line).useLocale(Locale.GERMANY);
         int medlemID = data.nextInt();
         TraeningsResultat tr = new TraeningsResultat(list.size() + 1, medlemID, data.next(), data.nextDouble(), data.next());
         list.add(tr);
      }
   }
   
   public static void registrerMedlemmer(ArrayList<Medlem> mList, ArrayList<Resultat> alleResultater) throws Exception{
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
      boolean aktivitetsform = aktivitetsformValg == 1 ? true : false; //ternary operation: er aktivitetsformValg = 1? hvis ja, return true, else return false
      System.out.println("Vælg om medlemmet er 1: motionist eller 2: konkurrencesvømmer");
      boolean erMotionist = console.nextInt() == 1 ? true : false;
      
      if(erMotionist){
         Medlem medlem = new Medlem(mList.size() + 1, fornavn, efternavn, titel, fdato, aktivitetsform, erMotionist);
         mList.add(medlem);
         
      }
      else{
         System.out.println("Indtast trænernavn");
         String traenerNavn = console.next();
         KonkurrenceSvoemmer m = new KonkurrenceSvoemmer(mList.size() + 1, fornavn, efternavn, titel, fdato, aktivitetsform, erMotionist, traenerNavn, alleResultater);
         mList.add(m);
      }
      gemMedlem(mList);

   }

   public static void gemMedlem(ArrayList<Medlem> list) throws Exception{
      String s = "";
      for(Medlem m : list){
         if(m instanceof KonkurrenceSvoemmer){
            s += m.getID() + " " + m.getFornavn() + " " + m.getEfternavn() + " " + m.getTitel() + " " + " " + m.getFdato() + " " + m.getAktivitetsform() + " " + ((KonkurrenceSvoemmer) m).getTraener() + " " + m.erMotionist() + "\r\n";
         }
         else{
            s += m.getID() + " " + m.getFornavn() + " " + m.getEfternavn() + " " + m.getTitel() + " " + " " + m.getFdato() + " " + m.getAktivitetsform() + " " + m.erMotionist() + "\r\n";
         }
      }
      PrintStream output = new PrintStream(new File("medlemmer.txt"));
      output.print(s);
      output.close();
   }
   
   public static void visMedlemmer(ArrayList<Medlem> mList){
      System.out.println("ALLE MEDLEMMER");
      for(Medlem m : mList){
         String af = m.getAktivitetsform() == true ? "Aktivt" : "Passivt"; //ternary operation: er aktivitetsform = true? hvis ja, return en String med Aktivt, else return en String med passivt
         if(m instanceof KonkurrenceSvoemmer){
            System.out.println(m.getID() + " " + m.getFornavn() + " " + m.getEfternavn() + " " + m.getTitel() + " " + " " + m.getFdato() + " " + m.getAktivitetsform() + " " + ((KonkurrenceSvoemmer) m).getTraener() + " " + m.erMotionist());
         }
         else{
            System.out.println(m.getID() + " " + m.getFornavn() + " " + m.getEfternavn() + " " + m.getTitel() + " " + " " + m.getFdato() + " " + m.getAktivitetsform() + " " + m.erMotionist());
         }
      }
//       for(KonkurrenceSvoemmer ks: ksList){
//          String af = ks.getAktivitetsform() == true ? "Aktivt" : "Passivt";
//          System.out.println(ks.getID() + " " + ks.getFornavn() + " " + ks.getEfternavn() + " " + ks.getTitel() + " " + ks.getFdato() + " " + af + " " + ks.getTraener());
//       }
   }
   
   public static void udfyldMedlemmer(ArrayList<Medlem> list, ArrayList<Resultat> alleResultater) throws Exception{
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
         
         if(erMotionist){
            list.add(new Medlem(id, fornavn, efternavn, titel, fdato, aktivitetsform, erMotionist));
         }
         else
         {
            list.add(new KonkurrenceSvoemmer(id, fornavn, efternavn, titel, fdato, aktivitetsform, erMotionist, traener, alleResultater));
         }
      }
   }
   public static void visKonkurrenceSvoemmere(ArrayList<Medlem> mList) throws Exception{
      System.out.println("KS'ers + resultater");
      ArrayList<Resultat> resultatList = new ArrayList<Resultat>();
      //ArrayLists for bedste resultat for alle medlemmer for hver disciplin
      ArrayList<Resultat> bedsteBrystsvoemning = new ArrayList<Resultat>();
      ArrayList<Resultat> bedsteHundesvoemning = new ArrayList<Resultat>();
      ArrayList<Resultat> bedsteRygcrawl = new ArrayList<Resultat>();
      ArrayList<Resultat> bedsteCrawl = new ArrayList<Resultat>();
      ArrayList<Resultat> bedsteButterfly = new ArrayList<Resultat>();
      
      
      
      for(Medlem m : mList){
         if(m instanceof KonkurrenceSvoemmer){
            ArrayList<StaevneResultat> srList = ((KonkurrenceSvoemmer) m).getStaevneResultater();
            if(srList.size() > 0){
               System.out.println(m);
               //Collections.sort(srList, Resultat.TidComparator);
               
               //Print bedste resultat for hver disciplin for et medlem
               for(StaevneResultat sr : srList){
                  System.out.println(sr);
                  
                  if(sr.getDisciplin().equals("Brystsvomning"){
                     bedsteBrystsvoemning.add(sr);
                  }
                  if(sr.getDisciplin().equals("Hundesvomning"){
                     bedsteHundesvoemning.add(sr);
                  }
                  if(sr.getDisciplin().equals("bedsteRygcrawl"){
                     bedsteRygcrawl.add(sr);
                  }
               }
               //get bedste tid per svoemmer
               
               resultatList.add(srList.get(0));
            }
            ArrayList<TraeningsResultat> trList = ((KonkurrenceSvoemmer) m).getTraeningsResultater();
            if(trList.size() > 0){
               Collections.sort(trList, Resultat.TidComparator);
               
               //Get index hvor id går igen
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
      for(Resultat r : top5rygcrawl){
         if(r != null){
            System.out.println(r);
         }
      }
   }
   public static void visStaevneResultaterForEnSvoemmer(){
      
   }
   
}