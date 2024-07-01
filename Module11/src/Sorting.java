import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Your implementation of various divide & conquer sorting algorithms.
 */

public class Sorting {

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(n log n)
     * And a best case running time of: O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: You may need to create a helper method that merges two arrays
     * back into the original T[] array. If two data are equal when merging,
     * think about which subarray you should pull from first.
     *
     * You may assume that the passed in array and comparator are both valid
     * and will not be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array to be sorted.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if ((arr.length <= 1) || (arr == null)){
            return;
        }
        else{
            T[] left = (T[]) new Object[arr.length/2];
            T[] right = (T[]) new Object[arr.length-(arr.length/2)];
            int n = 0;
            for (T t: arr){
                if (n <(arr.length/2)){
                    left[n] = t;
                }
                else if (n >=(arr.length/2)){
                    right[n-(arr.length/2)] = t;
                }
                n++;
            }
            mergeSort(left, comparator);
            mergeSort(right, comparator);
            int l = 0, r = 0;
            while ((l<left.length) && (r<right.length)){
                if (comparator.compare(left[l],right[r])<=0){
                    arr[l+ r] = left[l];
                    l++;
                }
                else{
                    arr[l+r] = right[r];
                    r++;
                }
            }
            while (l<left.length){
                arr[l+r] = left[l];
                l++;
            }
            while (r<right.length){
                arr[l+r] = right[r];
                r++;
            }
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(kn)
     * And a best case running time of: O(kn)
     *
     * Feel free to make an initial O(n) passthrough of the array to
     * determine k, the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * You may use an ArrayList or LinkedList if you wish, but it should only
     * be used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with merge sort. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * You may assume that the passed in array is valid and will not be null.
     *
     * @param arr The array to be sorted.
     */
    public static void lsdRadixSort(int[] arr) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        Queue<Integer>[] buckets = new LinkedList[19];
        int div = 1;
        int length = 1;
        int k = 0;
        for (int n : arr){
            if (Math.abs(n)>k){
                k = Math.abs(n);
            }
        }
        while (k>0){
            k = k/10;
            length++;
        }
        for (int a = 0; a<19; a++){
            buckets[a] = new LinkedList<>();
        }
        for (int j = 0; j<length; j++){
            for (int i : arr){
                int temp = i/div;
                buckets[(temp%10)+9].add(i);
            }
            div = div*10;
            int idx = 0;
            for (Queue<Integer> bucket : buckets){
                while (!bucket.isEmpty()){
                    arr[idx] = bucket.remove();
                    idx++;
                }
            }
        }
    }
}
/*[Executed at: Sat Jun 29 17:05:22 PDT 2024]

============================================================
Sorting.java successfully compiled.
============================================================
Tests Passed: 13 / 17

[Test Failure: mergeSort] [-0.59] : This mergeSort test was inconclusive due to: java.lang.StackOverflowError
Here is the stack trace to help identify the error in your code:
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58
	at Sorting.mergeSort, line number: 58

[Test Failure: lsdRadixSort] [-0.59] : Unexpected content after sorting array.
	Expected : [-1000, -999, 7, 8, 9]
	Actual : [-999, -1000, 7, 8, 9]

[Test Failure: lsdRadixSort] [-0.59] : Unexpected content after sorting array.
	Expected : [-2147483648, -2147483648, -9]
	Actual : [-9, -2147483648, -2147483648]

[Test Failure: compareTo] [-0.59] : Correct Comparator compareTo() usage could not be validated for the following method(s) due to early test failure(s): mergeSort.


Score: 7.65 / 10.0
============================================================*/