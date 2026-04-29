//Name:    Thai Duong Cai
//Date:     04/29/26
// What I learned: Learned how to deal with map and  
// How I feel about this lab:
// What I wonder: 
// If Java didn't implement Maps
// I would use two lists to store the keys and the values respectively into the same index
// Credits: 


import java.util.*;
public class Pd2DuongCaiActingSchool_shell
{
   public static void main(String[] args)
   {

        Map<String, String> sGrades = new HashMap<String, String>();     //HashMap
        
        sGrades.put("Jack Nicholson", "A-");
        sGrades.put("Humphrey Bogart", "A+");
        sGrades.put("Audrey Hepburn", "A");
        sGrades.put("Meryl Streep", "A-");
        sGrades.put("Jimmy Stewart", "A");
   
       // What you need to do:
   	// 1. display initial data.  Use an iterator instead of using the built-in toString method of HashMap
        System.out.println("===Initial Map===");
        display(sGrades);
   	// 2. reverse the map--use TreeMap
        System.out.println();
        System.out.println("===Reversed Map===");
   	    
   	    Map<String, ArrayList<String>> reversedsGrades = reverseMap(sGrades);
   	    displayReversed(reversedsGrades);
   	    
   	
       // 3. display the reversed map
   	
   } // main
   
   public static void display(Map<String, String> map) {
       Iterator<String> it = map.keySet().iterator();
       
       while (it.hasNext()) {
           String key = it.next();
           System.out.println(key + " (" + map.get(key) + ")" );
       }
   }
   
   public static void displayReversed(Map<String, ArrayList<String>> map) {
       Iterator<String> it = map.keySet().iterator();
       
       while (it.hasNext()) {
           String key = it.next();
           System.out.println(key + ": " + map.get(key));
       }
   }
   
   public static Map<String, ArrayList<String>> reverseMap (Map<String, String> map) {
       Map<String, ArrayList<String>> reversedMap = new HashMap<String, ArrayList<String>>();
       
       Iterator<String> it = map.keySet().iterator();
       
       while (it.hasNext()) {
           String key = it.next(); //Name
           String valueToKey = map.get(key); //Grade
           
           if (!reversedMap.containsKey(valueToKey)) {
               ArrayList<String> names = new ArrayList<>();
               names.add(key);
               reversedMap.put(valueToKey, names);
           } else {
               reversedMap.get(valueToKey).add(key);
           }
       }
       return reversedMap;
   }
} // ActingSchool_shell