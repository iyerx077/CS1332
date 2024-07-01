/**
 * Your implementation of an AVL.
 */
public class AVL<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private AVLNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary. This is as simple as calling the balance() method on the
     * current node, before returning it (assuming that your balance method
     * is written correctly from part 1 of this assignment).
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null){
            throw new java.lang.IllegalArgumentException();
        }
        size++;
        root = helpAdd(root, data);
        root = balance(root);
    }

    private AVLNode<T> helpAdd(AVLNode<T> node, T data){
        if (node == null){
            node = new AVLNode<>(data);
        }
        System.out.println("Node: " + node.getData() + " has height: " + node.getHeight() + " and BF: " + node.getBalanceFactor());
        if (node.getData().compareTo(data)==0){
            size--;
            return node;
        }
        else if (node.getData().compareTo(data)<0){
            node.setRight(helpAdd(node.getRight(),data));
            //balance(node.getRight());
        }
        else if (node.getData().compareTo(data)>0){
            node.setLeft(helpAdd(node.getLeft(),data));
            //balance(node.getLeft());
        }
        node = balance(node);
        return node;
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     *    simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     *    replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     *    replace the data, NOT predecessor. As a reminder, rotations can occur
     *    after removing the successor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary. This is as simple as calling the balance() method on the
     * current node, before returning it (assuming that your balance method
     * is written correctly from part 1 of this assignment).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If the data is null.
     * @throws java.util.NoSuchElementException   If the data is not found.
     */
    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null){
            throw new java.lang.IllegalArgumentException();
        }
        AVLNode<T> dummy = (AVLNode<T>) new AVLNode<>(-1);
        root = helpRemove(root, data, dummy);
        size--;
        return dummy.getData();
    }
    private AVLNode<T> helpRemove(AVLNode<T> node, T data, AVLNode<T> dummy){
        if (node == null){
            throw new java.util.NoSuchElementException();
        }
        System.out.println("Node: " + node.getData() + " has height: " + node.getHeight() + " and BF: " + node.getBalanceFactor());
        balance(node);
        if (node.getData().compareTo(data)>0){
            node.setLeft(helpRemove(node.getLeft(), data, dummy));
        }
        else if (node.getData().compareTo(data)<0){
            node.setRight(helpRemove(node.getRight(), data, dummy));
        }
        else{
            dummy.setData(node.getData());
            if ((node.getLeft() == null) && (node.getRight() == null)){
                return null;
            }
            else if ((node.getLeft() != null) && (node.getRight() != null)){
                AVLNode<T> dummy2 = (AVLNode<T>) new AVLNode<>(-1);
                node.setRight(helpSuccess(node.getRight(), dummy2));
                node.setData(dummy2.getData());
            }
            else if (node.getLeft() != null){
                return node.getLeft();
            }
            else if (node.getRight() != null){
                return node.getRight();
            }
        }
        return balance(node);
    }

    private AVLNode<T> helpSuccess(AVLNode<T> node, AVLNode<T> dummy){
        if (node.getLeft() == null){
            dummy.setData(node.getData());
            return node.getRight();
        }
        else{
            node.setLeft(helpSuccess(node.getLeft(), dummy));
            return balance(node);
        }
    }

    /**
     * Updates the height and balance factor of a node using its
     * setter methods.
     *
     * Recall that a null node has a height of -1. If a node is not
     * null, then the height of that node will be its height instance
     * data. The height of a node is the max of its left child's height
     * and right child's height, plus one.
     *
     * The balance factor of a node is the height of its left child
     * minus the height of its right child.
     *
     * This method should run in O(1).
     * You may assume that the passed in node is not null.
     *
     * This method should only be called in rotateLeft(), rotateRight(),
     * and balance().
     *
     * @param currentNode The node to update the height and balance factor of.
     */
    private void updateHeightAndBF(AVLNode<T> node) {
        // COPY YOUR CODE FROM PART 1 OF THE ASSIGNMENT!
        int left;
        if (node.getLeft() != null){
            left = node.getLeft().getHeight();
        }
        else{
            left = -1;
        }
        int right;
        if (node.getRight() != null){
            right = node.getRight().getHeight();
        }
        else{
            right = -1;
        }
        node.setHeight(Math.max(left, right) + 1);
        node.setBalanceFactor(left - right);
    }

    /**
     * Method that rotates a current node to the left. After saving the
     * current's right node to a variable, the right node's left subtree will
     * become the current node's right subtree. The current node will become
     * the right node's left subtree.
     *
     * Don't forget to recalculate the height and balance factor of all
     * affected nodes, using updateHeightAndBF().
     *
     * This method should run in O(1).
     *
     * You may assume that the passed in node is not null and that the subtree
     * starting at that node is right heavy. Therefore, you do not need to
     * perform any preliminary checks, rather, you can immediately perform a
     * left rotation on the passed in node and return the new root of the subtree.
     *
     * This method should only be called in balance().
     *
     * @param currentNode The current node under inspection that will rotate.
     * @return The parent of the node passed in (after the rotation).
     */
    private AVLNode<T> rotateLeft(AVLNode<T> currentNode) {
        // COPY YOUR CODE FROM PART 1 OF THE ASSIGNMENT!
        AVLNode<T> B = currentNode.getRight();
        currentNode.setRight(B.getLeft());
        B.setLeft(currentNode);
        updateHeightAndBF(currentNode);
        updateHeightAndBF(B);
        return B;
    }

    /**
     * Method that rotates a current node to the right. After saving the
     * current's left node to a variable, the left node's right subtree will
     * become the current node's left subtree. The current node will become
     * the left node's right subtree.
     *
     * Don't forget to recalculate the height and balance factor of all
     * affected nodes, using updateHeightAndBF().
     *
     * This method should run in O(1).
     *
     * You may assume that the passed in node is not null and that the subtree
     * starting at that node is left heavy. Therefore, you do not need to perform
     * any preliminary checks, rather, you can immediately perform a right
     * rotation on the passed in node and return the new root of the subtree.
     *
     * This method should only be called in balance().
     *
     * @param currentNode The current node under inspection that will rotate.
     * @return The parent of the node passed in (after the rotation).
     */
    private AVLNode<T> rotateRight(AVLNode<T> currentNode) {
        // COPY YOUR CODE FROM PART 1 OF THE ASSIGNMENT!
        AVLNode<T> B = currentNode.getLeft();
        currentNode.setLeft(B.getRight());
        B.setRight(currentNode);
        updateHeightAndBF(currentNode);
        updateHeightAndBF(B);
        return B;
    }

    /**
     * Method that balances out the tree starting at the node passed in.
     * This method should be called in your add() and remove() methods to
     * facilitate rebalancing your tree after an operation.
     *
     * The height and balance factor of the current node is first recalculated.
     * Based on the balance factor, a no rotation, a single rotation, or a
     * double rotation takes place. The current node is returned.
     *
     * You may assume that the passed in node is not null. Therefore, you do
     * not need to perform any preliminary checks, rather, you can immediately
     * check to see if any rotations need to be performed.
     *
     * This method should run in O(1).
     *
     * @param currentNode The current node under inspection.
     * @return The AVLNode that the caller should return.
     */
    private AVLNode<T> balance(AVLNode<T> currentNode) {
        // COPY YOUR CODE FROM PART 1 OF THE ASSIGNMENT!

        /* First, we update the height and balance factor of the current node. */
        updateHeightAndBF(currentNode);

        if ( currentNode.getBalanceFactor() == -2/* Condition for a right heavy tree. */ ) {
            if ( currentNode.getRight().getBalanceFactor() == 1/* Condition for a right-left rotation. */ ) {
                currentNode.setRight(rotateRight(currentNode.getRight()));
            }
            currentNode = rotateLeft(currentNode);
        } else if ( currentNode.getBalanceFactor() == 2/* Condition for a left heavy tree. */ ) {
            if ( currentNode.getLeft().getBalanceFactor() == -1/* Condition for a left-right rotation. */ ) {
                currentNode.setLeft(rotateLeft(currentNode.getLeft()));
            }
            currentNode = rotateRight(currentNode);
        }

        return currentNode;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree.
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree.
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
/*[Executed at: Tue Jun 25 22:52:20 PDT 2024]

============================================================
AVL.java successfully compiled.
============================================================
Tests Passed: 27 / 43

[Test Failure: remove] [-0.23] : This remove test was inconclusive due to: java.lang.NullPointerException
Here is the stack trace to help identify the error in your code:
	at AVL.remove, line number: 102

[Test Failure: remove] [-0.23] : Unexpected content after removing 5 from the tree.

+---------------------------------+
| Initial Tree:                   |
|                                 |
|               4                 |
|        /               \        |
|       2                 6       |
|    /       \       /            |
|   0         3     5             |
|      \                          |
|       1                         |
|                                 |
|                                 |
| Expected Tree:                  |
|                                 |
|       2                         |
|    /       \                    |
|   0         4                   |
|      \   /   \                  |
|       1 3     6                 |
|                                 |
|                                 |
| Actual Tree:                    |
|                                 |
|               4                 |
|        /               \        |
|       2                 6       |
|    /       \                    |
|   0         3                   |
|      \                          |
|       1                         |
+---------------------------------+


[Test Failure: remove] [-0.23] : Unexpected content after removing 5 from the tree.

+---------------------------------+
| Initial Tree:                   |
|                                 |
|               4                 |
|        /               \        |
|       1                 6       |
|    /       \       /            |
|   0         3     5             |
|          /                      |
|         2                       |
|                                 |
|                                 |
| Expected Tree:                  |
|                                 |
|       3                         |
|    /       \                    |
|   1         4                   |
|  /   \       \                  |
| 0     2       6                 |
|                                 |
|                                 |
| Actual Tree:                    |
|                                 |
|               4                 |
|        /               \        |
|       1                 6       |
|    /       \                    |
|   0         3                   |
|          /                      |
|         2                       |
+---------------------------------+


[Test Failure: remove] [-0.23] : Unexpected content after removing 3 from the tree.

+---------------------------------+
| Initial Tree:                   |
|                                 |
|               4                 |
|        /               \        |
|       2                 5       |
|    /       \               \    |
|   1         3               6   |
|  /                              |
| 0                               |
|                                 |
|                                 |
| Expected Tree:                  |
|                                 |
|       4                         |
|    /       \                    |
|   1         5                   |
|  /   \       \                  |
| 0     2       6                 |
|                                 |
|                                 |
| Actual Tree:                    |
|                                 |
|               4                 |
|        /               \        |
|       2                 5       |
|    /                       \    |
|   1                         6   |
|  /                              |
| 0                               |
+---------------------------------+


[Test Failure: remove] [-0.23] : Unexpected content after removing 7 from the tree.

+-----------------------------------------------------------------+
| Initial Tree:                                                   |
|                                                                 |
|                               8                                 |
|                /                               \                |
|               5                                11               |
|        /               \               /               \        |
|       3                 7             9                12       |
|    /       \       /                       \                    |
|   1         4     6                        10                   |
|  /   \                                                          |
| 0     2                                                         |
|                                                                 |
|                                                                 |
| Expected Tree:                                                  |
|                                                                 |
|               8                                                 |
|        /               \                                        |
|       3                11                                       |
|    /       \       /       \                                    |
|   1         5     9        12                                   |
|  /   \   /   \       \                                          |
| 0     2 4     6      10                                         |
|                                                                 |
|                                                                 |
| Actual Tree:                                                    |
|                                                                 |
|                               8                                 |
|                /                               \                |
|               5                                11               |
|        /               \               /               \        |
|       3                 6             9                12       |
|    /       \                               \                    |
|   1         4                              10                   |
|  /   \                                                          |
| 0     2                                                         |
+-----------------------------------------------------------------+


[Test Failure: remove] [-0.23] : Unexpected content after removing 4 from the tree.

+-----------------------------------------------------------------+
| Initial Tree:                                                   |
|                                                                 |
|                               7                                 |
|                /                               \                |
|               4                                10               |
|        /               \               /               \        |
|       2                 6             8                11       |
|    /       \       /                       \                    |
|   0         3     5                         9                   |
|      \                                                          |
|       1                                                         |
|                                                                 |
|                                                                 |
| Expected Tree:                                                  |
|                                                                 |
|               7                                                 |
|        /               \                                        |
|       2                10                                       |
|    /       \       /       \                                    |
|   0         5     8        11                                   |
|      \   /   \       \                                          |
|       1 3     6       9                                         |
|                                                                 |
|                                                                 |
| Actual Tree:                                                    |
|                                                                 |
|                               7                                 |
|                /                               \                |
|               5                                10               |
|        /               \               /               \        |
|       2                 6             8                11       |
|    /       \                               \                    |
|   0         3                               9                   |
|      \                                                          |
|       1                                                         |
+-----------------------------------------------------------------+


[Test Failure: remove] [-0.23] : Unexpected content after removing 3 from the tree.

+---------------------------------+
| Initial Tree:                   |
|                                 |
|               4                 |
|        /               \        |
|       2                 6       |
|    /       \       /       \    |
|   0         3     5         7   |
|      \                          |
|       1                         |
|                                 |
|                                 |
| Expected Tree:                  |
|                                 |
|       4                         |
|    /       \                    |
|   1         6                   |
|  /   \   /   \                  |
| 0     2 5     7                 |
|                                 |
|                                 |
| Actual Tree:                    |
|                                 |
|               4                 |
|        /               \        |
|       2                 6       |
|    /               /       \    |
|   0               5         7   |
|      \                          |
|       1                         |
+---------------------------------+


[Test Failure: remove] [-0.23] : Unexpected content after removing 10 from the tree.

+-----------------------------------------------------------------+
| Initial Tree:                                                   |
|                                                                 |
|                               4                                 |
|                /                               \                |
|               2                                 9               |
|        /               \               /               \        |
|       0                 3             6                10       |
|            \                       /       \               \    |
|             1                     5         7              11   |
|                                              \                  |
|                                               8                 |
|                                                                 |
|                                                                 |
| Expected Tree:                                                  |
|                                                                 |
|               4                                                 |
|        /               \                                        |
|       2                 7                                       |
|    /       \       /       \                                    |
|   0         3     6         9                                   |
|      \           /       /   \                                  |
|       1         5       8    11                                 |
|                                                                 |
|                                                                 |
| Actual Tree:                                                    |
|                                                                 |
|                               4                                 |
|                /                               \                |
|               2                                 9               |
|        /               \               /               \        |
|       0                 3             6                11       |
|            \                       /       \                    |
|             1                     5         7                   |
|                                              \                  |
|                                               8                 |
+-----------------------------------------------------------------+


[Test Failure: remove] [-0.23] : Unexpected content after removing 7 from the tree.

+---------------------------------------------------------------------------------------------------------------------------------+
| Initial Tree:                                                                                                                   |
|                                                                                                                                 |
|                                                               12                                                                |
|                                /                                                               \                                |
|                               7                                                                15                               |
|                /                               \                               /                               \                |
|               2                                10                             13                               18               |
|        /               \               /               \                               \               /               \        |
|       0                 4             8                11                              14             16               19       |
|            \       /       \               \                                                               \                    |
|             1     3         6               9                                                              17                   |
|                          /                                                                                                      |
|                         5                                                                                                       |
|                                                                                                                                 |
|                                                                                                                                 |
| Expected Tree:                                                                                                                  |
|                                                                                                                                 |
|                               12                                                                                                |
|                /                               \                                                                                |
|               4                                15                                                                               |
|        /               \               /               \                                                                        |
|       2                 8             13               18                                                                       |
|    /       \       /       \               \       /       \                                                                    |
|   0         3     6        10              14     16       19                                                                   |
|      \           /       /   \                       \                                                                          |
|       1         5       9    11                      17                                                                         |
|                                                                                                                                 |
|                                                                                                                                 |
| Actual Tree:                                                                                                                    |
|                                                                                                                                 |
|                                                               12                                                                |
|                                /                                                               \                                |
|                               8                                                                15                               |
|                /                               \                               /                               \                |
|               2                                10                             13                               18               |
|        /               \               /               \                               \               /               \        |
|       0                 4             9                11                              14             16               19       |
|            \       /       \                                                                               \                    |
|             1     3         6                                                                              17                   |
|                          /                                                                                                      |
|                         5                                                                                                       |
+---------------------------------------------------------------------------------------------------------------------------------+


[Test Failure: remove] [-0.23] : Unexpected content after removing 14 from the tree.

+---------------------------------------------------------------------------------------------------------------------------------+
| Initial Tree:                                                                                                                   |
|                                                                                                                                 |
|                                                               12                                                                |
|                                /                                                               \                                |
|                               7                                                                15                               |
|                /                               \                               /                               \                |
|               4                                10                             14                               17               |
|        /               \               /               \               /                               /               \        |
|       2                 5             8                11             13                              16               18       |
|    /       \               \               \                                                                               \    |
|   1         3               6               9                                                                              19   |
|  /                                                                                                                              |
| 0                                                                                                                               |
|                                                                                                                                 |
|                                                                                                                                 |
| Expected Tree:                                                                                                                  |
|                                                                                                                                 |
|                               7                                                                                                 |
|                /                               \                                                                                |
|               4                                12                                                                               |
|        /               \               /               \                                                                        |
|       2                 5             10               17                                                                       |
|    /       \               \       /       \       /       \                                                                    |
|   1         3               6     8        11     15       18                                                                   |
|  /                                   \           /   \       \                                                                  |
| 0                                     9         13   16      19                                                                 |
|                                                                                                                                 |
|                                                                                                                                 |
| Actual Tree:                                                                                                                    |
|                                                                                                                                 |
|                                                               12                                                                |
|                                /                                                               \                                |
|                               7                                                                15                               |
|                /                               \                               /                               \                |
|               4                                10                             13                               17               |
|        /               \               /               \                                               /               \        |
|       2                 5             8                11                                             16               18       |
|    /       \               \               \                                                                               \    |
|   1         3               6               9                                                                              19   |
|  /                                                                                                                              |
| 0                                                                                                                               |
+---------------------------------------------------------------------------------------------------------------------------------+


[Test Failure: remove] [-0.23] : Unexpected content after removing 7 from the tree.

+---------------------------------------------------------------------------------------------------------------------------------+
| Initial Tree:                                                                                                                   |
|                                                                                                                                 |
|                                                               7                                                                 |
|                                /                                                               \                                |
|                               4                                                                15                               |
|                /                               \                               /                               \                |
|               1                                 6                             10                               18               |
|        /               \               /                               /               \               /               \        |
|       0                 3             5                               8                13             16               19       |
|                    /                                                       \       /       \               \                    |
|                   2                                                         9     11       14              17                   |
|                                                                                      \                                          |
|                                                                                      12                                         |
|                                                                                                                                 |
|                                                                                                                                 |
| Expected Tree:                                                                                                                  |
|                                                                                                                                 |
|                               8                                                                                                 |
|                /                               \                                                                                |
|               4                                15                                                                               |
|        /               \               /               \                                                                        |
|       1                 6             11               18                                                                       |
|    /       \       /               /       \       /       \                                                                    |
|   0         3     5               10       13     16       19                                                                   |
|          /                       /       /   \       \                                                                          |
|         2                       9       12   14      17                                                                         |
|                                                                                                                                 |
|                                                                                                                                 |
| Actual Tree:                                                                                                                    |
|                                                                                                                                 |
|                                                               8                                                                 |
|                                /                                                               \                                |
|                               4                                                                15                               |
|                /                               \                               /                               \                |
|               1                                 6                             10                               18               |
|        /               \               /                               /               \               /               \        |
|       0                 3             5                               9                13             16               19       |
|                    /                                                               /       \               \                    |
|                   2                                                               11       14              17                   |
|                                                                                      \                                          |
|                                                                                      12                                         |
+---------------------------------------------------------------------------------------------------------------------------------+


[Test Failure: validSize] [-0.23] : Size variable could not be validated for the following method(s) due to early test failure(s): remove.

[Test Failure: dataHandling] [-0.23] : If you are failing this test case, that means either:
	1) You are using == instead of compareTo() or equals() to compare Objects in remove().
	2) You are returning the passed in data (or some other incorrect data) in remove().

[Test Failure: compareTo] [-0.23] : Correct compareTo() usage could not be validated for the following method(s) due to early test failure(s): remove.

[Test Failure: nodeHeight] [-0.23] : Node heights could not be validated for the following method(s) due to early test failure(s): remove.

[Test Failure: nodeBalanceFactor] [-0.23] : Node balance factors could not be validated for the following method(s) due to early test failure(s): remove.


Score: 6.28 / 10.0
============================================================*/