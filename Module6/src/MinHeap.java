import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    private T[] resize (T[] array){
        T[] tempArray;
        tempArray = (T[]) new Comparable[(array.length)*2];
        for (int i = 1; i<array.length; i++){
            tempArray[i] = array[i];
        }
        array = tempArray;
        return array;
    }
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null){
            throw new java.lang.IllegalArgumentException();
        }
        if ((size+1) == backingArray.length){
            backingArray = resize(backingArray);
        }
        backingArray[size+1] = data;
        size++;
        if (size >1){
            int iterator = size;
            while (iterator > 1){
                T temp = backingArray[iterator/2];
                if (data.compareTo(temp)<0){
                    backingArray[iterator/2] = data;
                    backingArray[iterator] = temp;
                }
                iterator = iterator/2;
            }
        }
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    private void replace(T[] array, int first, int last){
        T temp = array[first];
        array[first] = array[last];
        array[last] = temp;
    }
    public T remove() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (size == 0){
            throw new java.util.NoSuchElementException();
        }
        T temp2 = backingArray[1];
        replace(backingArray, 1, size);
        backingArray[size] = null;
        size--;
        int n = 1;
        while (n<=(size/2)){
            if ((backingArray[2*n]==null) || (backingArray[(2*n)+1]==null)){
                if (backingArray[2*n] != null){
                    if (backingArray[n].compareTo(backingArray[2*n])>0){
                        replace(backingArray, n, 2*n);
                    }
                    n = 2*n;
                }
                else if (backingArray[(2*n)+1] != null){
                    if (backingArray[n].compareTo(backingArray[(2*n)+1])>0){
                        replace(backingArray, n, (2*n)+1);
                    }
                    n = (2*n) + 1;
                }
                else{
                    n = size;
                }
            }
            else if ((backingArray[n].compareTo(backingArray[2*n])>0) && (backingArray[n].compareTo(backingArray[(2*n)+1])>0)){
                if (backingArray[(2*n)+1].compareTo(backingArray[2*n])<0){
                    replace(backingArray, n, (2*n)+1);
                    n = (2*n) + 1;
                }
                else if (backingArray[2*n].compareTo(backingArray[(2*n)+1])<0){
                    replace(backingArray, n, 2*n);
                    n = 2*n;
                }
            }
            else if ((backingArray[n].compareTo(backingArray[2*n])<0) && (backingArray[n].compareTo(backingArray[(2*n)+1])>0)){
                replace(backingArray, n, (2*n)+1);
                n = (2*n) + 1;
            }
            else if ((backingArray[n].compareTo(backingArray[2*n])>0) && (backingArray[n].compareTo(backingArray[(2*n)+1])<0)){
                replace(backingArray, n, 2*n);
                n = 2*n;
            }
            else{
                n = size;
            }
        }
        return temp2;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}