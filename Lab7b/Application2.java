import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * A program that displays the contents of a directory and calculates the total disk space.
 */
public class Application2{

    /**
     * Recursively builds a tree structure representing the contents of a directory.
     *
     * @param directory The directory to build the tree for.
     * @return The root node of the directory tree.
     */
    private static Node buildTree(File directory) {
        Node root = new Node(directory.getName(), 1, 0, new Date());

        for (File file : directory.listFiles()) {
            Node node = getFileInfo(file);
            root.children.add(node);

            if (file.isDirectory()) {
                node.children.addAll(buildTree(file).children);
            }
        }
        return root;
    }

    /**
     * Retrieves information about a file and creates a corresponding Node.
     *
     * @param file The file to get information for.
     * @return A Node representing the file information.
     */
    private static Node getFileInfo(File file) {
        String name = file.getName();
        int fileType = file.isFile() ? 0 : 1;
        long size = file.length();
        Date date = new Date(file.lastModified());
        return new Node(name, fileType, size, date);
    }

    /**
     * Displays the contents of a directory tree with indentation.
     *
     * @param node   The current node in the directory tree.
     * @param indent The indentation string based on the depth of the node.
     */
    private static void displayDirectoryContents(Node node, String indent) {
        System.out.printf("%s%s | Type: %s | Size: %d bytes | Date: %s%n",
                indent,
                node.name,
                node.fileType == 0 ? "File" : "Directory",
                node.size,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(node.date));

        for (Node child : node.children) {
            displayDirectoryContents(child, indent + "  ");
        }
    }

    /**
     * The main method of the program that initiates the directory tree display.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args){
        // Replace "your_directory_path" with the path to the directory you want to analyze
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Directory Path : ");
        // C:\\Users\\Rahul\\OneDrive\\Desktop\\GIT-HUB\\FDS_Java
        String directoryPath = sc.nextLine();
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            Node root = buildTree(directory);
            displayDirectoryContents(root, "");
        } else {
            System.out.println("Invalid directory path.");
        }
        sc.close();
    }
}
