package stackmazesolver;

/**
 * This class was copied from the Data structure textbook from page 127.
 * @param <E>
 */

public class SinglyLinkedList<E> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public SinglyLinkedList() { }

    public int size() {
        return this.size;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty())
            return null;
        return this.head.getElement();
    }

    public E last() {
        if (isEmpty())
            return null;
        return tail.getElement();
    }

    public void addFirst(E e) {
        this.head = new Node(e, this.head);
        if (size == 0)
            tail = this.head;
        size++;
    }

    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
            if (isEmpty())
                this.head = newest;
            else
                tail.setNext(newest);
            tail = newest;
            size++;
    }

    public E removeFirst() {
        if (isEmpty())
            return null;
            E answer = this.head.getElement();
        this.head = this.head.getNext();
        size--;
        if (size == 0)
            this.tail = null;
            return answer;
    }







}
