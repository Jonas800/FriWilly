# FriWilly
First semester project

ny opdaterMedlem;

public static void opdaterMedlemmer(ArrayList<Medlem> mList) throws Exception{
      boolean quit = false;
      do{
         Scanner console = new Scanner(System.in);
         
         System.out.println("Vælg et id for at ændre et medlem: ");
         int id = console.nextInt();
         
         System.out.println("Hvad kunne du godt tænke dig at ændre i? ");
         System.out.println("1. Fornavn");
         System.out.println("2. Efternavn");
         System.out.println("3. Titel");
         System.out.println("4. Fødselsdato");
         System.out.println("5. Aktiv eller passiv ");
         System.out.println("6. Aktivitetsform");
         System.out.println("0. log ud");
         System.out.print("Vælg venligst et menupunkt: ");
         int valg = console.nextInt();
         
         switch(valg){
            case 1:  
               for (Medlem m : mList) {
                  if (m.getID() == valg) {
                     System.out.println("Indtast fornavn");
                     String fornavn = console.next(); 
                     gemMedlem(mList);
                  }
               }
               break;
            case 2:
               for (Medlem m : mList) {
                  if (m.getID() == valg) {
                     System.out.println("Indtast efternavn");
                     String efternavn = console.next();
                     gemMedlem(mList);
                  }
               }
               break;
            case 3:
               for (Medlem m : mList) {
                  if (m.getID() == valg) {
                     System.out.println("Indtast title");
                     String titel = console.next();
                     gemMedlem(mList);
                  }
               }
               break;
            case 4:
               for (Medlem m : mList) {
                  if (m.getID() == valg) {
                     System.out.println("Indtast fødselsdato (yyyy-MM-dd)");
                     String fdato = console.next();
                     gemMedlem(mList);
                  }
               }
               break;
            case 5:
               for (Medlem m : mList) {
                  if (m.getID() == valg) {
                     System.out.println("Vælg om medlemmet er 1: aktivt eller 2: passivt");
                     int aktivitetsformValg = console.nextInt();
                     boolean aktivitetsform = aktivitetsformValg == 1 ? true : false; //ternary operation: er aktivitetsform = 1? hvis ja, return true, else return false

                     gemMedlem(mList);
                  }
               }
               break;
            case 6:
               for (Medlem m : mList) {
                  if (m.getID() == valg) {
                     System.out.println("Vælg om medlemmet er 1: motionist eller 2: konkurrencesv¯mmer");
                     boolean erMotionist = console.nextInt() == 1 ? true : false;
                     gemMedlem(mList);
                  }
               }
               break;
            case 0:
               quit = true;
               break;
            default:
               System.out.println("Ugyldigt valg");
         }
      }while (!quit);
      System.out.println("Tak og vær venlig at komme igen!");              
   }
