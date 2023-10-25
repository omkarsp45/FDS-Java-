import java.util.Arrays;

public class MyLongArray {
    private long[] a;
    private int currentIndex;

    public MyLongArray(int size) {
        a = new long[size];
        currentIndex = 0;
    }

    public int find(long searchKey) {
        for (int i = currentIndex - 1; i >= 0; i--) {
            if (searchKey == a[i]) {
                return i;
            }
        }
        return -1;
    }

    public void insert(long value) {
        if (currentIndex < a.length) {
            a[currentIndex] = value;
            currentIndex++;
        } else {
            System.out.println("Array Is Full!! Unable to Insert More Elements.");
        }
    }

    public long getElement(int index) {
        if (index < currentIndex) {
            return a[index];
        }
        return -1;
    }

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

    public void display() {
        System.out.print("Displaying Array : ");
        for (int i = 0; i < currentIndex; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

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

    public long deleteAt(int index) {
        long temp = a[index];
        delete(a[index]);
        return temp;
    }

    public int LastIndex() {
        return currentIndex;
    }
}
