import java.util.NoSuchElementException;

/**
 * Your implementation of a Singly-Linked List.
 */
public class SinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private SinglyLinkedListNode<T> head;
    private SinglyLinkedListNode<T> tail;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the element to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * ASSUMPTIONS:
     * - You may assume that the index will always be valid [0, size]
     * - You may assume that the data will not be null
     *
     * @param index the index to add the new element
     * @param data  the data to add
     */
    public void addAtIndex(int index, T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        SinglyLinkedListNode<T> previous = null;
        SinglyLinkedListNode<T> current = head;
        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<T>(data);
        if ((head == null) || (index == 0)){
            addToFront(data);
        }
        else{
            for (int i = 0;i<index;i++){
                previous = current;
                current = current.getNext();
            }
            previous.setNext(newNode);
            newNode.setNext(current);
        }
        tail = tail.getNext();
        size++;
    }

    /**
     * Adds the element to the front of the list.
     *
     * Method should run in O(1) time.
     *
     * @param data the data to add to the front of the list
     * @throws IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<T>(data);
        if (data == null){
            throw new IllegalArgumentException();
        }
        if (head == null) {
            head = newNode;
            tail = head;
        }
        else{
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }

    /**
     * Adds the element to the back of the list.
     *
     * Method should run in O(1) time.
     *
     * @param data the data to add to the back of the list
     * @throws IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null){
            throw new IllegalArgumentException();
        }
        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<T>(data);
        //head = new SinglyLinkedListNode<>();
        if (head == null) {
            head = new SinglyLinkedListNode<T>(data, null);
            tail = head;
        }
        else{
            SinglyLinkedListNode<T> current = head;
            while (current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(newNode);
            tail = current.getNext();
        }
        size++;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the front of the list
     * @throws NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        SinglyLinkedListNode<T> current = head;
        if (head == null) {
            tail = null;
            throw new NoSuchElementException();
        }
        else if (head.getNext() == null){
            head = null;
            tail = null;
        }else {
            head = head.getNext();
        }
        size--;
        return current.getData();
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Method should run in O(n) time.
     *
     * @return the data formerly located at the back of the list
     * @throws NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        SinglyLinkedListNode<T> current = head;
        if (head == null) {
            tail = null;
            throw new NoSuchElementException();
        }
        else if (head.getNext() == null){
            head = null;
            tail = head;
        }
        else {
            while (current.getNext() != null){
                tail = current;
                current = current.getNext();
            }
            tail.setNext(null);
        }
        size--;
        return current.getData();
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public SinglyLinkedListNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}