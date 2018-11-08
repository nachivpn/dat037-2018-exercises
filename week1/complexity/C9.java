import java.util.Comparator;

public class C9 {
	/**
	 * Checks if an array has duplicates.
	 *
	 * Given an array of size N, the method makes O(N) comparisons. It will
	 * therefore take O(N) time with an O(1) comparator.
	 *
	 * @param a the array
	 * @param c the comparator
	 * @throws IllegalArgumentException if array missing, comparator missing,
	 *          or not sorted
	 * @return true if and only if the array has duplicates
	 */
	public static <E> boolean hasDuplicates(E[] a, Comparator<? super E> c){
		if( ! isSorted(a, c) ){
			throw new IllegalArgumentException();
		}
		for(int i=0 ; i < a.length - 1 ; i++) {
			if( c.compare(a[i], a[i+1]) == 0 ){
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if an array only contains non-null elements and is sorted.
	 *
	 * Given an array of size N, the method makes O(N) comparisons. It will
	 * therefore take O(N) time with an O(1) comparator.
	 *
	 * @param a the array
	 * @param c the comparator
	 * @throws IllegalArgumentException If array or comparator is missing.
	 * @return true if and only if the array does not contain null
	 *          elements and all elements are in the order of the comparator
	 */
	public static <E> boolean isSorted(E[] a, Comparator<? super E> c){
		if( a == null || c == null ){
			throw new IllegalArgumentException();
		}
		if( a.length > 0 && a[0] == null ){
			return false;
		}
		for(int i=0 ; i < a.length - 1 ; i++) {
			assert a[i] != null;
			if( a[i+1] == null || c.compare(a[i], a[i+1]) > 0 ){
				return false;
			}
		}
		return true;
	}

	private static boolean runtest() {
		Comparator<Integer> c = new Comparator<Integer>(){
			public int compare(Integer i1, Integer i2){
				return Integer.compare(i1, i2);
			}
		};

		try {
			hasDuplicates(new Integer[]{5,6,7,9,8}, c);
			return false;
		} catch(IllegalArgumentException e){
		}

		try {
			hasDuplicates(new Integer[]{5,6,7,null,9}, c);
			return false;
		} catch(IllegalArgumentException e){
		}

		if(   hasDuplicates(new Integer[]{5,6,7,8,9}, c) )return false;
		if( ! hasDuplicates(new Integer[]{5,5,7,8,9}, c) )return false;
		if( ! hasDuplicates(new Integer[]{5,6,7,9,9}, c) )return false;

		return true;
	}

	public static void main(String[] args) {
		System.out.println("C9: " + (runtest() ? "TEST OK" : "TEST FAILED"));
	}
}
