import java.util.*;

 /**
  * This is the main class for a simple Stack program that allows users to perform basic
  * stack operations including push, pop, size, isEmpty, and peek.
  */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Length Of Stack: ");
        int length = sc.nextInt();
        Stack<Integer> stack = new Stack<>(length);
        System.out.println("1.Push\n2.Pop\n3.Size\n4.isEmpty\n5.Peek\n6.Exit");

        while (true) {
            System.out.print("Enter Your Choice: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter Element to be added: ");
                        int element = sc.nextInt();
                        stack.push(element);
                        break;
                    case 2:
                        System.out.println("Popped Element: " + stack.pop());
                        break;
                    case 3:
                        System.out.println("Size Of Stack: " + stack.size());
                        break;
                    case 4:
                        if (stack.isEmpty()) {
                            System.out.println("Stack is Empty");
                        } else {
                            System.out.println("Stack is Not Empty");
                        }
                        break;
                    case 5:
                        System.out.println("Peek Element: " + stack.peek());
                        break;
                    case 6:
                        sc.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid Choice...");
                }
            } else {
                System.out.println("Invalid Choice. Please enter an integer.");
                sc.next();
            }
        }
    }
}
