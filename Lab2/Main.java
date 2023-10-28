import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Size Of Array : ");
        int size = sc.nextInt();
        MyLongArray a1 = new MyLongArray(size);
        int num = 0;
        System.out.print("Enter Number Of Elements : ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Element " + (i + 1) + " : ");
            num = sc.nextInt();
            a1.insert(num);
        }
        a1.display();
        System.out.println("\nOperations: ");

        System.out.println(
                "1.Find Index Number Of Specific Element.\n2.Insert Element At End Of Array.\n3.Find Value Of Element At Specific Index.\n4.Delete Specific Element In an Array.\n5.Display All Elements Of Array.\n6.Delete Duplicate Elements In an Array.\n7.Insert Element At Specific Index.\n8.Delete Element At Specific Index.\n0.Stop Performing Operations.");
        while(true) {
            System.out.print("\nChoose Operation : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Value Of Element : ");
                    int Element = sc.nextInt();
                    System.out.println("Index Of Given Element In Array Is : " + a1.find(Element));
                    break;
                case 2:
                    System.out.print("Enter Element To Be Add At End Of Array : ");
                    int Element1 = sc.nextInt();
                    a1.insert(Element1);
                    a1.display();
                    break;
                case 3:
                    System.out.print("Enter Index : ");
                    int index = sc.nextInt();
                    long val = a1.getElement(index);
                    if (val != -1) {
                        System.out.println("Value At Index " + index + " : " + a1.getElement(index));
                    } else {
                        System.out.println("Incorrect Array Index!");
                    }
                    break;
                case 4:
                    System.out.print("Enter Element You Want To Delete : ");
                    int Element2 = sc.nextInt();
                    if (a1.delete(Element2)) {
                        System.out.println("Element Deleted Successfully!!");
                        a1.display();
                    } else {
                        System.out.println("Element Not Found!!");
                    }
                    break;
                case 5:
                    a1.display();
                    break;
                case 6:
                    System.out.println("Fetching Duplicate Elements...");
                    int[] duplicateElements = a1.dupDelete();
                    if (duplicateElements.length > 0) {
                        System.out.println("Duplicate Element(s) Found.\nElements Deleted Successfully!!");
                        a1.display();
                        System.out.print("Deleted Elements: ");
                        for (int element : duplicateElements) {
                            System.out.print(element + " ");
                        }
                        System.out.println();
                    } else {
                        System.out.println("No Duplicate Element Found");
                    }
                    break;
                case 7:
                    System.out.print("Enter Element To Be Inserted :");
                    int Element3 = sc.nextInt();
                    System.out.print("Enter Index At Which Element Is To Be Inserted : ");
                    int index1 = sc.nextInt();
                    if (!(a1.LastIndex() >= size)) {
                        a1.insert(index1, Element3);
                        a1.display();
                    } else {
                        System.out.println("Invalid index. Element cannot be inserted.");
                    }
                    break;
                case 8:
                    System.out.print("Enter Index At Which Element To Be Deleted : ");
                    int index2 = sc.nextInt();
                    a1.deleteAt(index2);
                    a1.display();
                    break;
                default:
                    System.out.println("No Operation Exists For Your Choice");
            }
            if (choice == 0) {
                System.out.println("Exiting From Loop... ");
                break;
            }
        }
        sc.close();
    }
}

class MyLongArray {
    private long[] a;
    protected int currentIndex;

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