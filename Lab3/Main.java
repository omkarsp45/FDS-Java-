import java.util.*;

/**
 * This Java program allows users to perform various operations on an array of long values.
 * Users can input values manually or initialize the array with random values, and then
 * perform operations such as finding, inserting, deleting, and sorting elements in the array.
 */
public class Main {

    /**
     * The main method is the entry point for the program.
     *
     * @param args The command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initialize the array size and number of elements
        System.out.print("Enter Size Of Array : ");
        int size = sc.nextInt();
        System.out.print("Enter Number Of Elements : ");
        int n = sc.nextInt();

        SortingOperations a1 = new SortingOperations(n);

        System.out.println("Options : ");
        System.out.println("1. Manually Give Values to Array.");
        System.out.println("2. Randomly Initialize Values.");
        System.out.print("Enter Your Choice : ");
        int q = sc.nextInt();

        switch (q) {
            case 1:
                // Manually input values
                for (int i = 0; i < n; i++) {
                    System.out.print("Enter Element " + (i + 1) + " : ");
                    long num = sc.nextLong();
                    a1.insert(num);
                }
                break;
            case 2:
                // Initialize the array with random values
                a1.initRandom();
                break;
        }

        a1.display();
        System.out.println("\nOperations:");

        // Display a menu of operations for the user to choose from
        System.out.println(
                "1. Find Index Number Of Specific Element.\n" +
                "2. Insert Element At End Of Array.\n" +
                "3. Find Value Of Element At Specific Index.\n" +
                "4. Delete Specific Element In an Array.\n" +
                "5. Display All Elements Of Array.\n" +
                "6. Delete Duplicate Elements In an Array.\n" +
                "7. Insert Element At Specific Index.\n" +
                "8. Delete Element At Specific Index.\n" +
                "9. Initialize Random Long Array.\n" +
                "10. Perform Bubble Sorting.\n" +
                "11. Perform Selection Sorting.\n" +
                "12. Perform Insertion Sorting.\n" +
                "0. Stop Performing Operations.");

        while(true){
            System.out.print("\nChoose Operation : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    // Find the index of a specific element
                    System.out.print("Enter Value Of Element : ");
                    long Element = sc.nextLong();
                    System.out.println("Index Of Given Element In Array Is : " + a1.find(Element));
                    break;
                case 2:
                    // Insert an element at the end of the array
                    System.out.print("Enter Element To Be Added At End Of Array : ");
                    long Element1 = sc.nextLong();
                    a1.insert(Element1);
                    a1.display();
                    break;
                case 3:
                    // Find the value of an element at a specific index
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
                    // Delete a specific element in the array
                    System.out.print("Enter Element You Want To Delete : ");
                    long Element2 = sc.nextLong();
                    if (a1.delete(Element2)) {
                        System.out.println("Element Deleted Successfully!!");
                        a1.display();
                    } else {
                        System.out.println("Element Not Found!!");
                    }
                    break;
                case 5:
                    // Display all elements of the array
                    a1.display();
                    break;
                case 6:
                    // Delete duplicate elements in the array
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
                    // Insert an element at a specific index
                    System.out.print("Enter Element To Be Inserted :");
                    long Element3 = sc.nextLong();
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
                    // Delete an element at a specific index
                    System.out.print("Enter Index At Which Element To Be Deleted : ");
                    int index2 = sc.nextInt();
                    a1.deleteAt(index2);
                    a1.display();
                    break;
                case 9:
                    // Initialize the array with random long values
                    a1.initRandom();
                    a1.display();
                    break;
                case 10:
                    // Perform bubble sorting on the array
                    System.out.println("Array after Bubble Sort:");
                    a1.bubbleSort();
                    System.out.println();
                    break;
                case 11:
                    // Perform selection sorting on the array
                    System.out.println("Array after Selection Sort:");
                    a1.selectionSort();
                    System.out.println();
                    break;
                case 12:
                    // Perform insertion sorting on the array
                    System.out.println("Array after Insertion Sort:");
                    a1.insertionSort();
                    System.out.println();
                    break;
                case 0:
                    // Exit the program
                    System.out.println("Exiting From Loop...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("No Operation Exists For Your Choice");
            }
        }
    }
}
