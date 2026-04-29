import java.util.*;

public class Pd2DuongCaiMySetProgram
{
   public static void main(String[] args)
   {
      MySet<Integer> s1 = new MySet<Integer>();
      s1.add(1); s1.add(2); s1.add(3); s1.add(4); s1.add(5);

      MySet<Integer> s2 = new MySet<Integer>();
      s2.add(3); s2.add(4); s2.add(5); s2.add(6); s2.add(7);

      MySet<Integer> s3 = new MySet<Integer>();
      s3.add(2); s3.add(3);  

      System.out.println("s1 = " + s1);
      System.out.println("s2 = " + s2);
      System.out.println("s3 = " + s3);
      System.out.println();

      System.out.println("s1.union(s2)      = " + s1.union(s2));
      System.out.println("s1.intersect(s2)  = " + s1.intersect(s2));
      System.out.println("s1.difference(s2) = " + s1.difference(s2));
      System.out.println("s1 unchanged?     s1 = " + s1);
      System.out.println();

      System.out.println("s3.subset(s1)?    " + s3.subset(s1));      // true
      System.out.println("s1.subset(s3)?    " + s1.subset(s3));      // false
      System.out.println("s1.subset(s1)?    " + s1.subset(s1));      // false (not proper)
      System.out.println("s1.superset(s3)?  " + s1.superset(s3));    // true
      System.out.println("s3.superset(s1)?  " + s3.superset(s1));    // false
      System.out.println();
   }
}

class MySet<E> extends HashSet<E>
{
   public MySet()
   {
      super();
   }

   public MySet(Collection<E> c)
   {
      super(c);
   }

   // Returns union of this set and s — does NOT modify this set
   public Set<E> union(Set<E> s)
   {
      MySet<E> result = new MySet<E>(this);
      result.addAll(s);
      return result;
   }

   // Returns intersection of this set and s — does NOT modify this set
   public Set<E> intersect(Set<E> s)
   {
      MySet<E> result = new MySet<E>(this);
      result.retainAll(s);
      return result;
   }

   // Returns difference of this set and s — does NOT modify this set
   public Set<E> difference(Set<E> s)
   {
      MySet<E> result = new MySet<E>(this);
      result.removeAll(s);
      return result;
   }

   // True if this is a proper subset of s
   public boolean subset(Set<E> s)
   {
      return s.containsAll(this) && !this.equals(s);
   }

   // True if this is a proper superset of s
   public boolean superset(Set<E> s)
   {
      return this.containsAll(s) && !this.equals(s);
   }
}