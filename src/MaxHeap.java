import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Implementation of a MaxHeap.
 */
public class MaxHeap<T extends Comparable<? super T>> {

    /*
     * The initial capacity of the MaxHeap when created with the default
     * constructor.
     */
    public static final int INITIAL_CAPACITY = 13;
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new MaxHeap.
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     * Using BuildHeap Algorithm
     *
     * The backingArray should have capacity 2n + 1 (including the empty 0
     * index) where n is the number of data in the passed in ArrayList (not
     * INITIAL_CAPACITY). Index 0 should remain empty, indices 1 to n should
     * contain the data in proper order, and the rest of the indices should
     * be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws java.lang.IllegalArgumentException if data or any element in data is null
     */
    public MaxHeap(ArrayList<T> data) {
        if (data == null || data.contains(null)) {
            throw new IllegalArgumentException("Error: data or element is null.");
        }

        backingArray = (T[]) new Comparable[2 * data.size() + 1];
        size = data.size();

        for (int i = 0; i < size; i++) {
            if (data.get(i) == null) {
                throw new IllegalArgumentException("Error: data is empty");
            }
            backingArray[i + 1] = data.get(i);
        }

        for (int i = backingArray.length / 2; i > 0; i--) {
            downHeap(i);
        }
    }

    /**
     * Helper method to perform downHeap
     *
     * @param index index we want to downHeap
     */
    private void downHeap(int index) {
        if (2 * index > size) {
            return;
        }

        T curr = backingArray[index];
        T left = backingArray[2 * index];
        T right;

        if (2 * index + 1 > size) {
            right = null;
        } else {
            right = backingArray[2 * index + 1];
        }

        if (right == null) {
            if (curr.compareTo(left) <= 0) {
                backingArray[index] = left;
                backingArray[index * 2] = curr;
            }
        } else {
            if (curr.compareTo(left) < 0 || curr.compareTo(right) < 0) {
                if (left.compareTo(right) >= 0) {
                    backingArray[index] = left;
                    backingArray[index * 2] = curr;
                    downHeap(index * 2);
                } else {
                    backingArray[index] = right;
                    backingArray[index * 2 + 1] = curr;
                    downHeap(index * 2 + 1);
                }
            }
        }
    }

    /**
     * Adds the data to the heap.
     *
     * If sufficient space is not available in the backing array (the backing
     * array is full except for index 0), resize it to double the current
     * length.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error: data is empty.");
        }

        if (size == backingArray.length - 1) {
            T[] temp = (T[]) new Comparable[backingArray.length * 2];

            for (int i = 0; i < backingArray.length; i++) {
                temp[i] = backingArray[i];
            }
            backingArray = temp;
        }
        backingArray[++size] = data;
        upHeap(size);
    }

    /**
     * Helper method to perform upHeap
     *
     * @param index index we want to upHeap
     */
    private void upHeap(int index) {
        if (index == 1) {
            return;
        }

        T curr = backingArray[index];
        T parent = backingArray[index / 2];

        if (curr.compareTo(parent) > 0) {
            backingArray[index / 2] = curr;
            backingArray[index] = parent;
            upHeap(index / 2);
        }
    }

    /**
     * Removes and returns the root of the heap.
     *
     * Does not shrink the backing array.
     *
     * Replace any unused spots in the array with null.
     *
     * @return the data that was removed
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Error: heap is empty.");
        }

        T root = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        downHeap(1);
        return root;
    }

    /**
     * Returns the maximum element in the heap.
     *
     * @return the maximum element
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T getMax() {
        if (size == 0) {
            throw new NoSuchElementException("Error: heap is empty");
        }
        return backingArray[1];
    }

    /**
     * Returns whether or not the heap is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the heap.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     */
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the backing array of the heap.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
