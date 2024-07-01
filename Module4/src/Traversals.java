import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Your implementation of the pre-order, in-order, and post-order
 * traversals of a tree.
 */
public class Traversals<T extends Comparable<? super T>> {

    /**
     * DO NOT ADD ANY GLOBAL VARIABLES!
     */

    /**
     * Given the root of a binary search tree, generate a
     * pre-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the pre-order traversal of the tree.
     */

    public List<T> preorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<T> preordertree = new ArrayList<T>();
        preordertree = helperpreorder(preordertree, root);
        return preordertree;
    }
    private List<T> helperpreorder(List<T> tree, TreeNode<T> root){
        if (root != null){
            tree.add(root.getData());
            helperpreorder(tree, root.getLeft());
            helperpreorder(tree, root.getRight());
        }
        return tree;
    }

    /**
     * Given the root of a binary search tree, generate an
     * in-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the in-order traversal of the tree.
     */
    public List<T> inorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<T> inordertree = new ArrayList<T>();
        inordertree = helperinorder(inordertree, root);
        return inordertree;
    }
    private List<T> helperinorder(List<T> tree, TreeNode<T> root){
        if (root != null){
            helperinorder(tree, root.getLeft());
            tree.add(root.getData());
            helperinorder(tree, root.getRight());
        }
        return tree;
    }

    /**
     * Given the root of a binary search tree, generate a
     * post-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the post-order traversal of the tree.
     */
    public List<T> postorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<T> postordertree = new ArrayList<T>();
        postordertree = helperpostorder(postordertree, root);
        return postordertree;
    }
    private List<T> helperpostorder(List<T> tree, TreeNode<T> root){
        if (root != null){
            helperpostorder(tree, root.getLeft());
            helperpostorder(tree, root.getRight());
            tree.add(root.getData());
        }
        return tree;
    }
}