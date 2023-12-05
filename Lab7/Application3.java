import java.util.Scanner;

/**
 * Represents a node in a binary tree that holds string values.
 */
class Node {
    String value;
    Node left, right;
    /**
     * Constructs a node with the given value and initializes left and right children to null.
     *
     * @param value The value of the node.
     */
    public Node(String value) {
        this.value = value;
        this.left = this.right = null;
    }
    /**
     * Displays the arithmetic expression in infix order for the subtree rooted at this node.
     * Uses parentheses to indicate the structure of the expression.
     */
    public void displayInfix() {
        if (left != null || right != null) {
            System.out.print("(");
        }
        if (left != null) {
            left.displayInfix();
        }
        System.out.print(value);
        if (right != null) {
            right.displayInfix();
        }
        if (left != null || right != null) {
            System.out.print(")");
        }
    }
}
/**
 * Represents an application that allows the user to build a binary tree for an arithmetic expression.
 */
public class Application3{
    /**
     * Main method to execute the application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the root value:");
        String rootValue = scanner.nextLine();
        Node root = new Node(rootValue);
        buildTree(root, scanner);

        System.out.print("Arithmetic expression in infix order: ");
        root.displayInfix();
        scanner.close();
    }
    /**
     * Recursively builds the binary tree for the arithmetic expression.
     *
     * @param node    The current node for which the left and right children are being set.
     * @param scanner The scanner object for user input.
     */
    private static void buildTree(Node node, Scanner scanner) {
        System.out.print("Enter left child for " + node.value + " [or 'N' for no child]:");
        String leftValue = scanner.nextLine();
        if (!leftValue.equals("N")) {
            node.left = new Node(leftValue);
            buildTree(node.left, scanner);
        }
        System.out.print("Enter right child for " + node.value + " [or 'N' for no child]:");
        String rightValue = scanner.nextLine();
        if (!rightValue.equals("N")) {
            node.right = new Node(rightValue);
            buildTree(node.right, scanner);
        }
    }
}
