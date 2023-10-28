import java.util.*;
/**
 * MyLongArray is a class for managing an array of long values with various operations.
 */
public class MyLongArray {
    protected long[] a;
    protected int currentIndex;

    /**
     * Constructs a MyLongArray with the specified size.
     *
     * @param size The size of the array to be created.
     */
    public MyLongArray(int size) {
        a = new long[size];
        currentIndex = 0;
    }

    /**
     * Finds the index of the first occurrence of a specific long value in the array.
     *
     * @param searchKey The value to search for in the array.
     * @return The index of the first occurrence of the value, or -1 if not found.
     */
    public int find(long searchKey) {
        for (int i = currentIndex - 1; i >= 0; i--) {
            if (searchKey == a[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Inserts a long value into the array.
     *
     * @param value The value to be inserted.
     */
    public void insert(long value) {
        if (currentIndex < a.length) {
            a[currentIndex] = value;
            currentIndex++;
        } else {
            System.out.println("Array Is Full!! Unable to Insert More Elements.");
        }
    }

    /**
     * Retrieves the element at a specific index in the array.
     *
     * @param index The index of the element to retrieve.
     * @return The element at the specified index, or -1 if the index is out of bounds.
     */
    public long getElement(int index) {
        if (index < currentIndex) {
            return a[index];
        }
        return -1;
    }

    /**
     * Deletes the first occurrence of a specific long value from the array.
     *
     * @param value The value to be deleted.
     * @return true if the value was found and deleted, false otherwise.
     */
    public boolean delete(long value) {
        int index = find(value);
        if (index != -1) {
            for (int i = index; i < currentIndex - 1; i++) {
                a[i] = a[i + 1];
            }
            currentIndex--;
            return true;
        }
        return false;
    }

    /**
     * Displays the elements in the array.
     */
    public void display() {
        System.out.print("Displaying Array: ");
        for (int i = 0; i < currentIndex; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /**
     * Deletes duplicate elements in the array and returns an array of deleted elements.
     *
     * @return An array containing the deleted duplicate elements.
     */
    public int[] dupDelete() {
        int[] deletedElements = new int[currentIndex];
        int deletedCount = 0;
        for (int i = 0; i < currentIndex - 1; i++) {
            for (int j = i + 1; j < currentIndex; j++) {
                if (a[i] == a[j]) {
                    deletedElements[deletedCount++] = (int) a[j];
                    delete(a[j]);
                }
            }
        }
        return Arrays.copyOf(deletedElements, deletedCount);
    }

    /**
     * Inserts a long value at a specific index in the array.
     *
     * @param index The index at which to insert the element.
     * @param value The value to be inserted.
     */
    public void insert(int index, long value) {
        if (index >= 0 && index < currentIndex) {
            currentIndex++;
            for (int i = currentIndex - 1; i > index; i--) {
                a[i] = a[i - 1];
            }
            a[index] = value;
        } else {
            System.out.println("Invalid index. Element cannot be inserted.");
        }
    }

    /**
     * Deletes the element at a specific index in the array and returns the deleted value.
     *
     * @param index The index of the element to be deleted.
     * @return The deleted element, or -1 if the index is out of bounds.
     */
    public long deleteAt(int index) {
        long temp = a[index];
        delete(a[index]);
        return temp;
    }

    /**
     * Returns the index of the last element in the array.
     *
     * @return The index of the last element.
     */
    public int LastIndex() {
        return currentIndex;
    }
}
