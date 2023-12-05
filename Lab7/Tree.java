import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A generic tree data structure.
 *
 * @param <E> The type of elements stored in the tree.
 */
public class Tree<E> implements Iterable<Position<E>> {
    private Node<E> root;
    private int size;

    /**
     * Constructs a new tree with a root element.
     *
     * @param rootElement The element to set as the root of the tree.
     */
    public Tree(E rootElement) {
        root = new Node<>(rootElement, null);
        size = 1;
    }

    /**
     * Returns the root position of the tree.
     *
     * @return The root position of the tree.
     */
    public Position<E> root() {
        return root;
    }

    /**
     * Returns the parent position of a given position.
     *
     * @param p The position for which to find the parent.
     * @return The parent position of the given position.
     */
    public Position<E> parent(Position<E> p) {
        Node<E> node = (Node<E>) p;
        return node.getParent();
    }

    /**
     * Returns a list of child positions of a given position.
     *
     * @param p The position for which to find children.
     * @return A list of child positions of the given position.
     */
    public List<Node<E>> children(Position<E> p) {
        Node<E> node = (Node<E>) p;
        return node.getChildren();
    }

    /**
     * Returns the number of children of a given position.
     *
     * @param p The position for which to count children.
     * @return The number of children of the given position.
     */
    public int numChildren(Position<E> p) {
        Node<E> node = (Node<E>) p;
        return node.getChildren().size();
    }

    /**
     * Checks if a given position is internal (has children).
     *
     * @param p The position to check.
     * @return True if the position is internal, false otherwise.
     */
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }

    /**
     * Checks if a given position is external (has no children).
     *
     * @param p The position to check.
     * @return True if the position is external, false otherwise.
     */
    public boolean isExternal(Position<E> p) {
        return numChildren(p) == 0;
    }

    /**
     * Checks if a given position is the root of the tree.
     *
     * @param p The position to check.
     * @return True if the position is the root, false otherwise.
     */
    public boolean isRoot(Position<E> p) {
        return p == root;
    }

    /**
     * Returns the size of the tree, which is the total number of positions.
     *
     * @return The size of the tree.
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the tree is empty (has no positions).
     *
     * @return True if the tree is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return getLeaves().iterator(); 
        // You can choose the desired traversal order
    }

    /**
     * Returns an iterable of all positions in the tree.
     *
     * @return An iterable of all positions in the tree.
     */
    public Iterable<Position<E>> positions() {
        List<Position<E>> allPositions = new LinkedList<>();
        if (!isEmpty()) {
            depthFirstTraversal(root, allPositions);
        }
        return allPositions;
    }

    /**
     * Adds a child with the given element to a parent position.
     *
     * @param parent The parent position to which to add a child.
     * @param element The element to set for the child.
     * @return The newly created child position.
     */
    public Position<E> addChild(Position<E> parent, E element) {
        Node<E> parentNode = (Node<E>) parent;
        Node<E> childNode = new Node<>(element, parentNode);
        parentNode.getChildren().add(childNode);
        size++;
        return childNode;
    }

    /**
     * Displays the tree structure in the console.
     */
    public void displayTree1() {
        displayTree1(root, "", 1, true);
    }
    
    private void displayTree1(Node<E> node, String parentNumber, int childNumber, boolean isRoot) {
        String nodeNumber = isRoot ? "" : parentNumber + childNumber;
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < nodeNumber.length(); i++) {
            indentation.append(" ");
        }
    
        System.out.println(indentation.toString() + nodeNumber + (isRoot ? "" : ". ") + node.getElement());
    
        List<Node<E>> children = node.getChildren();
        for (int i = 0; i < children.size(); i++) {
            displayTree1(children.get(i), nodeNumber + ".", i + 1, false);
        }
    }

    /**
     * Returns a list of sibling positions for a given position.
     *
     * @param p The position for which to find sibling positions.
     * @return A list of sibling positions for the given position.
     */
    public List<Position<E>> getSiblingPositions(Position<E> p) {
        Node<E> node = (Node<E>) p;
        Node<E> parent = node.getParent();

        if (parent == null) {
            return null; // Root has no siblings
        }

        List<Position<E>> siblings = new LinkedList<>();
        for (Node<E> sibling : parent.getChildren()) {
            if (sibling != node) {
                siblings.add(sibling);
            }
        }
        return siblings;
    }

    /**
     * Returns a list of leaf positions in the tree using a depth-first traversal.
     *
     * @return A list of leaf positions in the tree.
     */
    public List<Position<E>> getLeaves() {
        List<Position<E>> leaves = new LinkedList<>();
        if (!isEmpty()) {
            depthFirstTraversal(root, leaves);
        }
        return leaves;
    }

    /**
     * Returns a list of internal node positions in the tree using a depth-first traversal.
     *
     * @return A list of internal node positions in the tree.
     */
    public List<Position<E>> getInternalNodes() {
        List<Position<E>> internalNodes = new LinkedList<>();
        if (!isEmpty()) {
            depthFirstTraversalForInternalNodes(root, internalNodes);
        }
        return internalNodes;
    }

    private void depthFirstTraversal(Node<E> node, List<Position<E>> result) {
        if (node.isExternal()) {
            result.add(node);
        } else {
            result.add(node);
            for (Node<E> child : node.getChildren()) {
                depthFirstTraversal(child, result);
            }
        }
    }

    private void depthFirstTraversalForInternalNodes(Node<E> node, List<Position<E>> result) {
        if (node.isInternal()) {
            result.add(node);
            for (Node<E> child : node.getChildren()) {
                depthFirstTraversalForInternalNodes(child, result);
            }
        }
    }
}

