import java.util.ArrayList;

public class ALPriorityQueues {
    public static void main (String[] args) {
        ArrayListPriorityQueues<Integer> pq = new ArrayListPriorityQueues<>();
        
        pq.add(5);
        pq.add(4);
        pq.add(3);
        pq.add(1);
        pq.add(5);
        pq.add(7);
        pq.add(8);
        pq.add(9);
        
        System.out.println(pq.remove());
        
        pq.print();
    }
}


class ArrayListPriorityQueues <E extends Comparable<E>> {
   
    private ArrayList<E> a;
    
    public ArrayListPriorityQueues () {
        a = new ArrayList<>();
    }
    
    public boolean add (E obj) 
    {
        if (a.isEmpty()) return a.add(obj);
        int index = 0;
        for (int i = 0; i < a.size(); i++) {
            if (obj.compareTo(a.get(i)) > 0) 
                index = i+1;
        }
        
        a.add(index, obj);
        return true;
    }
    
    public E remove() {
        return a.remove(0);
    }
    
    public boolean isEmpty() {
        return a.size() == 0;
    }
    
    public void print() {
        System.out.println(a);
    }
}