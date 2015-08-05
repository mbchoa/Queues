
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int N;      // size of list
    private Node first; // first item in list
    private Node last;  // last item in list

    // helper linked list class
    private class Node {
        private Item item;
        private Node prev;
        private Node next;
    }
    /**
     * Initializes Dequue object
     */
    public Deque() {
        first = last = null;
        N = 0;
    }

    /**
     * Returns if queue is empty
     * @return true if queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the size of the queue
     * @return integer value of queue size
     */
    public int size() {
        return N;
    }

    /**
     * Adds item to the beginning of the queue
     * @param item item added to beginning of queue
     */
    public void addFirst(Item item) {
        if(item == null)
            throw  new NullPointerException();
        Node newNode = new Node();
        newNode.item = item;
        if (isEmpty()) {
            first = last = newNode;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        N++;
    }

    /**
     * Add item to the end of the queue
     * @param item item added to end of queue
     */
    public void addLast(Item item) {
        if(item == null)
            throw  new NullPointerException();
        Node newNode = new Node();
        newNode.item = item;
        if (isEmpty()) {
            first = last = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        N++;
    }

    /**
     * Removes first item from queue
     * @return item removed from the front
     */
    public Item removeFirst() {
        if(isEmpty())
            throw new NoSuchElementException();
        Node nodeToRemove = first;
        if (N == 1) {
            first = last = null;
        } else {
            first = first.next;
            first.prev = null;
            nodeToRemove.next = null;
        }
        N--;
        return nodeToRemove.item;
    }

    /**
     * Removes last item from queue
     * @return item removed from the end
     */
    public Item removeLast() {
        if(isEmpty())
            throw new NoSuchElementException();
        Node nodeToRemove = last;
        if (N == 1) {
            first = last = null;
        } else {
            last = last.prev;
            last.next = null;
            nodeToRemove.prev = null;
        }
        N--;
        return nodeToRemove.item;
    }

    /**
     * Returns iterator over items in order from front to end
     * @return iterator object over items
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    /**
     * Unit testing
     * @param args
     */
    public static void main(String[] args) {

    }
}