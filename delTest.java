import java.util.*;
import java.time.LocalDate;
import java.io.*;
public class delTest {
   public static void main(String[] args) throws Exception{
      
      ArrayList<Resultat> alleResultater = new ArrayList<Resultat>();
      udfyldResultater(alleResultater);
      ArrayList<Medlem> alleMedlemmer = new ArrayList<Medlem>();
      udfyldMedlemmer(alleMedlemmer, alleResultater);

      
      while(true){
         visMedlemmer(alleMedlemmer);
         
         visKonkurrenceSvoemmereOgResultater(alleMedlemmer);
         
         // seTop5(alleMedlemmer, "Brystsvomning");
//          seTop5(alleMedlemmer, "Hundesvomning");
//          seTop5(alleMedlemmer, "Butterfly");
//          seTop5(alleMedlemmer, "Rygcrawl");
//          seTop5(alleMedlemmer, "Crawl");
         
         opretNytResultat(alleMedlemmer, alleResultater);
         //registrerMedlemmer(alleMedlemmer, alleResultater);
      }
   }
   public static void udfyldResultater(ArrayList<Resultat> list) throws Exception{
      Scanner scanner = new Scanner(new File("staevneresultater.txt"));
      while(scanner.hasNextLine()){
         String line = scanner.nextLine();
         Scanner data = new Scanner(line).useLocale(Locale.US);
         StaevneResultat sr = new StaevneResultat(data.nextInt(), data.nextInt(), data.next(), data.nextDouble(), data.next(), data.next());
         list.add(sr);
      }
      scanner = new Scanner(new File("traeningsresultater.txt"));
      while(scanner.hasNextLine()){
         String line = scanner.nextLine();
         Scanner data = new Scanner(line).useLocale(Locale.US);
         TraeningsResultat tr = new TraeningsResultat(data.nextInt(), data.nextInt(), data.next(), data.nextDouble(), data.next());
         list.add(tr);
      }
   }
   
   public static void registrerMedlemmer(ArrayList<Medlem> alleMedlemmer, ArrayList<Resultat> alleResultater) throws Exception{
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
         Medlem medlem = new Medlem(alleMedlemmer.size() + 1, fornavn, efternavn, titel, fdato, aktivitetsform, erMotionist);
         alleMedlemmer.add(medlem);
         
      }
      else{
         System.out.println("Indtast trænernavn");
         String traenerNavn = console.next();
         KonkurrenceSvoemmer m = new KonkurrenceSvoemmer(alleMedlemmer.size() + 1, fornavn, efternavn, titel, fdato, aktivitetsform, erMotionist, traenerNavn, alleResultater);
         alleMedlemmer.add(m);
      }
      gemMedlem(alleMedlemmer);

   }

   public static void gemMedlem(ArrayList<Medlem> list) throws Exception{
      String s = "";
      for(Medlem m : list){
         if(m instanceof KonkurrenceSvoemmer){
            s += m.getID() + " " + m.getFornavn() + " " + m.getEfternavn() + " " + m.getTitel() + " " + " " + m.getFdato() + " " + m.getAktivitetsform() + " " + m.erMotionist() + " " + ((KonkurrenceSvoemmer) m).getTraener() + "\r\n";
         }
         else{
            s += m.getID() + " " + m.getFornavn() + " " + m.getEfternavn() + " " + m.getTitel() + " " + " " + m.getFdato() + " " + m.getAktivitetsform() + " " + m.erMotionist() + "\r\n";
         }
      }
      PrintStream output = new PrintStream(new File("medlemmer.txt"));
      output.print(s);
      output.close();
   }
   
   public static void visMedlemmer(ArrayList<Medlem> alleMedlemmer){
      System.out.println("ALLE MEDLEMMER");
      for(Medlem m : alleMedlemmer){
         String af = m.getAktivitetsform() == true ? "Aktivt" : "Passivt"; //ternary operation: er aktivitetsform = true? hvis ja, return en String med Aktivt, else return en String med passivt
         if(m instanceof KonkurrenceSvoemmer){
            System.out.println(m.getID() + " " + m.getFornavn() + " " + m.getEfternavn() + " " + m.getTitel() + " " + " " + m.getFdato() + " " + m.getAktivitetsform() + " " + ((KonkurrenceSvoemmer) m).getTraener() + " " + m.erMotionist());
         }
         else{
            System.out.println(m.getID() + " " + m.getFornavn() + " " + m.getEfternavn() + " " + m.getTitel() + " " + " " + m.getFdato() + " " + m.getAktivitetsform() + " " + m.erMotionist());
         }
      }
   }
   
   public static void udfyldMedlemmer(ArrayList<Medlem> list, ArrayList<Resultat> alleResultater) throws Exception{
      //Sikrer at medlemslisten er tom før vi ligger noget i den
      list.clear();
      
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
         boolean erMotionist = data.nextBoolean();
         
         if(erMotionist){
            list.add(new Medlem(id, fornavn, efternavn, titel, fdato, aktivitetsform, erMotionist));
         }
         else
         {
            String traener = data.next();
            list.add(new KonkurrenceSvoemmer(id, fornavn, efternavn, titel, fdato, aktivitetsform, erMotionist, traener, alleResultater));
         }
      }
   }
   public static void seTop5(ArrayList<Medlem> alleMedlemmer, String disciplin) throws Exception{
      //ArrayList for bedste resultat for alle medlemmer for valgte disciplin
      ArrayList<Resultat> topListe = new ArrayList<Resultat>();
      
      for(Medlem m : alleMedlemmer){
         if(m instanceof KonkurrenceSvoemmer){
            //Henter resultater fra medlems objektet
            ArrayList<StaevneResultat> srList = ((KonkurrenceSvoemmer) m).getStaevneResultater();
            ArrayList<TraeningsResultat> trList = ((KonkurrenceSvoemmer) m).getTraeningsResultater();
            
            //Køres kun igennem hvis der eksisterer traeningsresultater 
            if(srList.size() > 0){
               //Sorterer listen til laveste tid på index 0, anden lavest på index 1, osv
               Collections.sort(srList, Resultat.TidComparator);
               
               //Finder bedste staevneresultat for valgte disciplin for et medlem
               int i = 0;
               for(StaevneResultat sr : srList){
                  if(sr.getDisciplin().equals(disciplin) && i < 1){
                     topListe.add(sr);
                     i++;
                  }
               }
            }
            //Køres kun igennem hvis der eksisterer traeningsresultater
            if(trList.size() > 0){
               //Hvis vi sorterer listen behøver vi senere kun køre if-sætningerne forneden igennem én gang for at finde bedste tid
               Collections.sort(trList, Resultat.TidComparator);

               //Finder bedste result for hver disciplin for et medlem
               for(TraeningsResultat tr : trList){
                  int i = 0;
                  if(tr.getDisciplin().equals(disciplin) && i == 0){
                     //metode der tjekker om medlemID er unikt
                     int index = Resultat.containsIDreturnIndex(topListe, tr.getMedlemID());
                     if(index >= 0){
                        //tjek for objecternes tid, tidligere resultat hvis det nye er lavere
                        if(tr.getTid() < topListe.get(index).getTid()){
                           topListe.remove(index);
                           topListe.add(tr);
                           i++;
                        }
                     }
                     else{
                        topListe.add(tr);
                     }
                  }         
               }
            }
         }
      }
      //Efter vi har fundet bedste resultat for hvert medlem, sorterer vi listen en sidste gang for at sortere mellem medlemmer
      Collections.sort(topListe, Resultat.TidComparator);      
      //Printer de første fem i listen ud
      System.out.println(disciplin);
      if(topListe.size() > 0){
         System.out.printf("\n%-15s%-10s%-16s%-10s\n", "Navn", "Tid", "Dato", "Staevne");
         for(int i = 0; i < 5; i++){
            if(topListe.size() > i){
               int medlemID = topListe.get(i).getMedlemID();
               for(Medlem m2 : alleMedlemmer){
                  if(m2.getID() == medlemID){
                     System.out.printf("%-15s", m2.getFornavn() + " " + m2.getEfternavn());
                  }
               }
               String s = "Traening";
               if(topListe.get(i) instanceof StaevneResultat){
                  s = ((StaevneResultat) topListe.get(i)).getStaevne();
               }
               System.out.printf("%-10.2f%-16s%-10s\n", topListe.get(i).getTid(), topListe.get(i).getDato(), s);   
            }  
         }
      }
      else{
         System.out.println("\nDer er ingen resultater for denne disciplin.");
      }
   }
   public static void opretNytResultat(ArrayList<Medlem> alleMedlemmer, ArrayList<Resultat> alleResultater) throws Exception{
      //VIGTIGT: Husk at kalde udfyldMedlemmer så de får de nye informationer med
      visKonkurrenceSvoemmere(alleMedlemmer);

      
      Scanner console = new Scanner(System.in);
      
      System.out.println("Vaelg svoemmer ud fra ID");
      int id = alleResultater.get(alleResultater.size()-1).getID() + 1;
      int medlemID = console.nextInt();
      String disciplin = "";

      int valg = 0;
      do{
         System.out.println("Vaelg disciplin\n1: Brystsvoemning\n2: Hundesvoemning\n3: Butterfly\n4: Rygcrawl\n5: Crawl");
         valg = console.nextInt();
         if(valg == 1){
            disciplin = "Brystsvomning";
         }
         else if(valg == 2){
            disciplin = "Hundesvomning";
         }
         else if(valg == 3){
            disciplin = "Butterfly";
         }
         else if(valg == 4){
            disciplin = "Rygcrawl";
         }
         else if(valg == 5){
            disciplin = "Crawl";
         }
         else{
            System.out.println("Indtast gyldigt tal");
         }
      }
      while(valg != 1 && valg != 2 && valg != 3 && valg != 4 && valg != 5);
      System.out.println("Indtast tid");
      double tid = console.nextDouble();
      System.out.println("Indtast dato");
      String dato = console.next();
      
      valg = 0;
      do{
         System.out.println("Vaelg 1: Traening eller 2: Staevne");
         valg = console.nextInt();
         
         if(valg == 1){
            TraeningsResultat tr = new TraeningsResultat(id, medlemID, disciplin, tid, dato);
            alleResultater.add(tr);
         }
         else if(valg == 2){
            System.out.println("Indtast navn på staevne");
            String staevne = console.next();
            StaevneResultat sr = new StaevneResultat(id, medlemID, disciplin, tid, dato, staevne);
            alleResultater.add(sr);
         }
         else{
            System.out.println("Indtast gyldigt tal");
         }
      }
      while(valg != 1 && valg != 2);
      
      Resultat.gemResultater(alleResultater);
      udfyldMedlemmer(alleMedlemmer, alleResultater);
      
   }
   
   public static void visKonkurrenceSvoemmereOgResultater(ArrayList<Medlem> alleMedlemmer){
      //Print alle konkurrencesvoemmere
      System.out.println("Konkurrencesvoemmere og deres resultater");
      for(Medlem m : alleMedlemmer){
         if(m instanceof KonkurrenceSvoemmer){
            System.out.println("________________________________________________________\n\n" + m.getID() + ": " + m.getFornavn() + " " + m.getEfternavn() + "\n");
            
            
            ArrayList<TraeningsResultat> trList = ((KonkurrenceSvoemmer)m).getTraeningsResultater();
            if(trList.size() > 0){
               System.out.println("Traening:");
               
               System.out.printf("%-10s%-16s%-16s\n", "Tid", "Dato", "Disciplin"); 
               for(TraeningsResultat tr : trList){
                  System.out.printf("%-10.2f%-16s%-16s\n", tr.getTid(), tr.getDato(), tr.getDisciplin()); 
               }
               System.out.println();
            }
            ArrayList<StaevneResultat> srList = ((KonkurrenceSvoemmer)m).getStaevneResultater();
            if(srList.size() > 0){
               System.out.println("Staevner:");
               System.out.printf("%-10s%-16s%-16s%-16s\n", "Tid", "Dato", "Disciplin", "Staevne"); 
      
               for(StaevneResultat sr : srList){
                  System.out.printf("%-10.2f%-16s%-16s%-16s\n", sr.getTid(), sr.getDato(), sr.getDisciplin(), sr.getStaevne()); 
               }
            }
            if(srList.size() == 0 && trList.size() == 0){
               System.out.println("Ingen resultater for dette medlem.");
            }         
         }
      }
   }
   
   public static void visKonkurrenceSvoemmere(ArrayList<Medlem> alleMedlemmer){
      System.out.printf("%-5s%-16s\n", "ID", "Navn");
      for(Medlem m : alleMedlemmer){
         if(m instanceof KonkurrenceSvoemmer){
            System.out.printf("%-5d%-16s\n", m.getID(), m.getFornavn() + " " + m.getEfternavn());
         }
      }
   }
}