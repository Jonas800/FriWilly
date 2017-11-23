import java.util.*;

public class Delfinen{
   public static void main(String [] args)throws Exception{
      visMenu();
   }
   
   public static void visMenu()throws Exception{
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
   public static void formand()throws Exception{
      Scanner console = new Scanner(System.in);
      System.out.println("1: Register medlemmer\n2: Se medlemmer\n3: Opdater medlemmer\n4: Tilbage til hovedmenu");
         int formandValg = input.nextInt();
         switch(formandValg){
            case 1: 
               registerMembers();
               break;
            case 2:
               viewMembers();
               break;
            case 3:
               updateMembers();
               break;
            case 4:
               visMenu();
               break;
            default:
               System.out.println("Ugyldigt input. Proev igen");
               formand();
               break;
                  
         }
   }
   public static void traener()throws Exception{
      Scanner console = new Scanner(System.in);
      System.out.println("1: Se konkurrencesvoemmere\n2: Se top 5\n3: Opdater resultater\n4: Tilbage til hovedmenu");
      int traenerValg = console.nextInt();
      switch(traenerValg){
         case 1:
            seKonkurrenceSvoemmere();
            break;
         case 2:
            seTopFem();
            break;
         case 3:
            opdaterResultater();
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
   

   
  public static void menu(){
      Scanner console = new Scanner(System.in);
      System.out.println("1: Se medlemmer i restance\n2: Se medlemmers indbetaling i kontigent\n3: Håndter kontigent\n4: Tilbage til hovedmenu");
      int kassererValg = console.nextInt();
      switch(kassererValg){
         case 1:
            seRestance();
            break;
         case 2:
            seIndbetaling();
            break;
         case 3:
            haandterKontigent();
            break;
         case 4:
            visMenu();
            break;
         default:
            System.out.println("Ugyldigt input. Proev igen");
            break;
            
      }
   }
        
   }
}
       
