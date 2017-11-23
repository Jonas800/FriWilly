import java.io.*;

public class Traener extends Ansatte{

   public static void menu()throws Exception{
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

}