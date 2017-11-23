import java.io.*;

public class Formand extends Ansatte{

   public static void menu()throws Exception{
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
 


}