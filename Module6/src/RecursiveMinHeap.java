import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class RecursiveMinHeap<T extends Comparable<? super T>> {

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
    public RecursiveMinHeap() {
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
     private T[] replace(T[] array, int first, int last){
     T temp = array[first];
     array[first] = array[last];
     array[last] = temp;
     return array;
     }
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    private T[] replace(T[] array, int first, int last){
        T temp = array[first];
        array[first] = array[last];
        array[last] = temp;
        return array;
    }
    private T leftChild(T[] array, int index){
        return array[2*index];
    }
    private T rightChild(T[] array, int index){
        return array[(2*index)+1];
    }
    int count = 0;
    private T[] helpRemove(T[] array, int index){
        System.out.println("Count: " + count);
        if (index>(size/2)){
            System.out.println("Base condition. Iterations: " + count);
            return array;
        }
        if ((leftChild(array,index) == null) || (rightChild(array,index) == null)){
            if (leftChild(array,index) != null){
                count++;
                System.out.println("First condition: " + leftChild(array,index));
                if (array[index].compareTo(leftChild(array,index))>0){
                    replace(array, index, 2*index);
                    return helpRemove(array, 2*index);
                }
                else{
                    return array;
                }
            }
            else if (array[2*index+1] != null){
                count++;
                System.out.println("Second condition: " + rightChild(array,index));
                if (array[index].compareTo(rightChild(array,index))>0){
                    replace(array, index, (2*index)+1);
                    return helpRemove(array, 2*index+1);
                }
                else{
                    System.out.println("array exited");
                    return array;
                }
            }
        }
        else if ((array[index].compareTo(leftChild(array,index))>0) && (array[index].compareTo(rightChild(array,index))>0)){
            count++;
            if (rightChild(array,index).compareTo(leftChild(array,index))<0){
                System.out.println("Third condition: " + rightChild(array,index));
                array = replace(array, index, (2*index)+1);
                return helpRemove(array, (2*index)+1);
            }
            else if (leftChild(array,index).compareTo(rightChild(array,index))<0){
                System.out.println("Fourth condition: " + leftChild(array,index));
                array = replace(array, index, 2*index);
                return helpRemove(array, 2*index);
            }
        }
        else if (array[index].compareTo(leftChild(array,index))>0){
            count++;
            System.out.println("Fifth condition: " + leftChild(array,index));
            array = replace(array, index, 2*index);
            return helpRemove(array, 2*index);
        }
        else if (array[index].compareTo(rightChild(array,index))>0){
            count++;
            System.out.println("Sixth condition: " + rightChild(array,index));
            array = replace(array, index, (2*index)+1);
            return helpRemove(array, (2*index)+1);
        }
        return array;
    }
    public T remove() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (size == 0){
            throw new java.util.NoSuchElementException();
        }
        T temp2 = backingArray[1];
        backingArray = replace(backingArray, 1, size);
        backingArray[size] = null;
        size--;
        int n = 1;
        backingArray = helpRemove(backingArray, n);
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
/*
[Executed at: Wed Jun 12 19:24:49 PDT 2024]

============================================================
MinHeap.java successfully compiled.
============================================================
Tests Passed: 24 / 29

[Test Failure: remove] [-0.34] : Unexpected content after removing 0 (the minimum element) from the MinHeap.
	Before : [null, 0, 7, 14, 21, 28, 35, 42, 49, 56, null, null, null]
	Expected : [null, 7, 21, 14, 49, 28, 35, 42, 56, null, null, null, null]
	Actual : [null, 7, 21, 14, 56, 28, 35, 42, 49, null, null, null, null]

[Test Failure: remove] [-0.34] : Unexpected content after removing 0 (the minimum element) from the MinHeap.
	Before : [null, 0, 7, 14, 28, 21, 35, 49, 42, 56, 63, 70, null]
	Expected : [null, 7, 21, 14, 28, 63, 35, 49, 42, 56, 70, null, null]
	Actual : [null, 7, 21, 14, 28, 70, 35, 49, 42, 56, 63, null, null]

[Test Failure: validSize] [-0.34] : Size variable could not be validated for the following method(s) due to early test failure(s): remove.

[Test Failure: validData] [-0.34] : Returned data could not be validated for the following method(s) due to early test failure(s): remove.

[Test Failure: compareTo] [-0.34] : Correct compareTo() usage could not be validated for the following method(s) due to early test failure(s): remove.


Score: 8.28 / 10.0*/