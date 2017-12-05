import java.util.*;
import java.io.*;
public class Delfinen{
   public static void main(String [] args)throws Exception{
      ArrayList<Resultat> alleResultater = new ArrayList<Resultat>();
      udfyldResultater(alleResultater);
      ArrayList<Medlem> alleMedlemmer = new ArrayList<Medlem>();
      udfyldMedlemmer(alleMedlemmer, alleResultater);
      visMenu(alleMedlemmer, alleResultater);
   }
   
   public static void visMenu(ArrayList<Medlem> alleMedlemmer, ArrayList<Resultat> alleResultater) throws Exception{
      Scanner console = new Scanner(System.in);
      System.out.println("1: Formand\n2: Traener\n3: Kasserer\n0: Stop");
      int menuValg = console.nextInt();
      while(menuValg != 4){
         switch(menuValg){
            case 1:
               formand(alleMedlemmer, alleResultater);
               break;
            case 2:
               traener(alleMedlemmer, alleResultater);
               break;
            case 3:
               kasserer(alleMedlemmer, alleResultater);
               break;
            default:
               visMenu(alleMedlemmer, alleResultater);
               break;
         }
      }
   }
   public static void formand(ArrayList<Medlem> alleMedlemmer, ArrayList<Resultat> alleResultater)throws Exception{
      Scanner console = new Scanner(System.in);
      System.out.println("1: Register medlemmer\n2: Se medlemmer\n3: Opdater medlemmer\n0: Tilbage til hovedmenu");
         int formandValg = console.nextInt();
         switch(formandValg){
            case 1: 
               registrerMedlemmer(alleMedlemmer, alleResultater); // done
               break;
            case 2:
               visMedlemmer(alleMedlemmer); // done
               break;
            case 3:
               //opdaterMedlemmer(alleMedlemmer);
               break;
            case 0:
               visMenu(alleMedlemmer, alleResultater); // done
               break;
            default:
               System.out.println("Ugyldigt input. Proev igen");
               formand(alleMedlemmer, alleResultater);
               break;
                  
         }
   }
   
   public static void traener(ArrayList<Medlem> alleMedlemmer, ArrayList<Resultat> alleResultater)throws Exception{
      Scanner console = new Scanner(System.in);
      System.out.println("1: Se konkurrencesvoemmere\n2: Se top 5\n3: Opdater resultater\n0: Tilbage til hovedmenu");
      int traenerValg = console.nextInt();
      switch(traenerValg){
         case 1:
            visKonkurrenceSvoemmere(alleMedlemmer); //done
            break;
         case 2:
            int disciplinValg = 0;
            do{
            System.out.println("Vaelg disciplin:\n1: Brystsvoemning\n2: Hundesvoemning\n3: Butterfly\n4: Rygcrawl\n5: Crawl");
            disciplinValg = console.nextInt();
               switch(disciplinValg){
                  case 1:
                     seTop5(alleMedlemmer, "Brystsvomning"); //done
                     break;
                  case 2:
                     seTop5(alleMedlemmer, "Hundesvomning"); //done
                     break;            
                  case 3:
                     seTop5(alleMedlemmer, "Butterfly"); //done
                     break;
                  case 4:
                     seTop5(alleMedlemmer, "Rygcrawl"); //done
                     break;
                  case 5:
                     seTop5(alleMedlemmer, "Crawl"); //done
                     break;
                  default:
                     System.out.println("Ugyldigt input. Proev igen.");
                     break;
               }
            }
            while(disciplinValg != 1 && disciplinValg != 2 && disciplinValg != 3 && disciplinValg != 4 && disciplinValg !=5);
            break;
         case 3:
            //opdaterResultater(alleMedlemmer); // ikke endnu
            break;
         case 0:
            visMenu(alleMedlemmer, alleResultater);
            break;
         default:
            System.out.println("Ugyldigt input. Proev igen");
            traener(alleMedlemmer, alleResultater);
            break;
         
      }
   }      
   public static void kasserer(ArrayList<Medlem> alleMedlemmer, ArrayList<Resultat> alleResultater) throws Exception{
      Scanner console = new Scanner(System.in);
      System.out.println("1: Administrer kontingent\n2: Vis medlemmer i restance\n0: Tilbage til hovedmenu");
      int kassererValg = console.nextInt();
      switch(kassererValg){
         case 1:
            haandterKontingent(alleMedlemmer);
            break;
         case 2:
            visMedlemmerIRestance(alleMedlemmer);
            break;
         case 0:
            visMenu(alleMedlemmer, alleResultater);
            break;
         default:
            System.out.println("Ugyldigt input. Proev igen");
            break;
            
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
      //Sorterer listen efter ID, faldende
      Collections.sort(list, Resultat.IDComparator);
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
         Medlem medlem = new Medlem(alleMedlemmer.size() + 1, fornavn, efternavn, titel, fdato, aktivitetsform, erMotionist, false);
         alleMedlemmer.add(medlem);
         
      }
      else{
         System.out.println("Indtast trænernavn");
         String traenerNavn = console.next();
         KonkurrenceSvoemmer m = new KonkurrenceSvoemmer(alleMedlemmer.size() + 1, fornavn, efternavn, titel, fdato, aktivitetsform, erMotionist, false, traenerNavn, alleResultater);
         alleMedlemmer.add(m);
      }
      gemMedlem(alleMedlemmer);

   }

   public static void gemMedlem(ArrayList<Medlem> list) throws Exception{
      String s = "";
      for(Medlem m : list){
         if(m instanceof KonkurrenceSvoemmer){
            s += m.getID() + " " + m.getFornavn() + " " + m.getEfternavn() + " " + m.getTitel() + " " + " " + m.getFdato() + " " + m.getAktivitetsform() + " " + m.erMotionist() + " " + m.getHarBetalt() + " " + ((KonkurrenceSvoemmer) m).getTraener() + "\r\n";
         }
         else{
            s += m.getID() + " " + m.getFornavn() + " " + m.getEfternavn() + " " + m.getTitel() + " " + " " + m.getFdato() + " " + m.getAktivitetsform() + " " + m.erMotionist() + " " + m.getHarBetalt() + "\r\n";
         }
      }
      PrintStream output = new PrintStream(new File("medlemmer.txt"));
      output.print(s);
      output.close();
   }
   
   public static void visMedlemmer(ArrayList<Medlem> alleMedlemmer){
      System.out.println("ALLE MEDLEMMER");
      System.out.printf("%-5s%-20s%-15s%-16s%-15s%-15s%-15s\n", "ID", "Navn", "Titel", "Foedselsdag", "Aktivitetsform", "Medlemstype", "Traener");
      for(Medlem m : alleMedlemmer){
         String af = m.getAktivitetsform() == true ? "Aktivt" : "Passivt"; //ternary operation: er aktivitetsform = true? hvis ja, return en String med Aktivt, else return en String med passivt
         String em = m.erMotionist() == true ? "Motionist" : "Konkurrence";
         if(m instanceof KonkurrenceSvoemmer){
            System.out.printf("%-5d%-20s%-15s%-16s%-15s%-15s%-15s\n", m.getID(), m.getFornavn() + " " + m.getEfternavn(), m.getTitel(), m.getFdato(), af, em, ((KonkurrenceSvoemmer) m).getTraener());
         }
         else{
            System.out.printf("%-5d%-20s%-15s%-16s%-15s%-15s\n",m.getID(), m.getFornavn() + " " + m.getEfternavn(), m.getTitel(), m.getFdato(), af, em);
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
         boolean harBetalt = data.nextBoolean();
         
         if(erMotionist){
            list.add(new Medlem(id, fornavn, efternavn, titel, fdato, aktivitetsform, erMotionist, harBetalt));
         }
         else
         {
            String traener = data.next();
            list.add(new KonkurrenceSvoemmer(id, fornavn, efternavn, titel, fdato, aktivitetsform, erMotionist, harBetalt, traener, alleResultater));
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


   public static void visMedlemmerIRestance(ArrayList<Medlem> alleMedlemmer){
      System.out.printf("%-4s %-20s %-20s %-10s\n", "ID", "Fornavn", "Efternavn", "Kontigent"); 
      for(Medlem m: alleMedlemmer){
         if(m.getHarBetalt() == false){
            System.out.printf("%-4s %-20s %-20s %-10s\n", m.getID(), m.getFornavn(), m.getEfternavn(), m.getKontigent()); 
         }
      }
  
   
   }
   public static void haandterKontingent(ArrayList<Medlem> alleMedlemmer) throws Exception{
      //vismedlemmer
      
      Scanner console = new Scanner(System.in);
      System.out.println("Indtast ID til Medlem der skal aendres");
      int ID = console.nextInt();
      System.out.println("Medlem skal aendres til\n1: Har betalt\n2: Er i restance");
      boolean valg = console.nextInt() == 1 ? true : false;
      for(Medlem m: alleMedlemmer){
         if (m.getID() == ID){
            m.setHarBetalt(valg);
         
         }    
      }
      
      gemMedlem(alleMedlemmer);
   }  
}

       
