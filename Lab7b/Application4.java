public class Application4{
    // Recursive method to perform pre-order traversal and display the tree
    private static void displayPreOrder(TreeNode root, int depth) {
        if (root != null) {
            // Display the current node
            displayNode(root, depth);

            // Recursively display the left and right subtrees
            displayPreOrder(root.left, depth + 1);
            displayPreOrder(root.right, depth + 1);
        }
    }

    // Helper method to display a node with proper indentation
    private static void displayNode(TreeNode node, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("|    ");
        }
        System.out.println("|__ " + node.data);
    }

    public static void main(String[] args) {
        // Creating the binary tree as shown in the example
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Displaying the binary tree using pre-order traversal
        displayPreOrder(root, 0);
    }
}

class TreeNode {
    int data;
    TreeNode  left, right;

    public TreeNode(int item) {
        data = item;
        left = right = null;
    }
}