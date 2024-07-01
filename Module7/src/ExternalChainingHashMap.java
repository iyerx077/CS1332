import java.util.NoSuchElementException;

/**
 * Your implementation of a ExternalChainingHashMap.
 */
public class ExternalChainingHashMap<K, V> {

    /*
     * The initial capacity of the ExternalChainingHashMap when created with the
     * default constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * The max load factor of the ExternalChainingHashMap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final double MAX_LOAD_FACTOR = 0.67;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private ExternalChainingMapEntry<K, V>[] table;
    private int size;

    /**
     * Constructs a new ExternalChainingHashMap with an initial capacity of INITIAL_CAPACITY.
     */
    public ExternalChainingHashMap() {
        //DO NOT MODIFY THIS METHOD!
        table = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[INITIAL_CAPACITY];
    }

    /**
     * Adds the given key-value pair to the map. If an entry in the map
     * already has this key, replace the entry's value with the new one
     * passed in.
     *
     * In the case of a collision, use external chaining as your resolution
     * strategy. Add new entries to the front of an existing chain, but don't
     * forget to check the entire chain for duplicate keys first.
     *
     * If you find a duplicate key, then replace the entry's value with the new
     * one passed in. When replacing the old value, replace it at that position
     * in the chain, not by creating a new entry and adding it to the front.
     *
     * Before actually adding any data to the HashMap, you should check to
     * see if the table would violate the max load factor if the data was
     * added. Resize if the load factor (LF) is greater than max LF (it is
     * okay if the load factor is equal to max LF). For example, let's say
     * the table is of length 5 and the current size is 3 (LF = 0.6). For
     * this example, assume that no elements are removed in between steps.
     * If another entry is attempted to be added, before doing anything else,
     * you should check whether (3 + 1) / 5 = 0.8 is larger than the max LF.
     * It is, so you would trigger a resize before you even attempt to add
     * the data or figure out if it's a duplicate. Be careful to consider the
     * differences between integer and double division when calculating load
     * factor.
     *
     * When regrowing, resize the length of the backing table to
     * (2 * old length) + 1. You should use the resizeBackingTable method to do so.
     *
     * @param key   The key to add.
     * @param value The value to add.
     * @return null if the key was not already in the map. If it was in the
     *         map, return the old value associated with it.
     * @throws IllegalArgumentException If key or value is null.
     */
    private int absIndex(K key, int length){
        return Math.abs(key.hashCode()%length);
    }
    public V put(K key, V value) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (key == null || value == null){
            throw new IllegalArgumentException();
        }
        size++;
        if (((double) size /table.length)>MAX_LOAD_FACTOR){
            resizeBackingTable(table.length);
        }
        if (table[absIndex(key, table.length)] == null){
            table[absIndex(key, table.length)] = new ExternalChainingMapEntry(key, value);
        }
        else{
            ExternalChainingMapEntry<K, V> curr = table[absIndex(key, table.length)];
            ExternalChainingMapEntry<K, V> index = table[absIndex(key, table.length)];
            while ((index.getNext() != null) && (index.getKey() != key)){
                index = index.getNext();
            }
            if (index.getKey() == key){
                index.setValue(value);
            }
            else{
                table[absIndex(key, table.length)] = new ExternalChainingMapEntry(key, value, curr);
            }
        }
        return value;
    }

    /**
     * Removes the entry with a matching key from the map.
     *
     * @param key The key to remove.
     * @return The value associated with the key.
     * @throws IllegalArgumentException If key is null.
     * @throws NoSuchElementException   If the key is not in the map.
     */
    public V remove(K key) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (key == null){
            throw new IllegalArgumentException();
        }
        ExternalChainingMapEntry<K, V> index = table[absIndex(key, table.length)];
        ExternalChainingMapEntry<K, V> curr = null;
        V currVal;
            //track current node, intitialize the rest of the nodes; set the rest of the nodes to after the current one
            while (index != null){
                if (index.getKey() != key){
                    curr = index;
                    index = index.getNext();
                }
                else {
                    if (curr == null){
                        table[absIndex(key, table.length)] = index.getNext();
                    }
                    else{
                        curr.setNext(index.getNext());
                    }
                    currVal = index.getValue();
                    size--;
                    return currVal;
                }
            }
                throw new NoSuchElementException();
    }

    /**
     * Helper method stub for resizing the backing table to length.
     *
     * This method should be called in put() if the backing table needs to
     * be resized.
     *
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed.
     *
     * Since resizing the backing table is working with the non-duplicate
     * data already in the table, you won't need to explicitly check for
     * duplicates.
     *
     * Hint: You cannot just simply copy the entries over to the new table.
     *
     * @param Length The new length of the backing table.
     */
    private void resizeBackingTable(int length) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        ExternalChainingMapEntry<K, V>[] temp = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[(2*length)+1];
        for (int i = 0; i<length; i++){
            if (table[i] != null){
                ExternalChainingMapEntry<K, V> curr = table[i];
                if (curr.getNext() == null){
                    temp[absIndex(curr.getKey(), temp.length)] = new ExternalChainingMapEntry(curr.getKey(), curr.getValue());
                }
                else{
                    while (curr.getNext() != null){
                            temp[absIndex(curr.getKey(), temp.length)] = new ExternalChainingMapEntry(curr.getKey(), curr.getValue());
                        curr = curr.getNext();
                    }
                    temp[absIndex(curr.getKey(), temp.length)] = new ExternalChainingMapEntry(curr.getKey(), curr.getValue());
                }
            }
        }
        table = temp;
    }

    /**
     * Returns the table of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The table of the map.
     */
    public ExternalChainingMapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

    /**
     * Returns the size of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the map.
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
/*[Executed at: Wed Jun 26 18:28:46 PDT 2024]

============================================================
ExternalChainingHashMap.java successfully compiled.
============================================================
Tests Passed: 18 / 25

[Test Failure: put] [-0.4] : This put test was inconclusive due to: java.lang.ArrayIndexOutOfBoundsException: Index -10 out of bounds for length 13
Here is the stack trace to help identify the error in your code:
	at ExternalChainingHashMap.put, line number: 80

[Test Failure: remove] [-0.4] : This remove test was inconclusive due to: java.lang.NullPointerException
Here is the stack trace to help identify the error in your code:
	at ExternalChainingHashMap.remove, line number: 132

[Test Failure: remove] [-0.4] : This remove test was inconclusive due to: java.lang.ArrayIndexOutOfBoundsException: Index -11 out of bounds for length 13
Here is the stack trace to help identify the error in your code:
	at ExternalChainingHashMap.remove, line number: 118

[Test Failure: remove] [-0.4] : This remove test was inconclusive due to: java.lang.NullPointerException
Here is the stack trace to help identify the error in your code:
	at ExternalChainingHashMap.remove, line number: 132

[Test Failure: validSize] [-0.4] : Size variable could not be validated for the following method(s) due to early test failure(s): remove, put.

[Test Failure: validData] [-0.4] : Returned data could not be validated for the following method(s) due to early test failure(s): put, remove.

[Test Failure: equals] [-0.4] : equals() was not used correctly when testing the following method(s): put. Correct equals() usage could not be validated for the following method(s) due to early test failure(s): remove.


Score: 7.2 / 10.0
============================================================*/