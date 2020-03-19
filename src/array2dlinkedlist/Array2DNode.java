package array2dlinkedlist;


/**
 * Author: Rafael Mendoza
 * Description: This class is what links one node after another. The right data field connects the columns and the down data field connects the rows.
 * @param <E>.
 */
public class Array2DNode<E> {
    //Data Fields
    private E item;
    protected Array2DNode down;          //refers to the columns of the list
    protected Array2DNode right;         //refers to the rows of the list

    //Default Constructro
    public Array2DNode() {}

    //Constructor which takes an item of any type and initializes the item data field
    public Array2DNode(E item) {
        this.item = item;
    }


    //getter for item;
    public E getItem()  {
        return this.item;
    }

    //setter for item
    public void setItem(E item) {
        this.item = item;
    }
}
