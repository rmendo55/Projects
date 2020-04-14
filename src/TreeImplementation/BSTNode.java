package TreeImplementation;

public class BSTNode<E extends Comparable<E>> {
    protected E data;
    protected BSTNode<E> left;
    protected BSTNode<E> right;

    //default Constructor
    public BSTNode (){}


    public BSTNode(E data) {
        this.data = data;
    }

    public void insert(E key) {
        //compare if key goes left or right
        if (key.compareTo(this.data) == 0 ){
            //element already exists
            return;
        }
        else if (key.compareTo(this.data) == -1) {
            //left subtree
            if (this.left == null) {
                this.left = new BSTNode<>(key);
            }
            else {
                //recurse left
                this.left.insert(key);
            }
        }
        else if (key.compareTo(this.data) == 1) {
            //right subtree
            if (this.right == null) {
                this.right = new BSTNode<>(key);
            }
            else {
                this.right.insert(key);
            }
        }
    }

    public E getElement() {
        return this.data;
    }

    public BSTNode<E> parent(E key) {
        return parentHelper(key, this);
    }

    public BSTNode<E> parentHelper(E key, BSTNode<E> current) {
        //if the key is the root then the root doesn't have parent then return null
        if (key.compareTo(this.data) == 0) {
            return null;
        }
        else {
            if (current.left.data.compareTo(key) == 0 || current.right.data.compareTo(key) == 0) {
                return current;
            }
            else {
                if (key.compareTo(current.data) == 1) {
                    return parentHelper(key, current.right);
                }
                else {
                    return parentHelper(key, current.left);
                }
            }

        }
    }

    public Object[] children(E key) {
        return childrenHelper(key, this);
    }

    public Object[] childrenHelper(E key, BSTNode<E> parent) {
        if (key.compareTo(parent.data) == 0) {
            if (parent.left != null && parent.right != null) {
                return new Object[]{parent.left.data, parent.right.data};
            }
            else if (parent.left == null && parent.right != null) {
                return new Object[]{null, parent.right};
            }
            else {
                return new Object[]{parent.left, null};
            }
        }
        else {
            if (key.compareTo(parent.data) == 1) {
                return childrenHelper(key, parent.right);
            }
            else {
                return childrenHelper(key, parent.left);
            }
        }
    }

    public int numChildren(E key, BSTNode<E> current) {
        if (key.compareTo(current.data) == 0) {
            if (current.left != null && current.right != null) {
                return 2;
            }
            else if (current.left == null && current.right != null || current.left != null && current.right == null) {
                return 1;
            }
            else {
                return 0;
            }
        }
        else {
            //recurse to the next node
            //check if to traverse left or right
            if (key.compareTo(current.data) == 1) {
                return numChildren(key, current.right);
            }
            else {
                return numChildren(key, current.left);
            }
        }
    }

    public int maxDepth() {
        return maxDepth(this);
    }
    public int maxDepth(BSTNode<E> node) {
        //base case
        if (node == null) {
            return 0;
        }
        else {
            //calculate max depth for left and right subtrees
            int maxLeft = maxDepth(node.left);
            int maxRight = maxDepth(node.right);

            if (maxLeft > maxRight) {
                return maxLeft + 1;
            }
            else {
                return maxRight + 1;
            }
        }
    }

    public void inorder(){
        if (this == null) {
            System.out.println("Tree is empty");
        }
        else {
            //tree is not empty (Traverse)
            if (this.left != null) {
                this.left.inorder();
            }
            else {
                System.out.println(this.getElement());
                return;
            }
            System.out.println(this.getElement());
            if (this.right == null) {
                return;
            }
            else {
                this.right.inorder();
                return;
            }
        }
    }

    public void preorder() {
        if (this == null) {
            System.out.println("The tree is empty");
            return;
        } else {
            if (this != null) {
                System.out.println(this.getElement());
                if (this.left != null) {
                    this.left.preorder();
                    if (this.right != null) {
                        this.right.preorder();
                    }
                }
                else {
                    if (this.right != null) {
                        this.right.preorder();
                        return;
                    }
                    else {
                        return;
                    }
                }

            }
        }
    }

    public void postorder() {
        if (this == null) {
            System.out.println("Tree is empty");
        }
        else {
            if (this.left != null) {
                this.left.postorder();
                if (this.right != null) {
                    this.right.postorder();
                    System.out.println(this.getElement());
                }
            }
            else {
                System.out.println(this.getElement());
                return;
            }

        }
    }


}
