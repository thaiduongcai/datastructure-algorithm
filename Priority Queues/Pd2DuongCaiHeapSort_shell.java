/****************************************************************************
 Name: Thai Duong Cai   
 Lab Assignment:  Heap Sort Program
 Purpose of the program:  Learn how to sort an array using heapsort
 
 What I Learned (be as specific as possible):
 I learned how heapsort works
 Learn how to make a better heapDown method
    heapDown
    1. Check if there is at least one child of the root.
    2. Having 3 index to track the left, right, and the largerChild -> left
    3. Check if right exists && greater than the left.
    4. Check if the parent greater than the larger child
     -> True -> break;
     -> False -> swap() -> k = largerChild (update k value)

 Learned how to write makeHeap() method
    1. Find number of nodes -> length - 1
    2. Find & store subtree index into -> index
    3. Make that subtree to be maxheap by using heapdown
    4. Find other subtrees -> index-- until index = 1
 
 How I feel about this lab:
 Do able!
 
 What I am wondering:
 
 The credits: who and/or what website(s) helped you (must state 
 what information you got from the helper or website)
 I just used chatGPT
  - Explain the concepts
  - Save time
  - Fix my code -> I write my own code first and check to see what's wrong, if there is.
 Students (names) you helped (to what extent, be specific): 
 ****************************************************************************/


public class Pd2DuongCaiHeapSort_shell
{
   public static void main(String[] args)
   {
   //Part 1: Given a max heap, sort it. Do this part first. 
    //   double heap[] = {-1,99,80,85,17,30,84,2,16,1};
    //   display(heap);
    //   sort(heap);
    //   display(heap);
    //   System.out.println(isSorted(heap));
   
//   Part 2:  Generate 10 random numbers, make a heap, sort it.
      int SIZE = 10;
      double[] heap = new double[SIZE + 1];
      heap = createRandom(heap);
      display(heap);
       makeHeap(heap);  
       display(heap); 
       sort(heap);
       display(heap);
       System.out.println(isSorted(heap));
   }
   
	//******* Part 1 ******************************************
   // precondition:
   // postcondition:
   public static void display(double[] array)
   {
      for(int k = 1; k < array.length; k++)
         System.out.print(array[k] + "    ");
      System.out.println("\n");	
   }
   // precondition: The array is a max-heap
   // postcondition:  Sort the array in ascending order (small to big)
   public static void sort(double[] array)
   {
      if (!isSorted(array)) 
      {
            int lastIndex = array.length-1;
            for (int i = 1; i < array.length; i++) 
            {
                swap(array, 1, lastIndex);
                lastIndex--;
                heapDown(array, 1, lastIndex);
            }    
      }
   }
   // precondition: n/a
   // postcondition: Move the first element to the end, and move the end to the first.   
   public static void swap(double[] array, int a, int b)
   {
        double temp = array[a];
        array[a] = array[b];
        array[b] = temp;
   }
   // precondition: n/a
   // postcondition: New array will be a max heap tree
   public static void heapDown(double[] array, int k, int size)
   {
        while (k*2 <= size)
        {
            int left = k*2;
            int right = left + 1;
            int largerChild = left;
            
            if (right <= size && array[right] > array[largerChild]) largerChild = right;
            
            if (array[k] >= array[largerChild]) break;
            
            swap(array, k, largerChild);
            
            k = largerChild;
        }
   }
   // precondition: n/a
   // postcondition: Return true if the array is sorted, vice versa
   public static boolean isSorted(double[] array)
   {
       
        for (int i = 1; i < array.length - 1; i++)
           if (array[i] > array[i+1]) return false;
        return true;
   }
   
   // ****** Part 2 *******************************************

	//Generate 100 random numbers between 1 and 100, formatted to 2 decimal places
   //postcondition:  array[0] == -1, the rest of the array is random
   public static double[] createRandom(double[] array)
   { 
       array[0] = -1;
       for (int i = 1; i < array.length; i++) {
           array[i] = Math.round((1.0 + Math.random() * 99.0) * 100) / 100.0;
       } 
       return array;
   }
   
   //Turn the random array into a MAX heap
   //postcondition:  array[0] == -1, the rest of the array is in heap-order
   private static void makeHeap(double[] array)
   {
        int N = array.length-1;
        int index = N/2;
        while (index >= 1) {
            heapDown(array, index, N);
            index--;
        }
   }
   
} // HeapSort_shell