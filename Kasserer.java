import java.io.*;

public class Kasserer extends Ansatte{

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