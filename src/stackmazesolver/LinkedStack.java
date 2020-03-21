package stackmazesolver;

/**
 * This class was copied from the Data structure textbook from page 233.
 */

import java.util.Stack;


public class LinkedStack<E> {
    private SinglyLinkedList<E> list = new SinglyLinkedList<>(); //an empty list

    public LinkedStack() { }                //new Stack relies on the initially empty list

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(E element) {
        list.addFirst(element);
    }

    public E top() {
        return list.first();
    }

    public E pop() {
        return list.removeFirst();
    }
}
