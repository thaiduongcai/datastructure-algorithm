//*********************************************************************************************************************************
//Name:   Thai Duong Cai   
// Period:       2                                          
// Date: 5/7
// What I learned:
   // I was confused why I used System.out.print but nothing is printed on the screen. The reason is that PrintStream and system.setout
   // set the output to be directly printed in the file instead of the screen.
   
// How I feel about this lab:
// What I wonder:  
//***********************************************************************************************************************************

import java.io.*;
import java.util.*;
public class Pd2DuongCaiDictionary_shell
{
   private static PrintWriter pw;
   public static void main(String[] args) throws Exception
   {
   
      /***************************************************
                        PART I
       **************************************************/
       
       PrintStream console = System.out;
      try {System.setOut(new PrintStream(new FileOutputStream("Pd2DuongCaidictionaryOutput.txt")));}
      catch(Exception e) {} 
    
      Map<String, Set<String>> eng2spn = new TreeMap<String, Set<String>>();
      Scanner infile = new Scanner(new File("spanglish.txt"));
      while(infile.hasNext())
      {
         add(eng2spn, infile.next(), infile.next());
      }
      infile.close();
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
      
      
      Map<String, Set<String>> spn2eng = reverse(eng2spn);
      System.out.println("SPANISH TO ENGLISH");
      display(spn2eng);
 /***************************************************
          PART II 
 **************************************************/

// The two maps are still in the memory. Part II can interact with the user and add
// new word translations to the output file.
         // Write your Part II code here
         // Menu options: translate from (1) English to Spanish 
         //                              (2) Spanish to English 
         //                              (3) Add a new tranlation: (a) from English->Spanish (b) from Spanish->English
         //                              (4) Exit
         
      Scanner sc = new Scanner(System.in);
      int choice = 0;
 
      while (choice != 3)
      {
         console.println("\nWhat would you like to do today? Select a number: ");
         console.println("  1) Search dictionary");
         console.println("  2) Add translation");
         console.println("  3) Exit");
         
         // Guard against non-integer input
         if (!sc.hasNextInt()) { sc.next(); continue; }
         choice = sc.nextInt();
 
         if (choice == 1)
         {
            console.println("\nChoose 1 to translate from English to Spanish or");
            console.println("2 to translate from Spanish to English.");
            
            if (!sc.hasNextInt()) { sc.next(); continue; }
            int direction = sc.nextInt();
 
            if (direction == 1)
            {
               console.println("You have selected the English to Spanish dictionary.");
               console.println("What word would you like to search for (type in all lowercase)?");
               String word = sc.next();
               if (eng2spn.containsKey(word))
                  console.println(eng2spn.get(word));
               else
                  console.println("Sorry, this word is currently not in the dictionary.");
            }
            else if (direction == 2)
            {
               console.println("You have selected the Spanish to English dictionary.");
               console.println("What word would you like to search for (type in all lowercase)?");
               String word = sc.next();
               if (spn2eng.containsKey(word))
                  console.println(spn2eng.get(word));
               else
                  console.println("Sorry, this word is currently not in the dictionary.");
            }
            else
            {
               console.println("Invalid option. Please enter 1 or 2.");
            }
         }
         else if (choice == 2)
         {
            console.println("\nWhat English word would you like to add?");
            String engWord = sc.next();
            console.println("What is the Spanish translation?");
            String spnWord = sc.next();
            
            // Add to both maps so they stay in sync
            add(eng2spn, engWord, spnWord);
            add(spn2eng, spnWord, engWord);
            console.println("Translation added successfully!");
         }
         else if (choice != 3)
         {
            console.println("Invalid option. Please enter 1, 2, or 3.");
         }
      }
 
      // Write the current (updated) state of both maps to the Part II output file
      pw = new PrintWriter(new FileWriter("Pd2DuongCaiUpdatedDictionary.txt"));
      pw.println("ENGLISH TO SPANISH");
      for (String key : eng2spn.keySet())
         pw.println(key + " : " + eng2spn.get(key));
      pw.println("SPANISH TO ENGLISH");
      for (String key : spn2eng.keySet())
         pw.println(key + " : " + spn2eng.get(key));
      pw.close();
   }
   
   // Note: must explain how your method works
   // Postcondition: display the contents of  a dictionary on the screen
   public static void display(Map<String, Set<String>> m)
   {
   //This method traverses the map and print out the proper values.
      Iterator<String> it = m.keySet().iterator();
      
      while (it.hasNext()) {
         String key = it.next();
         System.out.println(key + " : " + m.get(key));
      }
   }
   // Note: must explain how your method works
   // postcondition: insert a new pair to the English to Spanish Dictionary
   public static void add(Map<String, Set<String>> engToSpnDictionary, String word, String translation)
   {
   // Check if the word is assigned to its translations in spanish or not, if not add it in and assign it to the proper translation.
      if (engToSpnDictionary.containsKey(word)) engToSpnDictionary.get(word).add(translation);
      else {
         Set<String> trans = new TreeSet<String>();
         trans.add(translation);
         engToSpnDictionary.put(word, trans);
      }               
   }
   // Note: must explain how your method works
   // postcondition: returns a Spanish to English dictionary
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> engToSpnDictionary)
   {
      Map<String, Set<String>> spn2eng = new TreeMap<String, Set<String>>();
      for (String engWord : engToSpnDictionary.keySet()) {
         for (String spnWord : engToSpnDictionary.get(engWord)) {
            add(spn2eng, spnWord, engWord);
         }
      }
      return spn2eng;
   }
}
      /********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/