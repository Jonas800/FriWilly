import java.util.*;

public class Delfinen{
   public static void main(String [] args)throws Exception{
      ArrayList<Medlem> mList = new ArrayList<Medlem>();
      udfyldMedlemmer(mList);
      visMenu(mList);
   }
   
   public static void visMenu(mList)throws Exception{
      Scanner console = new Scanner(System.in);
      System.out.println("1: Formand\n2: Traener\n3: Kasserer\n4: Stop");
      int menuValg = console.nextInt();
      while(menuValg != 4){
         switch(menuValg){
            case 1:
               formand();
               break;
            case 2:
               traener();
               break;
            case 3:
               kasserer();
               break;
            default:
               visMenu();
               break;
         }
      }
   }
   public static void formand(ArrayList<Medlem> mList)throws Exception{
      Scanner console = new Scanner(System.in);
      System.out.println("1: Register medlemmer\n2: Se medlemmer\n3: Opdater medlemmer\n4: Tilbage til hovedmenu");
         int formandValg = input.nextInt();
         switch(formandValg){
            case 1: 
               registrerMedlemmer(ArrayList<Medlem> mList); // done
               break;
            case 2:
               visMedlemmer(ArrayList<Medlem> mList); // done
               break;
            case 3:
               opdaterMedlemmer(ArrayList<Medlem> mList);
               break;
            case 4:
               visMenu(); // done
               break;
            default:
               System.out.println("Ugyldigt input. Proev igen");
               formand();
               break;
                  
         }
   }
   public static void traener(ArrayList<Medlem> mList)throws Exception{
      Scanner console = new Scanner(System.in);
      System.out.println("1: Se konkurrencesvoemmere\n2: Se top 5\n3: Opdater resultater\n4: Tilbage til hovedmenu");
      int traenerValg = console.nextInt();
      switch(traenerValg){
         case 1:
            visKonkurrenceSvoemmere(ArrayList<Medlem> mList); //done
            break;
         case 2:
            seTopFem(ArrayList<Medlem> mList); //ikke endnu
            break;
         case 3:
            opdaterResultater(ArrayList<Medlem> mList); // ikke endnu
            break;
         case 4:
            visMenu();
            break;
         default:
            System.out.println("Ugyldigt input. Proev igen");
            traener();
            break;
         
      }
   }      
   public static void Kasserer(ArrayList<Medlem> mList){
      Scanner console = new Scanner(System.in);
      System.out.println("1: Se medlemmer i restance\n2: Se medlemmers indbetaling i kontigent\n3: Håndter kontigent\n4: Tilbage til hovedmenu");
      int kassererValg = console.nextInt();
      switch(kassererValg){
         case 1:
            seRestance(ArrayList<Medlem> mList);
            break;
         case 2:
            seIndbetaling(ArrayList<Medlem> mList);
            break;
         case 3:
            haandterKontigent(ArrayList<Medlem> mList);
            break;
         case 4:
            visMenu();
            break;
         default:
            System.out.println("Ugyldigt input. Proev igen");
            break;
            
      }
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
   public static void visMedlemmer(ArrayList<Medlem> mList){
      for(Medlem m : mList){
         String af = m.getAktivitetsform() == true ? "Aktivt" : "Passivt"; //forkortet if/else sætning
         System.out.println(m.getID() + " " + m.getFornavn() + " " + m.getEfternavn() + " " + m.getTitel() + " " + m.getFdato() + " " + af + " " + m.getTraener());
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
               Collections.sort(srList, StaevneResultat.TidComparator);
               
               for(StaevneResultat sr : srList){
                  System.out.println(sr);
                  
               }
               //get bedste tid per svoemmer
               
               resultatList.add(srList.get(0));
               
               
            }
         }
      }
   }
}

       
