import java.util.Scanner;

/**
 * The Main class represents a simple Priority Queue menu-driven program
 * where users can interact with a priority queue by inserting elements,
 * finding the minimum element, removing the minimum element, checking the size,
 * checking if it is empty, and exiting the program.
 */
public class Main {
    /**
     * The main method is the entry point of the program.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Creating a Priority Queue of Integer keys and String values
        PriorityQueue<Integer,String> pq = new PriorityQueue<>();

        // Displaying the Priority Queue Menu
        System.out.println("\nPriority Queue Menu:");
        System.out.println("1. Insert");
        System.out.println("2. Min");
        System.out.println("3. Remove Min");
        System.out.println("4. Size");
        System.out.println("5. Is Empty");
        System.out.println("6. Exit");

        // Handling user input in a loop until the user chooses to exit
        while (true) {
            System.out.println();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                // Inserting an element into the priority queue
                case 1:
                    System.out.print("Enter key: ");
                    int key = scanner.nextInt();
                    System.out.print("Enter value: ");
                    String value = scanner.next();
                    pq.insert(key, value);
                    System.out.println("Entry inserted.");
                    break;

                // Finding and displaying the minimum element in the priority queue
                case 2:
                    Entry<Integer, String> minEntry = pq.min();
                    if (minEntry != null) {
                        System.out.println("Min element: " + minEntry.getValue());
                    } else {
                        System.out.println("Priority queue is empty.");
                    }
                    break;

                // Removing and displaying the minimum element from the priority queue
                case 3:
                    Entry<Integer, String> removedEntry = pq.removeMin();
                    if (removedEntry != null) {
                        System.out.println("Removed min element: " + removedEntry.getValue());
                    } else {
                        System.out.println("Priority queue is empty.");
                    }
                    break;

                // Displaying the size of the priority queue
                case 4:
                    System.out.println("Size of priority queue: " + pq.size());
                    break;

                // Checking if the priority queue is empty
                case 5:
                    System.out.println("Is priority queue empty: " + pq.isEmpty());
                    break;

                // Exiting the program
                case 6:
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                    scanner.close(); // Closing the scanner before exiting
                    break;

                // Handling invalid user input
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
