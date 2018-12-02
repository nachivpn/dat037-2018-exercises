import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxPriorityQueue<E> {
    private PriorityQueue<E> pq;

    /**
     * Creates a new empty MaxPriorityQueue using the given comparison.
     *
     * Time complexity: O(1)
     *
     * @param c the comparator to use
     */
    public MaxPriorityQueue(final Comparator<E> c){
        pq = new PriorityQueue<E>(1, new Comparator<E>(){
            public int compare(E e1, E e2){
                return -1 * c.compare(e1,e2);
            }
        });
    }

    /**
     * Finds and returns the maximum element (if any).
     *
     * Time complexity: O(1).
     *
     * @return null when empty, else the maximum element
     */
    public E findMax(){
        // 'peek' on java.util.PriorityQueue takes O(1).
        return pq.peek();
    }

    /**
     * The maximum element (if any) is removed from the priority queue.
     *
     * Time complexity: O(log N), given a size of N.
     *
     * @return null when empty, else the maximum element
     */
    public E deleteMax(){
        // 'poll' on java.util.PriorityQueue takes O(log N).
        return pq.poll();
    }

    /**
     * Inserts an element into the priority queue.
     *
     * Time complexity: O(log N), given a size of N.
     *
     * @param e the element to insert
     * @throws NullPointerException when e is null
     */
    public void insert(E e){
        // 'add' on java.util.PriorityQueue takes O(log N).
        pq.add(e);
    }
}