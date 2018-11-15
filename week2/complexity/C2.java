import java.util.*;

/*
 * The main idea here is to keep a bit array (implemented as boolean array) 
 * to keep track of which elements must be in the subset to be generated next.
 *
 * NOTE: this implementation doesn't generate the empty set,
 * but it's trivial to modify the code to include it
 *
 * What is the time complexity of this implementation of subset generation?
 * One would need to sharpen this question to answer this question!
 */
public class C2 {	
   
    /*
     * this method "lazily" generates subsets,
     * i.e., a subset is computed/produced only when .next() is called
     * on the returned iterator
     */
    public static Iterator<Set<Integer>> subsets(int[] a){

	boolean[] bits = new boolean[a.length];
	
	return new Iterator<Set<Integer>>(){

	    /* increments the bit sequence (in the array) array by 1
	     * i.e., on calling increment(),
	     *       000 becomes 001
	     *       001 becomes 010
	     *       010 becomes 011  ...etc 
	     */
	    public void increment(){
		Boolean carry = true;
		for (int i = 0; i < bits.length && carry; i++)
		    if(bits[i]) {bits[i] = false; carry = true;}
		    else     {bits[i] = true; carry = false;}
	    }

	    /*
	     * Have we reached the maximum value for this sequence of bits?
	     * If not, there are more subsets (combinations) which can be generated.
	     */
	    public boolean hasNext(){
		boolean alldone = true;
		for(int i = 0; i < bits.length; i++){
		    alldone = bits[i] && alldone;
		}
		return !alldone;
	    }

	    /*
	     * Simply increments the bit array,
	     * and uses the bit array to guide adding elements to the subset
	     */
	    public Set<Integer> next(){
		increment();
		Set<Integer> s = new HashSet<Integer>();
		for(int i = 0; i < bits.length; i++)
		    if (bits[i]) s.add(a[i]);
		return s;
	    }
	    
	};
    }

    public static void main(String[] args){
	Iterator it = subsets( new int[]{ 1, 2, 3, 4} );
	while(it.hasNext()){
	    System.out.println(it.next());
	}
    }
}
