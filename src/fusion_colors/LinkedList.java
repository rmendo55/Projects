package fusion_colors;

public class LinkedList {
    public Node head;
    public Node tail;
    private int size;

    public LinkedList() {}

    public String getFirst() {
        if (this.head == null) {
            return null;
        }
        else {
            return this.head.element;
        }
    }
    public String getLast() {
        if (this.tail == null) {
            return null;
        }
        else {
            return this.tail.element;
        }
    }

    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void addFirst(String element) {
        Node newNode = new Node(element);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        }
        else {
            newNode.next = this.head;
            this.head = newNode;
        }
        this.size++;
    }
    public void addLast(String element) {
        Node newNode = new Node(element);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        }
        else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
        this.size++;
    }
    public void removeFirst() {
        if (isEmpty()) {
            return;
        }
        else {
            if (this.size == 1) {
                this.tail = null;
            }
            this.head = this.head.next;
            this.size--;
        }
    }
    public void removeLast(){
        if (isEmpty()) {
            return;
        }
        else if (this.size == 1) {
            this.head = null;
            this.tail = null;
            this.size--;
        }
        else {
           Node current = this.head;
           Node previous = this.head;
           while (current.next != null) {
               previous = current;
               current = current.next;
           }
           previous.next = null;
           this.tail = previous;
           this.size--;
        }
    }
    public String remove(String element) {
        String s = element.substring(0, element.length() -1);
        String s2 = "";
        if (this.head.element.contains(s)) {
            s2 = this.head.element;
            this.removeFirst();
        }
        else if(this.tail.element.contains(s)) {
            s2 = this.tail.element;
            this.removeLast();
        }
        else {
            Node current = this.head;
            Node previous = this.head;
            while (current.next != null) {
                previous = current;
                if (current.element.contains(s)) {
                    s2 = current.element;
                    previous = current.next;
                    break;
                }
                current = current.next;
            }
        }
        this.size--;
        return s2;
    }

    public String get(int index) {
        int count = 0;
        Node current = this.head;
        while (count < index) {
            current = current.next;
            count++;
        }
        return current.element;
    }
    public boolean exist(String element) {
        boolean doesExist = false;
        Node current = this.head;
        if (current != null) {
            while (current.next != null) {
                if (current.element.contains(element)) {
                    doesExist = true;
                    break;
                }
                current = current.next;
            }
            if (current != null) {
                if (current.element.contains(element)) {
                    doesExist = true;
                }
            }
        }
        return doesExist;
    }
}

