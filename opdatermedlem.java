import java.util.*;
import java.io.*;

public class opdaterMedlem {
   public static void main(String[] args) throws Exception{;
   }
   
   public static void opdaterMedlem(ArrayList<Medlem> mList) throws Exception{
      Scanner console = new Scanner(System.in);
      
      System.out.println("Vaelg id for at aendre et medlem:");
      int id = console.nextInt();
      System.out.println("Indtast fornavn: ");
      String fornavn = console.next();
      System.out.println("Indtast efternavn: ");
      String efternavn = console.next();
      System.out.println("Indtast foedselsdato (yyyy-MM-dd): ");
      String fdato = console.next();
      System.out.println("Vaelg om medlemmet er 1: aktivt eller 2: passivt");
      int aktivitetsformValg = console.nextInt();
      boolean aktivitetsform = aktivitetsformValg > 1 ? false : true;
      System.out.println("Vaelg om medlemmet er 1: motionist eller 2: konkurrencesvoemmer: ");
      int medlemType = console.nextInt();
      
      if(medlemType == 1){
         Medlem medlem = new Medlem(id, fornavn, efternavn, fdato, aktivitetsform);
         mList.add(medlem);
         
         gemMedlem(mList);
      }
      else{
         System.out.println("Indtast traener navn: ");
         String traenerNavn = console.next();
         KonkurrenceSvoemmer ks = new KonkurrenceSvoemmer(1, fornavn, efternavn, fdato, aktivitetsform, traenerNavn);
         list.add(ks);
         
         saveKonkurrenceSvoemmere(list);
         KonkurrenceSvoemmer ks = new KonkurrenceSvoemmer(1, fornavn, efternavn, titel, fdato, aktivitetsform, traenerNavn);
         ksList.add(ks);
         
         gemKonkurrenceSvoemmere(ksList);
      }
      
      
   }
   
  