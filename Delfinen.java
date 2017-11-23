import java.util.*;

public class Delfinen{
   public static void main(String [] args)throws Exception{
      loadMenu();
   }
   
   public static void loadMenu()throws Exception{
      Scanner console = new Scanner(System.in);
      System.out.println("1: Formand\n2: Traener\n3: Kasserer\n4: Stop");
      int menuChoice = console.nextInt();
      while(menuChoice != 4){
         switch(menuChoice){
            case 1:
               formand();
               break;
            case 2:
               trainer();
               break;
            case 3:
               cashier();
               break;
            default:
               loadMenu();
               break;
         }
      }
   }
   
   
   
   public static void formand()throws Exception{
      Scanner input = new Scanner(System.in);
      System.out.println("1: Register medlemmer\n2: Se medlemmer\n3: Opdater medlemmer\n4: Tilbage");
         int formandChoice = input.nextInt();
         if (formandChoice == 4){
            loadMenu(); 
         }else {
            switch(formandChoice){
               case 1: 
                  registerMembers();
                  break;
               case 2:
                  viewMembers();
                  break;
               case 3:
                  updateMembers();
                  break;
               default:
                  System.out.println("Forkert input. Proev igen");
                  formand();
                  break;
                  
            }
         }
   }
   
   public static void 
       
}