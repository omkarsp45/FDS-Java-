import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a node in a directory tree, storing information about a file or directory.
 */
class Node {
    String name;
    int fileType; // 0-file, 1-directory
    long size;
    Date date;
    List<Node> children;

    /**
     * Constructor to initialize a Node with file/directory information.
     *
     * @param name     The name of the file or directory.
     * @param fileType The type of the file (0 for file, 1 for directory).
     * @param size     The size of the file in bytes.
     * @param date     The last modified date of the file or directory.
     */
    Node(String name, int fileType, long size, Date date) {
        this.name = name;
        this.fileType = fileType;
        this.size = size;
        this.date = date;
        this.children = new ArrayList<>();
    }
}