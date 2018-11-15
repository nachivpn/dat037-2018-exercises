import java.util.*;

public class BoundedQueue<T> {
    private final List<T> array;
    private int rear  = 0;
    private int front = 0;
    private int size = 0;

    BoundedQueue(int capacity) {
        array = new ArrayList<T>(capacity);
    }

    public int getCapacity() {
        return array.size();
    }

    public int getSize() {
        return size;
    }

    public void enqueue(T element) {
        if (size == array.size()) {
            throw new RuntimeException("No space left for another element in BoundedQueue");
        }
        if (rear == array.size()) {
            rear = 0;
        }
        size++;
        array.set(rear++, element);
    }

    public T dequeue() {
        if (front == array.size()) {
            front = 0;
        }
        size--;
        return array.get(front++);
    }

    public Iterable<T> queue() {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    private int pointer = front;

                    @Override
                    public boolean hasNext() {
                        return pointer == rear;
                    }

                    @Override
                    public T next() {
                        final T element = array.get(pointer);
                        if (++pointer == array.size())
                            pointer = 0;
                        return element;
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}
