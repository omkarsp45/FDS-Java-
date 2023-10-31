import java.util.*;

/**
 * This class represents a generic stack with methods to perform stack operations.
 *
 * @param <T> the type of elements to be stored in the stack
 */
class Stack<T> {
    private T[] arr;
    private int top;

    /**
     * Initializes a new stack with the specified size.
     *
     * @param size the maximum capacity of the stack
     */
    public Stack(int size) {
        arr = (T[]) new Object[size];
        top = -1;
    }

    /**
     * Returns the current size of the stack.
     *
     * @return the number of elements in the stack
     */
    public int size() {
        return top + 1;
    }

    /**
     * Pushes an element onto the stack.
     *
     * @param element the element to be pushed onto the stack
     * @throws IllegalStateException if the stack is full
     */
    public void push(T element) {
        if (size() == arr.length) {
            throw new IllegalStateException("Stack is Full");   // Exception Handling
        }
        arr[++top] = element;
    }

    /**
     * Pops and returns the top element from the stack.
     *
     * @return the element that was removed from the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();    // Exception Handling
        }
        return arr[top--];
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Returns the top element of the stack without removing it.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();    // Exception Handling
        }
        return arr[top];
    }    
}

