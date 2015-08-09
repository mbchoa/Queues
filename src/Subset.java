import java.util.Iterator;

public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            rq.enqueue(item);
        }

        Iterator i = rq.iterator();
        int numPrinted = 0;
        while (i.hasNext() && numPrinted < k) {
            StdOut.println(rq.sample());
            numPrinted++;
        }
    }
}
