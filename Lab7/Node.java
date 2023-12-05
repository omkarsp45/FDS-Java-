import java.util.LinkedList;
import java.util.List;

/**
 * An interface representing a position in a tree with an element of type E.
 *
 * @param <E> The type of elements stored in the position.
 */
interface Position<E> {
    /**
     * Returns the element stored in this position.
     *
     * @return The element stored in this position.
     */
    E getElement();
}

/**
 * A class representing a node in a tree with an element of type E.
 *
 * @param <E> The type of elements stored in the node.
 */
class Node<E> implements Position<E> {
    private E element;
    private Node<E> parent;
    private List<Node<E>> children;

    /**
     * Constructs a new node with the given element and parent node.
     *
     * @param element The element to store in the node.
     * @param parent The parent node of this node, or null if it's the root.
     */
    public Node(E element, Node<E> parent) {
        this.element = element;
        this.parent = parent;
        this.children = new LinkedList<>();
    }

    /**
     * Returns the element stored in this node.
     *
     * @return The element stored in this node.
     */
    @Override
    public E getElement() {
        return element;
    }

    /**
     * Returns the parent node of this node.
     *
     * @return The parent node of this node, or null if it's the root.
     */
    public Node<E> getParent() {
        return parent;
    }

    /**
     * Returns a list of child nodes of this node.
     *
     * @return A list of child nodes of this node.
     */
    public List<Node<E>> getChildren() {
        return children;
    }

    /**
     * Checks if this node is an internal node (has children).
     *
     * @return True if this node is an internal node, false otherwise.
     */
    public boolean isInternal() {
        return !children.isEmpty();
    }

    /**
     * Checks if this node is an external node (has no children).
     *
     * @return True if this node is an external node, false otherwise.
     */
    public boolean isExternal() {
        return children.isEmpty();
    }
}
