import java.util.*;

/**
 * This class represents a generic queue implemented using an array with methods
 * to perform common queue operations.
 *
 * @param <T> the type of elements to be stored in the queue
 */
class Queue<T> {
    private T[] arr;
    private int front;
    private int rear;
    private int size;

    /**
     * Initializes an empty queue with the specified capacity.
     *
     * @param capacity the maximum capacity of the queue
     */
    public Queue(int capacity) {
        arr = (T[])new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    /**
     * Adds an element to the back of the queue.
     *
     * @param element the element to be added to the queue
     */
    public void enqueue(T element) {
        if (isFull()) {
            throw new IllegalStateException("Queue is a Full");  //Exception Handling
        }
        rear = (rear + 1) % arr.length;
        arr[rear] = element;
        size++;
    }

    /**
     * Removes and returns the first element from the queue.
     *
     * @return the first element in the queue, or null if the queue is empty
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is Empty");    // Exception Handling
        }
        T removed = arr[front];
        front = (front + 1) % arr.length;
        size--;
        return removed;
    }

    /**
     * Returns the first element of the queue without removing it.
     *
     * @return the first element in the queue, or null if the queue is empty
     */
    public T first() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is Empty");    // Exception Handling
        }
        return arr[front];
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the number of elements in the queue
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if the queue is full.
     *
     * @return true if the queue is full, false otherwise
     */
    public boolean isFull() {
        return size == arr.length;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Capacity Of Queue: ");
        int capacity = sc.nextInt();
        Queue<Integer> queue = new Queue<>(capacity);
        System.out.println("1.Enqueue\n2.Dequeue\n3.First\n4.Size\n5.isEmpty\n6.Exit");

        while (true) {
            System.out.print("Enter Your Choice: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter Element to be added: ");
                        int element = sc.nextInt();
                        queue.enqueue(element);
                        break;
                    case 2:
                        Integer dequeued = queue.dequeue();
                        if (dequeued == null) {
                            System.out.println("Queue is Empty");
                        } else {
                            System.out.println("Dequeued Element: " + dequeued);
                        }
                        break;
                    case 3:
                        Integer first = queue.first();
                        if (first == null) {
                            System.out.println("Queue is Empty");
                        } else {
                            System.out.println("First Element: " + first);
                        }
                        break;
                    case 4:
                        System.out.println("Size of Queue: " + queue.size());
                        break;
                    case 5:
                        if (queue.isEmpty()) {
                            System.out.println("Queue is Empty");
                        } else {
                            System.out.println("Queue is Not Empty");
                        }
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
