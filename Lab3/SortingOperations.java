import java.util.*;

/**
 * SortingOperations is a subclass of MyLongArray that provides sorting operations on an array of long values.
 */
public class SortingOperations extends MyLongArray {
    /**
     * Constructs a SortingOperations object with the specified size.
     *
     * @param size The size of the array to be created.
     */
    public SortingOperations(int size) {
        super(size);
    }

    /**
     * Initializes the array with random long values using the Random class.
     */
    public void initRandom() {
        Random rand = new Random();
        for (int i = 0; i < a.length; i++) {
            insert(rand.nextLong());
        }
    }

    /**
     * Sorts the array using the bubble sort algorithm and prints the sorted array.
     */
    public void bubbleSort() {
        long[] b;
        b = new long[currentIndex];
        for (int i = 0; i < currentIndex; i++) {
            b[i] = a[i];
        }
        for (int i = 0; i < currentIndex - 1; i++) {
            for (int j = 0; j < currentIndex - i - 1; j++) {
                if (b[j] > b[j + 1]) {
                    long temp = b[j];
                    b[j] = b[j + 1];
                    b[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < currentIndex; i++) {
            System.out.print(b[i] + " ");
        }
    }

    /**
     * Sorts the array using the selection sort algorithm and prints the sorted array.
     */
    public void selectionSort() {
        long[] b;
        b = new long[currentIndex];
        for (int i = 0; i < currentIndex; i++) {
            b[i] = a[i];
        }
        for (int i = 0; i < currentIndex - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < currentIndex; j++) {
                if (b[j] < b[minIndex]) {
                    minIndex = j;
                }
            }
            long temp = b[minIndex];
            b[minIndex] = b[i];
            b[i] = temp;
        }
        for (int i = 0; i < currentIndex; i++) {
            System.out.print(b[i] + " ");
        }
    }

    /**
     * Sorts the array using the insertion sort algorithm and prints the sorted array.
     */
    public void insertionSort() {
        long[] b;
        b = new long[currentIndex];
        for (int i = 0; i < currentIndex; i++) {
            b[i] = a[i];
        }
        for (int i = 1; i < currentIndex; i++) {
            long key = b[i];
            int j = i - 1;
            while (j >= 0 && b[j] > key) {
                b[j + 1] = b[j];
                j--;
            }
            b[j + 1] = key;
        }
        for (int i = 0; i < currentIndex; i++) {
            System.out.print(b[i] + " ");
        }
    }
}
