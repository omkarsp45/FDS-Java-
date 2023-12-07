import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a key-value pair to be used in the PriorityQueue class.
 *
 * @param <K> the type of keys in the entry.
 * @param <V> the type of values associated with the keys.
 */
class Entry<K, V> {
    private K key;
    private V value;

    /**
     * Constructs an entry with the specified key and value.
     *
     * @param key   the key of the entry.
     * @param value the value associated with the key.
     */
    public Entry(K key, V value){
        this.key = key;
        this.value = value;
    }

    /**
     * Gets the key of the entry.
     *
     * @return the key of the entry.
     */
    public K getKey(){
        return key;
    }

    /**
     * Gets the value associated with the key.
     *
     * @return the value associated with the key.
     */
    public V getValue(){
        return value;
    }
}

/**
 * This class represents a priority queue implementation using a binary heap.
 * The priority queue is based on a generic key-value pair, where keys are
 * comparable.
 *
 * @param <K> the type of keys in the priority queue, must be comparable.
 * @param <V> the type of values associated with the keys.
 */
public class PriorityQueue<K extends Comparable<K>, V> {
    // The binary heap representation of the priority queue.
    private List<Entry<K, V>> heap;

    /**
     * Constructs an empty priority queue.
     */
    public PriorityQueue(){
        heap = new ArrayList<>();
        // The first element is set to null for easier index calculations.
        heap.add(null);
    }

    /**
     * Inserts a key-value pair into the priority queue.
     *
     * @param key   the key of the entry.
     * @param value the value associated with the key.
     */
    public void insert(K key, V value){
        Entry<K, V> entry = new Entry<>(key, value);
        heap.add(entry);
        upHeap(heap.size()-1);
    }

    /**
     * Returns the entry with the minimum key in the priority queue.
     *
     * @return the entry with the minimum key, or null if the priority queue is empty.
     */
    public Entry<K, V> min(){
        if (isEmpty()) {
            return null;
        }
        return heap.get(1);
    }

    /**
     * Removes and returns the entry with the minimum key in the priority queue.
     *
     * @return the entry with the minimum key, or null if the priority queue is empty.
     */
    public Entry<K, V> removeMin(){
        if (isEmpty()) {
            return null;
        }

        Entry<K, V> minEntry = heap.get(1);
        heap.set(1, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        downHeap(1);
        return minEntry;
    }

    /**
     * Returns the number of entries in the priority queue.
     *
     * @return the size of the priority queue.
     */
    public int size(){
        return heap.size() - 1;
    }

    /**
     * Checks if the priority queue is empty.
     *
     * @return true if the priority queue is empty, false otherwise.
     */
    public boolean isEmpty(){
        return size() == 0;
    }

    // Helper method to maintain the heap property by moving the entry up the heap.
    private void upHeap(int index){
        while (index > 1) {
            int parentIndex = index / 2;
            if (heap.get(index).getKey().compareTo(heap.get(parentIndex).getKey()) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    // Helper method to maintain the heap property by moving the entry down the heap.
    private void downHeap(int index){
        int leftChild = index * 2;
        int rightChild = index * 2 + 1;
        int smallest = index;

        if (leftChild < heap.size() && heap.get(leftChild).getKey().compareTo(heap.get(smallest).getKey()) < 0) {
            smallest = leftChild;
        }

        if (rightChild < heap.size() && heap.get(rightChild).getKey().compareTo(heap.get(smallest).getKey()) < 0) {
            smallest = rightChild;
        }

        if (smallest != index){
            swap(index, smallest);
            downHeap(smallest);
        }
    }

    // Helper method to swap two entries in the heap.
    private void swap(int i, int j){
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
