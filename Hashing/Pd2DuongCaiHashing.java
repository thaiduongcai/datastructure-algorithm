/*****************************************************************************************************************
NAME:   Thai Duong Cai   
PERIOD:   2
DUE DATE:    04/22/2026

PURPOSE:    Learn about Hashing

WHAT I LEARNED:    Hashing allows O(1) average-case insert and retrieval by mapping
                   objects to array indices via a hash function. Collisions are handled
                   by linear probing (check next slot), relative prime probing (step by
                   a constant relatively prime to array length), or chaining (linked lists
                   at each bucket). Each scheme trades off simplicity, clustering, and
                   memory usage differently. 
         
CREDITS (BE SPECIFIC: FRIENDS, PEERS, ONLINE WEBSITE): 

****************************************************************************************************************/
/***********************************************************************************
   Assignment:  This hashing program results in collisions.
                You are to implement three different collision schemes: 
                linear probing, relative prime probing (use the first relatively prime 
                number of the length of the hash table as the step increase), and 
                chaining.  Then implement a search algorithm that is appropriate
                for each collision scheme.
 ***********************************************************************************/
import java.util.*;
import javax.swing.*;

public class Pd2DuongCaiHashing
{
   public static void main(String[] args)
   {
      int arrayLength = Integer.parseInt(JOptionPane.showInputDialog(
                         "Hashing!\n"+
                         "Enter the size of the array:  ")); // enter 20
       
      int numItems = Integer.parseInt(JOptionPane.showInputDialog(
                         "Add n items:  "));                 // enter 15
     
      int scheme = Integer.parseInt(JOptionPane.showInputDialog(
           "The Load Factor is " + (double)numItems/arrayLength +
           "\nWhich collision scheme?\n"+
           "1. Linear Probing\n" +
           "2. Relatively Prime Probing\n"+
           "3. Chaining"));
      Hashtable table = null;
      switch( scheme )
      {
         case 1:   
            table = new HashtableLinearProbe(arrayLength);
            break;
         case 2: // rehash using the first relatively prime of arrayLength
            table = new HashtableRelativePrime(arrayLength); 
            break;
         case 3:  
            table = new HashtableChaining(arrayLength);
            break;
         default:  System.exit(0);    
      }
      for(int i = 0; i < numItems; i++)
         table.add("Item" + i);
      String action = JOptionPane.showInputDialog(
                       "Search for:  Item0" + " to "+ "Item"+(numItems-1));
      int itemNumber = 0;
      if (action != null)
      {
         itemNumber = Integer.parseInt(action);
         while( itemNumber != -1 )
         {
            String key = "Item" + itemNumber;
            int index = table.indexOf(key); 
            if( index >= 0)    //found it
               System.out.println(key + " found  at index " + index);
            else
               System.out.println(key + " not found!");
            action = JOptionPane.showInputDialog(
                       "Search for:  Item0" + " to "+ "Item"+(numItems-1));
            if (action != null)
               itemNumber = Integer.parseInt(action); 
            else
               itemNumber = -1;                         
         } 
      }
      System.out.println ("Goodbye!");
      System.exit(0);
   } // main
} // Hashing

interface Hashtable
{
   void add(Object obj);
   int indexOf(Object obj);
}


class HashtableLinearProbe implements Hashtable
{
   private Object[] array;
   public HashtableLinearProbe(int size)
   {
      array = new Object[size];                
   }
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if (array[index] == null)  //empty
      {
         array[index] = obj; //insert it
         System.out.println(obj + "\t" + code + "\t" + index);
      }
      else    //collision
      {
         System.out.println(obj + "\t" + code + "\tCollision at "+ index);
         index = linearProbe(index);
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
   }  
   
   
   public int linearProbe(int index)
   {
      for (int i = index + 1; i != index; i++)
      {
         if (i == array.length) i = 0;   // wrap around
         if (array[i] == null) return i; // found an empty slot
      }
      return index;
   }
   
   
   public int indexOf(Object obj)     
   {
      int index = Math.abs(obj.hashCode() % array.length);
      while(array[index] != null)
      {
         if(array[index].equals(obj))  //found it
         {
            return index;
         }
         else    //search for it in a linear probe manner
         {
            index = (index + 1) % array.length;  // advance and wrap
            System.out.println("Looking at index " + index);
         }
      } // while
      return -1; //not found
   } // indexOf
} // HashtableLinearProbe


class  HashtableRelativePrime implements Hashtable
{
   private Object[] array;
   private int constant = 2;
   
   public  HashtableRelativePrime(int size)
   {
        array = new Object[size]; //constructor
        //find a constant that is relatively prime to the size of the array
        constant = 2;
        while (gcd(constant, size) != 1)
        {
            constant++;
        }
        System.out.println("Relatively prime constant: " + constant);
   }
   
   // Euclidean algorithm to find greatest common divisor
   private int gcd(int a, int b)
   {
      if (b == 0) return a;
      return gcd(b, a % b);
   }
   
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if(array[index] == null)  //empty
      {
         array[index] = obj; //insert it
         System.out.println(obj + "\t" + code + "\t" + index);
      }
      else //collision
      {
         System.out.println(obj + "\t" + code + "\tCollision at "+ index);
         index = rehash(index);
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
   }  
   
   public int rehash(int index)
   {
      index = (index + constant) % array.length;
      while (array[index] != null)
      {
         index = (index + constant) % array.length;
      }
      return index;
   }
   
   public  int indexOf(Object obj)
   {
      int index = Math.abs(obj.hashCode() % array.length);
      while(array[index] != null)
      {
         if(array[index].equals(obj))  //found it
         {
            return index;
         }
         else //search for it in a rehashing manner
         {
            index = (index + constant) % array.length;
            System.out.println("Looking at index " + index);
         }
      }
        return -1; //not found
   }
} //  HashtableRelativePrime


class HashtableChaining implements Hashtable
{
   private LinkedList[] array;
   public HashtableChaining(int size)
   {
        array = new LinkedList[size];    //instantiate the array
        for (int i = 0; i < size; i++)
            array[i] = new LinkedList(); //instantiate the LinkedLists
                            
   }
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      array[index].addFirst(obj);
      System.out.println(obj + "\t" + code + " " + " at " + index + ": "+ array[index]);
   }  
   public int indexOf(Object obj)
   {
      int index = Math.abs(obj.hashCode() % array.length);
      if( !array[index].isEmpty() )
      {
         if(array[index].contains(obj))  //found it
         {
            return index;
         }
         else //search for it in a chaining manner
         {
            System.out.println("Looking at index " + index);
            return -1;
         }
      }
        return -1; //not found
   } // indexOf
} // HashtableChaining