/**
 * Created by Cee on 9/26/14.
 */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        private Node before;
        private Node next;
        private Item item;
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public Deque() {
        head = new Node();
        tail = new Node();
    }

    public boolean isEmpty() {
        return (this.size == 0);
    }

    public int size() {
        return this.size;
    }

    public void addFirst(Item item) {
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

    public void addLast(Item item) {
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

    public Item removeFirst() {
        if (head.item == null) throw new java.util.NoSuchElementException();
        Item ret = head.item;
        head = head.next;
        if (head == null) {
            head = new Node();
            tail = new Node();
        }
        this.size--;
        return ret;
    }

    public Item removeLast() {
        if (tail.item == null) throw new java.util.NoSuchElementException();
        Item ret = tail.item;
        tail = tail.before;
        if (tail == null) {
            head = new Node();
            tail = new Node();
        }
        this.size--;
        return ret;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return (current != null);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current.item == null) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    private void print() {
        Node node = head;
        while (node != null) {
            System.out.print(node.item+" ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
//        Deque<Integer> deque = new Deque<Integer>();
//        deque.removeFirst();
//        System.out.println(deque.isEmpty());
//
//        deque.addLast(3);
//        deque.addFirst(1);
//        deque.addLast(2);
//        deque.print();
//        System.out.println(deque.size());
//        System.out.println(deque.removeFirst());
//        System.out.println(deque.removeFirst());
//        deque.addLast(4);
//        for (Integer i:deque){
//            System.out.println(i);
//        }
//        deque.print();
//        System.out.println(deque.removeLast());
//        System.out.println(deque.removeLast());
//        System.out.println(deque.isEmpty());
    }
}
