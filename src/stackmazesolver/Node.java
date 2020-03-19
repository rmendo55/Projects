package stackmazesolver;

/**
 * This class was copied from the Data structure textbook from page 126.
 * @param <E>
 */

public class Node<E> {
    private E element;
    private Node<E> next;

    public Node(E e, Node<E> n) {
        this.element = e;
        this.next = n;
    }

    public E getElement() {
        return this.element;
    }

    public Node<E> getNext() {
        return this.next;
    }

    public void setNext(Node<E> n) {
        this.next = n;
    }

}
