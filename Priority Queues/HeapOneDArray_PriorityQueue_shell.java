/**************************************************************************************
 Name:   Thai Duong Cai
 Date:   3/12/2026
 What I learned:
    I learned how to cast from Comparable to E generic type
 How I feel about this lab: As long as I understand how the methods work, it's fine.

 I am wondering (the what-if moment):
 - I wondered if I should create a method to check if the array is about to full -> Later I created the overSize method to do that
 Credits: myself 100%
***************************************************************************************/
import java.util.*;
public class Pd2DuongCaiHeapOneDArray_PriorityQueue_shell <E extends Comparable <E>>
{
   private static final int DEFAULT_CAPACITY = 1024;
   private Comparable [] items;      // use a 1-D array instead of ArrayList
   private int numItems;    // number of elements in items

  
   public static void main (String [] args)
   {
      // Create a HeapPriorQueue_shell object to test all the methods in this class
      Pd2DuongCaiHeapOneDArray_PriorityQueue_shell <Integer> pq = new Pd2DuongCaiHeapOneDArray_PriorityQueue_shell <>();
      
        System.out.println("Original");
        System.out.println(pq); // toString method test
        System.out.println("Added 6 into the array");
        pq.add(6);
        System.out.println(pq); // toString method test
        System.out.println("Remove the highest priority");
        pq.remove();
        System.out.println(pq); // toString method test
        System.out.println("Is the array empty?");
        System.out.println(pq.isEmpty()); // isEmpty method test
        System.out.println("What is the highest priority in the array?");
        System.out.println(pq.peek()); // peek method test
   }
   
   public Pd2DuongCaiHeapOneDArray_PriorityQueue_shell()
   {
       // your code goes here
       items = (new Comparable [] {null, 5,12,20,32,52});  // a min-heap
       numItems = 5;
         
   } 
   
   
   public Pd2DuongCaiHeapOneDArray_PriorityQueue_shell (int initialCapacity)
   {
        numItems = 0;
        items = new Comparable[initialCapacity];
   }
   

    
   // precondition: n/a
   // postcondition: return true if the list is empty, vice versa.
   public boolean isEmpty()
   {
      return numItems == 0;
   }
   
   // precondition: N/A
   // postcondition: return the highest priority in the list   
   public E peek()
   {
        if (!isEmpty()) return (E) items[1];
        return null;
   }
   
   // precondition: The array is not empty
   // postcondition: Remove the highest priority item and return that item. Shift every other items into proper positions.
   public E remove()
   {
      E removedItem = (E)items[1];  
      items[1] = items[numItems];
      numItems -= 1;
      reheapDown(numItems);
      
      return removedItem;  
   }

   // precondition: N/A
   // postcondition: add the new item, obj, into the heap at the proper position   
   public boolean add(E obj)
   {
      if (overSize()) doubleCapacity();
      numItems += 1;
      items[numItems] = obj;
      
      reheapUp();
      
      return true;  
     
   } // add
      
   // precondition:
   // postcondition:
   public String toString ()
   {
       String print = "";
        for (int i = 1; i <= numItems; i++) 
            print += items[i] + " ";
        return print;
   }
   
   // precondition:
   // postcondition:
   private void reheapDown(int index)
   {
       int trackIndex = 1;
       while(trackIndex <= numItems) {
           E left = (E)items[trackIndex*2];
           E right = (E)items[trackIndex*2 + 1]; 
           E center = (E)items[trackIndex];
           if (center.compareTo(left) > 0  && center.compareTo(right) > 0) {
               if (left.compareTo(right) < 0) {
                   E temp = (E)items[trackIndex];
                   items[trackIndex] = items[trackIndex*2];
                   items[trackIndex*2] = temp;
                   trackIndex *= 2;
               } else {
                   E temp = (E)items[trackIndex];
                   items[trackIndex] = items[trackIndex*2 + 1];
                   items[trackIndex*2 + 1] = temp;
                   trackIndex = trackIndex*2 + 1;
               }
           }
            else break;
       }
   }
   
   // precondition:
   // postcondition:   
   private void reheapUp()
   {
        int trackIndex = numItems;
        while (trackIndex != 1) {
            if (items[trackIndex].compareTo(items[trackIndex/2]) < 0) {
                E temp = (E) items[trackIndex];
                items[trackIndex] = items[trackIndex/2];
                items[trackIndex/2] = temp;
            } else break;
            trackIndex/=2;
        }
   }
   
   private boolean overSize() {
       return (numItems + 1) == items.length;
   }
   
   // precondition: The array is full
   // postcondition: Resize the 1D array items to be doubled   
   private void doubleCapacity()
   {
        Comparable[] newArray = new Comparable[items.length * 2];
        newArray[0] = null;
        for (int i = 1; i <= numItems; i++) {
            newArray[i] = items[i];
        }
        
        items = newArray;
   }
     
}  //HeapPriorityQueue_shell