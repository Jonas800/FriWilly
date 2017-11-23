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
               Formand formand = new Formand();
               formand.menu();
               break;
            case 2:
               Traener traener = new Traener();
               traener.menu();
               break;
            case 3:
               Kasserer kasserer = new Kasserer();
               kasserer.menu();
               break;
            default:
               visMenu();
               break;
         }
      }
   }
            
}
       
