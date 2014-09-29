/**
* Created by Cee on 9/27/14.
*/

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private class Node {
        private Node before;
        private Node next;
        private Item item;
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public RandomizedQueue() {
        head = new Node();
        tail = new Node();
    }

    public boolean isEmpty() {
        return (this.size == 0);
    }

    public int size() {
        return this.size;
    }

    public void enqueue(Item item) {
        if (Math.random() > 0.5) {
            addFirst(item);
        } else {
            addLast(item);
        }
    }

    public Item dequeue() {
        if (this.size == 0) throw new java.util.NoSuchElementException();
        int index = (int) (this.size * Math.random());
        Node node = head;
        while (index > 0) {
            node = node.next;
            index--;
        }
        Item ret = node.item;
//        System.out.println("deque: "+ret);
        if (node.next != null) node.next.before = node.before;
        if (node.before != null) node.before.next = node.next;
        this.size--;
        if (this.size == 0) {
            head = new Node();
            tail = new Node();
        }
        return ret;
    }

    public Item sample() {
        if (this.size == 0) throw new java.util.NoSuchElementException();
        int index = (int) (this.size * Math.random());
        Node node = head;
        while (index > 0) {
            node = node.next;
            index--;
        }
        Item ret = node.item;
//        System.out.println("sample: "+ret);
        return ret;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private Node current = head;
        private int countDown = size;
        private int index = (int) (size * Math.random());

        public RandomizedQueueIterator() {
            while (index > 0) {
                current = current.next;
                index--;
            }
        }

        public boolean hasNext() {
            return (countDown != 0);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current.item == null) throw new java.util.NoSuchElementException();
            Item item = current.item;
            int count = size - 1;
            while (count > 0) {
                count--;
                if (current.next != null) {
                    current = current.next;
                } else {
                    current = head;
                }
            }
            countDown--;
            return item;
        }
    }

    private void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        Node node = new Node();
        node.item = item;
        if (!isEmpty()) {
            node.next = head;
            head.before = node;
            head = node;
        } else {
            head = node;
            tail = head;
        }
        this.size++;
    }

    private void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        Node node = new Node();
        node.item = item;
        if (!isEmpty()) {
            node.before = tail;
            tail.next = node;
            tail = node;
        } else {
            tail = node;
            head = tail;
        }
        this.size++;
    }

    public static void main(String[] args) {
//        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
//        randomizedQueue.enqueue(1);
//        randomizedQueue.enqueue(2);
//        randomizedQueue.enqueue(3);
//        randomizedQueue.enqueue(4);
//        randomizedQueue.enqueue(5);
//
//        for (int i:randomizedQueue) {
//            System.out.println(i);
//        }
//        System.out.println("-------");
//        for (int i:randomizedQueue) {
//            System.out.println(i);
//        }
//        randomizedQueue.sample();
//        randomizedQueue.sample();
//        randomizedQueue.sample();
//        randomizedQueue.sample();
//        randomizedQueue.sample();
//
//
////        System.out.println(randomizedQueue.size());
////        randomizedQueue.dequeue();
    }


}
