import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents a position within a positional list, allowing for retrieval of an element.
 *
 * @param <E> the type of element stored in this position
 */
interface Position<E> {
    /**
     * Retrieves the element stored at this position.
     *
     * @return The stored element.
     * @throws IllegalStateException If the position is no longer valid.
     */
    E getElement() throws IllegalStateException;
}

/**
 * Interface defining the operations of a PositionalList.
 *
 * @param <E> The type of element stored in the list.
 */
interface PositionalList<E> extends Iterable<E> {

    /**
     * Returns the number of elements in the list.
     *
     * @return number of elements in the list
     */
    int size();

    /**
     * Tests whether the list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the first position in the list.
     *
     * @return the first position in the list (or null if empty)
     */
    Position<E> first();

    /**
     * Returns the last position in the list.
     *
     * @return the last position in the list (or null if empty)
     */
    Position<E> last();

    /**
     * Returns the position immediately before the given position.
     *
     * @param p a position of the list
     * @return the position immediately before the given position (or null if p is the first position)
     * @throws IllegalArgumentException if p is not a valid position for this list
     */
    Position<E> before(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns the position immediately after the given position.
     *
     * @param p a position of the list
     * @return the position immediately after the given position (or null if p is the last position)
     * @throws IllegalArgumentException if p is not a valid position for this list
     */
    Position<E> after(Position<E> p) throws IllegalArgumentException;

    /**
     * Adds an element to the beginning of the list.
     *
     * @param e the element to add
     * @return the position of the new element
     */
    Position<E> addFirst(E e);

    /**
     * Adds an element to the end of the list.
     *
     * @param e the element to add
     * @return the position of the new element
     */
    Position<E> addLast(E e);

    /**
     * Adds an element before the given position in the list.
     *
     * @param p a position of the list
     * @param e the element to add
     * @return the position of the new element
     * @throws IllegalArgumentException if p is not a valid position for this list
     */
    Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Adds an element after the given position in the list.
     *
     * @param p a position of the list
     * @param e the element to add
     * @return the position of the new element
     * @throws IllegalArgumentException if p is not a valid position for this list
     */
    Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Sets the element at the given position to a new element.
     *
     * @param p a position of the list
     * @param e the new element to set
     * @return the previous element at the position
     * @throws IllegalArgumentException if p is not a valid position for this list
     */
    E set(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Removes the element at the given position from the list.
     *
     * @param p a position of the list
     * @return the removed element
     * @throws IllegalArgumentException if p is not a valid position for this list
     */
    E remove(Position<E> p) throws IllegalArgumentException;

    /**
     * Displays the elements of the list in order.
     */
    void display();
}

/**
 * A doubly-linked implementation of the PositionalList interface.
 *
 * @param <E> the type of elements stored in the list
 */
class LinkedPositionalList<E> implements PositionalList<E> {

    // Inner class representing a node in the doubly-linked list
    private static class Node<E> implements Position<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        public E getElement() throws IllegalStateException {
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setElement(E e) {
            element = e;
        }

        public void setPrev(Node<E> p) {
            prev = p;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;

    /**
     * Initializes an empty list.
     */
    public LinkedPositionalList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    /**
     * Validates the given position and returns it as a Node.
     * This utility ensures the position is valid within the current list instance.
     *
     * @param p the position to validate
     * @return the position as a Node
     * @throws IllegalArgumentException if p is not a valid position for this list
     */
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
        Node<E> node = (Node<E>) p;
        if (node.getNext() == null) throw new IllegalArgumentException("p is no longer in the list");
        return node;
    }

    /**
     * Adds an element between the given nodes and returns its new position.
     *
     * @param e the element to insert
     * @param pred the predecessor node
     * @param succ the successor node
     * @return the position of the new element
     */
    private Position<E> position(Node<E> node) {
        if (node == header || node == trailer) return null;
        return node;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public Position<E> first() { return position(header.getNext()); }
    public Position<E> last() { return position(trailer.getPrev()); }
    public Position<E> before(Position<E> p) throws IllegalArgumentException { 
        Node<E> node = validate(p);
        return position(node.getPrev());
    }
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newest = new Node<>(e, pred, succ);
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }

    public Position<E> addFirst(E e) { return addBetween(e, header, header.getNext()); }

    public Position<E> addLast(E e) { return addBetween(e, trailer.getPrev(), trailer); }

    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        E answer = node.getElement();
        node.setElement(null);
        node.setNext(null);
        node.setPrev(null);
        return answer;
    }

    /**
     * Displays the elements of the list in order.
     */
    public void display() {
        Node<E> current = header.getNext();
        while (current != trailer) {
            System.out.print(current.getElement() + " -> ");
            current = current.getNext();
        }
        System.out.println("null");
    }

    /**
     * Returns an iterator for the elements of the list.
     *
     * @return an iterator for the elements of the list
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Position<E> cursor = first();
            private Position<E> recent = null;

            public boolean hasNext() {
                return (cursor != null);
            }

            public E next() throws NoSuchElementException {
                if (cursor == null) throw new NoSuchElementException("No Element at Next");
                recent = cursor;
                cursor = after(cursor);
                return recent.getElement();
            }

            public void remove() throws IllegalStateException {
                if (recent == null) throw new IllegalStateException("No Element in List");
                LinkedPositionalList.this.remove(recent);
                recent = null;
            }
        };
    }
}
