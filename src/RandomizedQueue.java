import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;         // array of items
    private int N;            // number of elements on stack

    /**
     * Initializes an empty stack.
     */
    public RandomizedQueue() {
        a = (Item[]) new Object[2];
    }

    /**
     * Is this stack empty?
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Returns the number of items in the stack.
     * @return the number of items in the stack
     */
    public int size() {
        return N;
    }

    /**
     * Adds item to the end of the queue
     * @param item item added to end of queue
     */
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (N == a.length) resize(2*a.length);
        a[N++] = item;
    }

    /**
     * Resizes array with input size
     * @param capacity new size of array
     */
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    /**
     * Removes and returns item randomly from array
     * @return item removed from array
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Stack overflow");
        int removeIdx = StdRandom.uniform(N);
        Item item = a[removeIdx];
        a[removeIdx] = a[N-1];
        a[N-1] = null;
        N--;
        if (N > 0 && N == a.length/4) resize(a.length/2);
        return item;
    }

    /**
     * Returns an item randomly from array
     * @return item sampled from array
     */
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Stack overflow");
        return a[StdRandom.uniform(N)];
    }

    /**
     * Returns an iterator which randomly iterates through array
     * @return iterator which iterates through original array randomly
     */
    public Iterator<Item> iterator() { return new RandomArrayIterator(); }

    private class RandomArrayIterator implements Iterator<Item> {
        private int i;
        private Item[] copy;

        public RandomArrayIterator() {
            copy = (Item[]) new Object[N];
            for(int j = 0; j < copy.length; j++)
                copy[j] = a[j];
            StdRandom.shuffle(copy);
        }

        public boolean hasNext() { return i < N; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy[i++];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        rq.enqueue("1");
        rq.enqueue("2");
        rq.enqueue("3");
        StdOut.println("sample:\t" + rq.sample());      //==> "CEKXSVFFZV"
        rq.enqueue("4");
        StdOut.println("\tdequeue:\t" + rq.dequeue());     //==> "OIKEUEMSXM"
        StdOut.println("sample:\t" + rq.sample());     //==> "null"
        StdOut.println("\tdequeue:\t" + rq.dequeue());    // ==> "RFMWTKBCTU"
        StdOut.println("sample:\t" + rq.sample());     //==> "null"
        StdOut.println("sample:\t" + rq.sample());     // ==> "CEKXSVFFZV"
        StdOut.println("sample:\t" + rq.sample());     // ==> "null"
        StdOut.println("\tdequeue:\t" + rq.dequeue());    // ==> "null"

        StdOut.println("\nsample:\t" + rq.sample());
        StdOut.println("sample:\t" + rq.sample());
        StdOut.println("sample:\t" + rq.sample());
        StdOut.println("sample:\t" + rq.sample());
        StdOut.println("sample:\t" + rq.sample());
        StdOut.println("sample:\t" + rq.sample());
    }
}