import java.util.*;
import java.io.*;

public class DelfinenMain {
   public static void main(String[] args) {
   Scanner input = new Scanner(System.in);

   HovedMenu(input);
   
   }  
   
   public static void FormandMenu(Scanner input){
      boolean quit = false;
      do {
         System.out.println("1. Opret ny bruger");
         System.out.println("2. Ændre i bruger");
         System.out.println("3. Tilbage til hoved menuen");
         System.out.println("0. Quit");
         System.out.print("\nVælg menupunkt: ");
         IntPrint(input);
         int menuItem = input.nextInt();
         switch (menuItem) {
            case 1:
               System.out.println("\nDu har valgt at Oprette ny bruger\n");
               // Opret bruger metode 
               break;
            case 2:
               System.out.println("\nDu har valgt at ændre i bruger\n");
               // ændre bruger metode                
               break;
            case 3:
               System.out.println("\n Du har valgt tilbage til hoved menuen\n");
               HovedMenu(input);
               break;
            case 0:
                quit = true;
                break;
            default:
                System.out.println("Ugyldigt valg.");
            }
        } while (!quit);
        System.out.println("Tak og vær venlig at komme igen!");
    }

   
   public static void HovedMenu(Scanner input){
        boolean quit = false;
        do{
            System.out.println(" \n");
            System.out.println("Velkommen til Delfinen main menu.\n");
            System.out.println("1. Formand menu");
            System.out.println("2. Trænr menu");
            System.out.println("3. Kasserer menu");
            System.out.println("0. Quit");
            System.out.print("\nVælg venligst et menupunkt: ");
            IntPrint(input);
            int menuVare = input.nextInt();
            switch (menuVare) {
                case 1:
                    System.out.println("\nIndtast venligst Formand adgangskode\n");
                    String password = input.next();
                    if(password.equals("1212")){
                        System.out.println("\nAdgang givet.\n\n");
                        FormandMenu(input);
                    }else{
                        System.out.println("\nAdgang nægtet!\n\n");
                    }
                    break;
                case 2:
                    System.out.println("\nIndtast venligst Træner adgangskode\n");
                    password = input.next();
                    if(password.equals("1313")){
                        System.out.println("\nAdgang givet.\n\n");
                        // Træner methode her
                    }else{
                        System.out.println("\nAdgang nægtet!\n\n");
                    }

                    break;
                case 3:
                    System.out.println("\nIndtast venligst Kasserer adgangskode\n");
                    password = input.next();
                    if(password.equals("1414")){
                        System.out.println("\nAdgang givet.\n\n");
                        // kasserer methode her
                    }else{
                        System.out.println("\nAdgang nægtet!\n\n");
                    }
                    break;
                case 0:
                    quit = true;
                    break;
                default:
                    System.out.println("Ugyldigt valg.");
            }
        }while (!quit);
        System.out.println("Tak og vær venlig at komme igen!");
   }
   
   public static String IntPrint(Scanner input){
      String number =null;
      while (!input.hasNextInt()) {
         number = input.next();
         System.out.println("Ugyldig indtastning angiv venligst et helt nummer");
      }
      return number;
   }


     
}
