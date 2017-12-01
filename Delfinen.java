import java.util.*;
import java.io.*;
public class Delfinen{
   public static void main(String [] args)throws Exception{
      ArrayList<Resultat> alleResultater = new ArrayList<Resultat>();
      ArrayList<Medlem> alleMedlemmer = new ArrayList<Medlem>();
      udfyldMedlemmer(alleMedlemmer, alleResultater);
      visMedlemmerIRestance(alleMedlemmer);
      visMenu(alleMedlemmer);
   }
   
   public static void visMenu(ArrayList<Medlem> alleMedlemmer) throws Exception{
      Scanner console = new Scanner(System.in);
      System.out.println("1: Formand\n2: Traener\n3: Kasserer\n4: Stop");
      int menuValg = console.nextInt();
      while(menuValg != 4){
         switch(menuValg){
            case 1:
               formand(alleMedlemmer);
               break;
            case 2:
               traener(alleMedlemmer);
               break;
            case 3:
               kasserer(alleMedlemmer);
               break;
            default:
               visMenu(alleMedlemmer);
               break;
         }
      }
   }
   public static void formand(ArrayList<Medlem> alleMedlemmer)throws Exception{
      Scanner console = new Scanner(System.in);
      System.out.println("1: Register medlemmer\n2: Se medlemmer\n3: Opdater medlemmer\n4: Tilbage til hovedmenu");
         int formandValg = console.nextInt();
         switch(formandValg){
            case 1: 
               //registrerMedlemmer(alleMedlemmer, alleResultater); // done
               break;
            case 2:
               visMedlemmer(alleMedlemmer); // done
               break;
            case 3:
               //opdaterMedlemmer(alleMedlemmer);
               break;
            case 4:
               visMenu(alleMedlemmer); // done
               break;
            default:
               System.out.println("Ugyldigt input. Proev igen");
               formand(alleMedlemmer);
               break;
                  
         }
   }
   
   public static void traener(ArrayList<Medlem> alleMedlemmer)throws Exception{
      Scanner console = new Scanner(System.in);
      System.out.println("1: Se konkurrencesvoemmere\n2: Se top 5\n3: Opdater resultater\n4: Tilbage til hovedmenu");
      int traenerValg = console.nextInt();
      switch(traenerValg){
         case 1:
            //visKonkurrenceSvoemmere(alleMedlemmer); //done
            break;
         case 2:
            //seTopFem(alleMedlemmer); //ikke endnu
            break;
         case 3:
            //opdaterResultater(ArrayList<Medlem> alleMedlemmer); // ikke endnu
            break;
         case 4:
            visMenu(alleMedlemmer);
            break;
         default:
            System.out.println("Ugyldigt input. Proev igen");
            traener(alleMedlemmer);
            break;
         
      }
   }      
   public static void kasserer(ArrayList<Medlem> alleMedlemmer){
      Scanner console = new Scanner(System.in);
      System.out.println("1: Se medlemmer i restance\n2: Se medlemmers indbetaling i kontigent\n3: Håndter kontigent\n4: Tilbage til hovedmenu");
      int kassererValg = console.nextInt();
      switch(kassererValg){
         case 1:
            //seRestance(ArrayList<Medlem> alleMedlemmer);
            break;
         case 2:
            //seIndbetaling(ArrayList<Medlem> alleMedlemmer);
            break;
         case 3:
            //haandterKontigent(ArrayList<Medlem> alleMedlemmer);
            break;
         case 4:
            //visMenu();
            break;
         default:
            System.out.println("Ugyldigt input. Proev igen");
            break;
            
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
   public static void visMedlemmerIRestance(ArrayList<Medlem> alleMedlemmer){
   //      System.out.printf("%-4s %-20s %-20s %-10s\n", 

      for(Medlem m: alleMedlemmer){
         if(m.getHarBetalt() == false){
            System.out.printf("%-4s %-20s %-20s %-10s\n", m.getID(), m.getAddresse(), m.get.TelefonNummer(), get.navn()); 
         }
      }
  
   
   }
  
}

       
