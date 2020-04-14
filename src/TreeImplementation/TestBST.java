package TreeImplementation;

public class TestBST {
    public static void main(String[] args) {
        TreeNode<Integer> tree = new TreeNode<Integer>();
        tree.insert(60);
        tree.insert(55);
        tree.insert(100);
        tree.insert(45);
        tree.insert(57);
        tree.insert(67);
        tree.insert(107);

        System.out.println(tree.maxDepth());
        tree.inorder();
        tree.preorder();
        tree.postOrder();
    }

}
