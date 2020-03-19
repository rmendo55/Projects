package array2dlinkedlist;


import java.util.List;

/**
 * Author: Rafael Mendoza
 * Description: This class populates either empyt nodes or actual nodes if the user instantiates this class with a 2d arrray. This class also provides methods allows the user to
 * add more empty nodes and also allows the user obtain any position from the 2d array.
 * @param <E>
 */

public class Array2D<E> {
    //all data fields are private
    private int rows;                          //number of rows
    private int cols;                          //number of columns
    Array2DNode head;                          //head is what instantiates a new Node

    //Default Constructor
    public Array2D() { }

    //A constructro which takes the initial number of rows and columns of the Array2D.
    //Initializes the Array2D with linked nodes that are initially empty
    public Array2D(int rowSize, int colSize) {
        //first add number of columns;
        for (int i = 0; i < colSize; i++) {
            addFirstCol();
        }
        //then add number of rows
        for (int j = 0; j < rowSize-1; j++) {
            addLastRow();
        }
    }
    //Constructor that accepts a noraml 2D Array
    //Initializes the rArray2D with actual values
    public Array2D(E[][] array) {
        int rows = array.length;
        int cols = array[0].length;
        for (int i = 0; i < cols; i++) {
            addFirstCol();
        }
        for (int j = 0; j < rows - 1; j++) {
            addFirstRow();
        }
        for (int k = 0; k < rows; k++) {
            for (int k2 = 0; k2 < cols; k2++) {
                set(k + 1, k2 + 1,  array[k][k2]);
            }
        }
    }
    public void addFirstCol() {
        if (this.rows == 0 && this.cols == 0) {
            this.head = new Array2DNode();
            this.head.right = null;
            this.head.down = null;
            ++this.rows;
            ++this.cols;
        }
        else if (this.rows == 1 && this.cols > 0) {
            Array2DNode current = this.head;
            this.head = new Array2DNode();
            this.head.right = current;
            this.head.down = null;
            ++this.cols;
        }
        else if (this.rows > 0) {
            Array2DNode currentCol = null;
            Array2DNode current = this.head;
            for (int i = 0; i < this.rows; i++) {
                if (i == 0) {
                    this.head = new Array2DNode();
                    currentCol = this.head;
                }
                else {
                    this.head = new Array2DNode();
                    this.head.down = currentCol;
                    currentCol = this.head;
                }
            }
            while (currentCol != null) {
                currentCol.right = current;
                current = current.down;
                currentCol = currentCol.down;

            }
            ++this.cols;
        }
    }

    public void addFirstRow() {
        if (this.rows == 0 && this.cols == 0) {
            this.head = new Array2DNode();
            this.head.right = null;
            this.head.down = null;
            ++this.rows;
            ++cols;
        } else if (this.rows > 0 && this.cols == 1) {
            Array2DNode current = this.head;
            this.head = new Array2DNode();
            this.head.down = current;
            this.head.right = null;
            ++this.rows;
        }
        else if (this.rows > 0 ) {
            Array2DNode currentRow = null;
            Array2DNode current = this.head;
            for (int i = 0; i < this.cols; i++) {
                if (i == 0) {
                    this.head = new Array2DNode();
                    currentRow = this.head;
                }
                else {
                    this.head = new Array2DNode();
                    this.head.right = currentRow;
                    currentRow = this.head;
                }
            }
            currentRow = this.head;
            while(currentRow != null) {
                currentRow.down = current;
                currentRow = currentRow.right;
                current = current.right;
            }
            ++this.rows;
        }
    }

    public void addLastCol() {
        if (this.rows == 0 && this.cols == 0) {
            this.head = new Array2DNode();
            this.head.right = null;
            this.head.down = null;
            ++this.rows;
            ++cols;
        }
        else if (this.rows == 1 && this.cols > 0) {
            Array2DNode newest = new Array2DNode();
            Array2DNode current = this.head;
            while(current.right != null) {
                current = current.right;
            }
            current.right = newest;
            ++this.cols;
        }
        else {
            Array2DNode currentNode = null;
            Array2DNode current = this.head;
            Array2DNode newest = new Array2DNode();
            //get the last node from the row
            while(current.right != null) {
                current = current.right;
            }
            current.right = newest;

            while(current.down != null) {
                currentNode = new Array2DNode();
                current.right.down = currentNode;
                current = current.down;
                current.right = currentNode;
            }
            ++this.cols;
        }
    }

    public void addLastRow() {
        if (this.rows == 0 && this.cols == 0) {
            this.head = new Array2DNode();
            this.head.right = null;
            this.head.down = null;
            ++this.rows;
            ++cols;
        }
        else if (this.rows > 0 && this.cols == 1) {
            Array2DNode newest = new Array2DNode();
            Array2DNode current = this.head;
            while(current.down != null) {
                current = current.down;
            }
            current.down = newest;
            ++this.rows;
        }
        else {
            Array2DNode currentNode = new Array2DNode();
            Array2DNode newest = new Array2DNode();
            Array2DNode current = this.head;
            while(current.down != null) {
                current = current.down;
            }

            current.down = newest;

            while(current.right != null) {
                currentNode = new Array2DNode();
                current.down.right = currentNode;
                current = current.right;
                current.down = currentNode;
            }
            ++this.rows;
        }
    }

    public void deleteFirstCol() {
        Array2DNode newHead = this.head.right;
        while(this.head.down != null) {
            this.head = this.head.down;
        }
        this.head = newHead;
        --this.cols;
    }

    public void deleteFirstRow() {
        Array2DNode newHead = this.head.down;
        while(this.head.right != null) {
            this.head = this.head.right;
        }
        this.head = newHead;
        --this.rows;
    }

    public void deleteLastCol() {
        Array2DNode current = this.head;
        Array2DNode previous = this.head;
        Array2DNode current2 = null;
        while(current.right != null) {
            previous = current;
            current = current.right;
        }
        previous.right = null;
        if (this.head.down != null) {
            current2 = this.head.down;
            while (current2 != null) {
                current = current2;
                previous = current;
                while (current.right != null) {
                    previous = current;
                    current = current.right;
                }
                previous.right = null;
                if (current2.down != null) {
                    current2 = current2.down;
                }
                else {
                    current2 = null;
                }
            }
        }

        --this.cols;
    }

    public void deleteLastRow() {
        Array2DNode current = this.head;
        Array2DNode previous = this.head;
        while(current.down != null) {
            previous = current;
            current = current.down;
        }
        current = previous;
        previous.down = null;
        while(current.right != null) {
            previous = current;
            current = current.right;
            previous.down = null;
        }
        previous.down = null;
        previous = current;
        previous.down = null;
        --this.rows;
    }
    public E get(int row, int col) {
        int row2 = row - 1;
        int col2 = col - 1;
        if (row < 0 || row > this.rows || col < 0 || col > this.cols) {
            throw new ArithmeticException();
        }
        Object element = null;
            Array2DNode current = this.head;
            if (row2 == 0 && row2 == col2) {
                element = (E)current.getItem();
            }
            else {
                int i = 0;
                int j = 0;
                while (i != row2) {
                    current = current.down;
                    ++i;
                }
                while(j != col2) {
                    current = current.right;
                    ++j;
                }
                element = (E)current.getItem();
            }
        return (E)element;
    }

    public Array2DNode getCol(int col) {
        if (col < 0 || col > this.cols - 1) {
            throw new ArithmeticException();
        }
        Array2DNode current = this.head;
        int i = 0;
        while(i != col) {
            current = current.right;
            ++i;
        }
        return current;
    }

    public Array2DNode getRow(int row) {
        if (row < 0 || row > this.rows - 1) {
            throw new ArithmeticException();
        }
        Array2DNode current = this.head;
        int i = 0;
        while(i != row) {
            current = current.down;
            ++i;
        }
        return current;
    }

    public void set(int row, int col, E item) {
        int row2 = row - 1;
        int col2 = col - 1;
        if (row < 0 || row > this.rows || col < 0 || col > this.cols) {
            throw new ArithmeticException();
        }
            if (row2 < this.rowSize() && col2 < this.colSize()) {
                if (row2 == 0) {
                    int i = 0;
                    Array2DNode current = this.head;
                    while (i != col2) {
                        current = current.right;
                        ++i;
                    }
                    current.setItem(item);
                }
                else {
                    Array2DNode current = this.head;
                    int j = 0;
                    while (j != row2) {
                        current = current.down;
                        ++j;
                    }
                    int i = 0;
                    while (i != col2) {
                        current = current.right;
                        ++i;
                    }
                    current.setItem(item);
                }
            }
    }

    public void addRow(int index) {
        int index2 = index - 1;
       if (index < 0 || index > rowSize()) {
           throw new ArithmeticException();
       }
       if (index2 == 0) {
           addFirstRow();
       }
       else if (index2 == rowSize() - 1){
           addLastRow();
       }
       else {
            Array2DNode top = this.head;
            for (int i = 0; i < index2 - 1; i++) {
                top = top.down;
            }
           Array2DNode buttom = top.down;
            Array2DNode newest = new Array2DNode();

            while(top.right != null) {
                top.down = newest;
                newest.down = buttom;
                top = top.right;
                buttom = buttom.right;
                newest.right = new Array2DNode();
                newest = newest.right;
            }
                top.down = newest;
                newest.down = buttom;
                ++this.rows;
       }
    }

    public void addCol(int index) {
        int index2 = index - 1;
                if (index < 0 || index > colSize()) {
            throw new ArithmeticException();
        }
        else if (index2 == 0) {
            addFirstCol();
        }
        else if (index2 == this.colSize() - 1){
            addLastCol();
        }
        else {
            Array2DNode left = this.head;
            for (int i = 0; i < index2 - 1; i++) {
               left = left.right;
            }
            Array2DNode right = left.right;
            Array2DNode newest = new Array2DNode();

            while(left.down != null) {
                left.right = newest;
                newest.right = right;
                left = left.down;
                right = right.down;
                newest.down = new Array2DNode();
                newest = newest.down;
            }
            left.right = newest;
            newest.right = right;
            ++this.cols;
        }
    }

    public void deleteRow(int index) {
        int index2 = index - 1;
        if (index < 0 || index > rowSize()) {
            throw new ArithmeticException();
        }
        if (index2 == 0) {
            deleteFirstRow();
        }
        else if (index2 == rowSize() - 1) {
            deleteLastRow();
        }
        else {
            Array2DNode top = this.head;
            Array2DNode top2 = this.head;
            for (int i = 0; i < index2 -1; i++) {
                top = top.down;
            }

            for (int j = 0; j <= index2; j++) {
                top2 = top2.down;
            }

            while(top.right != null) {
                top.down = top2;
                top = top.right;
                top2 = top2.right;
            }
            top.down = top2;
            --this.rows;
        }
    }

    public void deleteCol(int index) {
        int index2 = index - 1;
        if (index < 0 || index > colSize()) {
            throw new ArithmeticException();
        }
        if (index2 == 0) {
            deleteFirstCol();
        }
        else if (index2 == colSize() - 1) {
            deleteLastCol();
        }
        else {
            Array2DNode left = this.head;
            Array2DNode left2 = this.head;
            for (int i = 0; i < index2 - 1; i++) {
                left = left.right;
            }
            for (int j = 0; j <= index2; j++) {
                left2 = left2.right;
            }

            while(left.down != null) {
                left.right = left2;
                left = left.down;
                left2 = left2.down;
            }
            left.right = left2;
            --this.cols;
        }
    }

    public int colSize() {
        return this.cols;
    }
    public int rowSize() {
        return this.rows;
    }
}
