import java.util.Scanner;

/**
 * Main is a console-based user interface to interact with a PositionalList implementation.
 */

public class Main{
    /**
     * Finds the position of a given element in the positional list.
     *
     * @param list    the positional list to search within
     * @param element the element to find
     * @return the position of the element if found, null otherwise
     */
    private static Position<String> findPosition(PositionalList<String> list, String element) {
        Position<String> currentPosition = list.first();
        while (currentPosition != null) {
            if (currentPosition.getElement().equals(element)) {
                return currentPosition;
            }
            currentPosition = list.after(currentPosition);
        }
        return null;
    }
    
    /**
     * The main entry point of the program.
     * Provides a menu-driven interface for the user to interact with the PositionalList.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        PositionalList<String> list = new LinkedPositionalList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Element to First");
            System.out.println("2. Add Element to Last");
            System.out.println("3. Add Element Before");
            System.out.println("4. Add Element After");
            System.out.println("5. Set Element ");
            System.out.println("6. Remove Element ");
            System.out.println("7. Size of List ");
            System.out.println("8. Check if List is Empty");
            System.out.println("9. Get First Element ");
            System.out.println("10. Get Last Element ");
            System.out.println("11. Get Element Before");
            System.out.println("12. Get Element After");
            System.out.println("13. Display List ");
            System.out.println("0. Exit Program");
            while (true) {
                System.out.println();
                System.out.print("Enter your choice: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine();  // consume newline

                Position<String> position;
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter element : ");
                        list.addFirst(scanner.nextLine());
                        break;

                    case 2:
                        System.out.print("Enter element : ");
                        list.addLast(scanner.nextLine());
                        break;

                    case 3:
                        System.out.print("Enter reference element :");
                        position = findPosition(list, scanner.nextLine());
                        if (position != null) {
                        System.out.print("Enter new element:");
                        list.addBefore(position, scanner.nextLine());
                        } else {
                        System.out.println("Reference element not found.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter reference element (after which new element will be added):");
                        position = findPosition(list, scanner.nextLine());
                        if (position != null) {
                            System.out.print("Enter new element:");
                            list.addAfter(position, scanner.nextLine());
                        } else {
                            System.out.println("Reference element not found.");
                        }
                        break;
            
                    case 5:
                        System.out.print("Enter reference element to set:");
                        position = findPosition(list, scanner.nextLine());
                        if (position != null) {
                        System.out.print("Enter new element:");
                        list.set(position, scanner.nextLine());
                        } else {
                        System.out.println("Reference element not found.");
                        }
                        break;

                    case 6:
                        System.out.print("Enter reference element to remove:");
                        position = findPosition(list, scanner.nextLine());
                        if (position != null) {
                        list.remove(position);
                        } 
                        else {
                        System.out.println("Reference element not found.");
                        }
                        break;

                    case 7:
                        System.out.println("Size of List : " + list.size());
                        break;

                    case 8:
                        System.out.println("List is Empty : " + list.isEmpty());
                        break;

                    case 9:
                        if(list.first() != null) 
                            System.out.println("First Element in List : " + list.first().getElement());
                        else
                            System.out.println("List is Empty");
                        break;

                    case 10:
                        if(list.last() != null)
                            System.out.println("Last Element in List : " + list.last().getElement());
                        else
                            System.out.println("List is empty");
                        break;

                    case 11:
                        System.out.print("Enter Reference element :");
                        position = findPosition(list, scanner.nextLine());
                        if (position != null) {
                            if (list.before(position) != null) {
                                System.out.println("Element Before Reference element : " + list.before(position).getElement());
                            }
                            else {
                                System.out.println("Given element is the first element, so there's no preceding element.");
                            }
                        }
                        else {
                            System.out.println("Reference element not found.");
                        }
                        break;
                    
                    case 12:
                        System.out.print("Enter reference element for which you want the succeeding element:");
                        position = findPosition(list, scanner.nextLine());
                        if (position != null) {
                            if (list.after(position) != null) {
                                System.out.println("After: " + list.after(position).getElement());
                            } else {
                                System.out.println("Given element is the last element, so there's no succeeding element.");
                            }
                        } else {
                            System.out.println("Reference element not found.");
                        }
                        break;

                    case 13:
                        list.display();
                        break;

                    case 0:
                        System.exit(0);
                }
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
