public class UnboundedQueue<T> {
    private BoundedQueue<T> queue;

    UnboundedQueue() {
        this(1000);
    }

    UnboundedQueue(int capacity) {
        queue = new BoundedQueue<T>(capacity);
    }

    public void enqueue(T element) {
        if (queue.getSize() == queue.getCapacity()) {
            BoundedQueue<T> doubleQueue = new BoundedQueue<T>(queue.getCapacity() * 2);
            for (T e : queue.queue()) {
                doubleQueue.enqueue(e);
            }
            queue = doubleQueue;
        }
        queue.enqueue(element);
    }

    public T dequeue() {
        return queue.dequeue();
    }

    public Iterable<T> queue() {
        return queue.queue();
    }
}
