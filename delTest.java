import java.util.*;
import java.time.LocalDate;
import java.io.*;
public class delTest {
   public static void main(String[] args) throws Exception{
      
      ArrayList<Resultat> alleResultater = new ArrayList<Resultat>();
      udfyldResultater(alleResultater);
      ArrayList<Medlem> alleMedlemmer = new ArrayList<Medlem>();
      udfyldMedlemmer(alleMedlemmer, alleResultater);

      
      // for(Resultat r : alleResultater){
//       if(r instanceof StaevneResultat){
//          System.out.println(((StaevneResultat) r).getStaevne());
//       }}
      while(true){
      visMedlemmer(alleMedlemmer);
      
      visKonkurrenceSvoemmere(alleMedlemmer);
      
      registrerMedlemmer(alleMedlemmer, alleResultater);
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
   public static void visKonkurrenceSvoemmere(ArrayList<Medlem> alleMedlemmer) throws Exception{
      System.out.println("KS'ers + resultater");
      ArrayList<Resultat> resultatList = new ArrayList<Resultat>();
      //ArrayLists for bedste resultat for alle medlemmer for hver disciplin
      ArrayList<Resultat> bedsteBrystsvoemning = new ArrayList<Resultat>();
      ArrayList<Resultat> bedsteHundesvoemning = new ArrayList<Resultat>();
      ArrayList<Resultat> bedsteRygcrawl = new ArrayList<Resultat>();
      ArrayList<Resultat> bedsteCrawl = new ArrayList<Resultat>();
      ArrayList<Resultat> bedsteButterfly = new ArrayList<Resultat>();
      
      
      
      for(Medlem m : alleMedlemmer){
         if(m instanceof KonkurrenceSvoemmer){
            ArrayList<StaevneResultat> srList = ((KonkurrenceSvoemmer) m).getStaevneResultater();
            if(srList.size() > 0){
               System.out.println(m);
               Collections.sort(srList, Resultat.TidComparator);
               
               //Get bedste staevneresultat for hver disciplin for et medlem
               int bs = 0, hs = 0, rc = 0, c = 0, bf = 0;
               for(StaevneResultat sr : srList){
                  //System.out.println(sr);
                  
                  if(sr.getDisciplin().equals("Brystsvomning") && bs < 1){
                     bedsteBrystsvoemning.add(sr);
                     bs++;
                  }
                  if(sr.getDisciplin().equals("Hundesvomning") && hs < 1){
                     bedsteHundesvoemning.add(sr);
                     hs++;
                  }
                  if(sr.getDisciplin().equals("Rygcrawl") && rc < 1){
                     bedsteRygcrawl.add(sr);
                     rc++;
                  }
                  if(sr.getDisciplin().equals("Crawl") && c < 1){
                     bedsteCrawl.add(sr);
                     c++;
                  }
                  if(sr.getDisciplin().equals("Buttefly") && bf < 1){
                     bedsteButterfly.add(sr);
                     bf++;
                  }
               }
               
               //resultatList.add(srList.get(0));
            }
            ArrayList<TraeningsResultat> trList = ((KonkurrenceSvoemmer) m).getTraeningsResultater();
            if(trList.size() > 0){
               
               //Hvis vi sorterer listen behøver vi kun køre if-sætningerne forneden igennem én gang for at finde bedste tid
               Collections.sort(trList, Resultat.TidComparator);
               int bs = 0, hs = 0, rc = 0, c = 0, bf = 0;

               //Get bedste staevneresultat for hver disciplin for et medlem
               for(TraeningsResultat tr : trList){
                  //System.out.println(sr);
                  
                  if(tr.getDisciplin().equals("Brystsvomning") && bs < 1){
                     int index = Resultat.containsIDreturnIndex(bedsteBrystsvoemning, m.getID());
                     if(index >= 0){
                        //tjek for objecternes tid, tidligere resultat hvis det nye er lavere
                        if(tr.getTid() < bedsteBrystsvoemning.get(index).getTid()){
                           bedsteBrystsvoemning.remove(index);
                           bedsteBrystsvoemning.add(tr);
                           bs++;
                        }
                     }
                  }
                  if(tr.getDisciplin().equals("Hundesvomning") && hs < 1){
                     int index = Resultat.containsIDreturnIndex(bedsteHundesvoemning, m.getID());
                     if(index >= 0){
                        //tjek for objecternes tid, tidligere resultat hvis det nye er lavere
                        if(tr.getTid() < bedsteHundesvoemning.get(index).getTid()){
                           bedsteHundesvoemning.remove(index);
                           bedsteHundesvoemning.add(tr);
                           hs++;
                        }
                     }
                  }
                  if(tr.getDisciplin().equals("Rygcrawl") && rc < 1){
                     int index = Resultat.containsIDreturnIndex(bedsteRygcrawl, m.getID());
                     if(index >= 0){
                        //tjek for objecternes tid, tidligere resultat hvis det nye er lavere
                        if(tr.getTid() < bedsteRygcrawl.get(index).getTid()){
                           bedsteRygcrawl.remove(index);
                           bedsteRygcrawl.add(tr);
                           bs++;
                        }
                     }

                  }
                  if(tr.getDisciplin().equals("Crawl") && c < 1){
                     int index = Resultat.containsIDreturnIndex(bedsteCrawl, m.getID());
                     if(index >= 0){
                        //tjek for objecternes tid, tidligere resultat hvis det nye er lavere
                        if(tr.getTid() < bedsteCrawl.get(index).getTid()){
                           bedsteCrawl.remove(index);
                           bedsteCrawl.add(tr);
                           c++;
                        }
                     }
                  }
                  if(tr.getDisciplin().equals("Buttefly") && bf < 1){
                     int index = Resultat.containsIDreturnIndex(bedsteButterfly, m.getID());
                     if(index >= 0){
                        //tjek for objecternes tid, tidligere resultat hvis det nye er lavere
                        if(tr.getTid() < bedsteButterfly.get(index).getTid()){
                           bedsteButterfly.remove(index);
                           bedsteButterfly.add(tr);
                           bf++;
                        }
                     }                  }
               }

               //Get index hvor id går igen
               int index = Resultat.containsIDreturnIndex(resultatList, m.getID());
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
//       Collections.sort(resultatList, Resultat.TidComparator);
//       Collections.sort(resultatList, Resultat.TidComparator);

      System.out.println("top5");
      Collections.sort(bedsteBrystsvoemning, Resultat.TidComparator);
      Collections.sort(bedsteHundesvoemning, Resultat.TidComparator);
      Collections.sort(bedsteRygcrawl, Resultat.TidComparator);
      Collections.sort(bedsteCrawl, Resultat.TidComparator);
      Collections.sort(bedsteButterfly, Resultat.TidComparator);

      System.out.println("BS");
      for(Resultat r : bedsteBrystsvoemning){
         System.out.println(r);
      }
      System.out.println("HS");
      for(Resultat r : bedsteHundesvoemning){
         System.out.println(r);
      }
      System.out.println("RC");
      for(Resultat r : bedsteRygcrawl){
         System.out.println(r);
      }
      System.out.println("C");
      for(Resultat r : bedsteCrawl){
         System.out.println(r);
      }
      System.out.println("BF");
      for(Resultat r : bedsteButterfly){
         System.out.println(r);
      }
      
      // Resultat[] top5brystSvomning = new Resultat[5];
//       Resultat[] top5hundeSvomning = new Resultat[5];
//       Resultat[] top5rygcrawl = new Resultat[5];
//       Resultat[] top5crawl = new Resultat[5];
//       Resultat[] top5butterfly = new Resultat[5];
//       
//       int bs = 0;
//       int rc = 0;
//       int bf = 0;
//       int c = 0;
//       int hs = 0;
//       for(Resultat r : resultatList){
//          if(r.getDisciplin().equals("Brystsvomning") && bs < 5){
//             top5brystSvomning[bs] = r;
//             bs++;
//          }
//          if(r.getDisciplin().equals("Rygcrawl") && rc < 5){
//             top5rygcrawl[rc] = r;
//             rc++;
//          }
//          if(r.getDisciplin().equals("Butterfly") && bf < 5){
//             top5butterfly[bf] = r;
//             bf++;
//          }
//          if(r.getDisciplin().equals("Crawl") && c < 5){
//             top5crawl[c] = r;
//             c++;
//          }
//          if(r.getDisciplin().equals("Hundesvomning") && hs < 5){
//             top5hundeSvomning[hs] = r;
//             hs++;
//          }
//       }
//       Arrays.sort(top5hundeSvomning, Resultat.TidComparator);
//       Arrays.sort(top5rygcrawl, Resultat.TidComparator);
//       Arrays.sort(top5butterfly, Resultat.TidComparator);
//       Arrays.sort(top5crawl, Resultat.TidComparator);
//       Arrays.sort(top5brystSvomning, Resultat.TidComparator);
// 
//       for(Resultat r : top5hundeSvomning){
//          if(r != null){
//             System.out.println(r);
//          }
//       }
//       for(Resultat r : top5brystSvomning){
//          if(r != null){
//             System.out.println(r);
//          }
//       }
//       for(Resultat r : top5rygcrawl){
//          if(r != null){
//             System.out.println(r);
//          }
//       }
   }
   public static void visStaevneResultaterForEnSvoemmer(){
      
   }
   
}