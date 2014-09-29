/**
 * Created by Cee on 9/27/14.
 */
public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        String s;
        while ((s = StdIn.readString()) != null) {
            randomizedQueue.enqueue(s);
        }
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            System.out.println(randomizedQueue.dequeue());
        }
    }
}
