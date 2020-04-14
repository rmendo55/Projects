package TreeImplementation;

public class TreeNode<E extends Comparable<E>>  {
    BSTNode<E> root;

    public TreeNode() {}

    public void insert(E key) {
        if (this.root == null) {
            this.root = new BSTNode<>(key);
        }
        else {
            this.root.insert(key);
        }
    }

    public BSTNode<E> getRoot() {
        if (this.root == null) {
            return null;
        }
        else {
            return this.root;
        }
    }

    public BSTNode<E> parent(E key) {
        return this.root.parent(key);
    }

    public Object[] children(E key) {
        return root.children(key);
    }

    public int numChildren(E key) {
        return root.numChildren(key, root);
    }

    public int maxDepth() {
        return root.maxDepth();
    }

   public void inorder() {
        this.root.inorder();
   }

   public void preorder() {
        this.root.preorder();
   }

   public void postOrder() {this.root.postorder();}



}
