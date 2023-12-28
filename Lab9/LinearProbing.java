import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

// concrete subclass for hash map using open addressing with linear probing
class ProbeHashMap<K, V> extends AbstractHashMap<K, V> {
    private MapEntry<K, V>[] table; // a fixed array of entries (all initially null)
    private MapEntry<K, V> DEFUNCT = new MapEntry<>(null, null); // sentinel

    // provide same constructors as base class
    public ProbeHashMap(int cap, int p) {
        super(cap, p);
    }

    public ProbeHashMap(int cap) {
        super(cap);
    }

    public ProbeHashMap() {
        super();
    }

    // create an empty table having length equal to current capacity
    protected void createTable() {
        table = (MapEntry<K, V>[]) new MapEntry[capacity];
    }

    // returns true if location is either empty or the "defunct" sentinel
    private boolean isAvailable(int j) {
        return (table[j] == null || table[j] == DEFUNCT);
    }

    // returns index with key k, or -(a + 1) such that k could be added at index a
    private int findSlot(int h, K k) {
        int avail = -1; // no slot available (thus far)
        int j = h; // index while scanning table
        do {
            if (isAvailable(j)) { // may be either empty or defunct
                if (avail == -1)
                    avail = j; // this is the first available slot!
                if (table[j] == null)
                    break; // if empty, search fails immediately
            } else if (table[j].getKey().equals(k))
                return j; // successful match
            j = (j + 1) % capacity; // keep looking (cyclically)
        } while (j != h); // stop if we return to the start
        return -(avail + 1); // search has failed
    }

    // returns value associated with key k in bucket with hash value h, or else null
    protected V bucketGet(int h, K k) {
        int j = findSlot(h, k);
        if (j < 0)
            return null; // no match found
        return table[j].getValue();
    }

    // associates key k with value v in bucket with hash value h; returns old value
    protected V bucketPut(int h, K k, V v) {
        int j = findSlot(h, k);
        if (j >= 0) // this key has an existing entry
            return table[j].setValue(v);
        table[-(j + 1)] = new MapEntry<>(k, v); // convert to proper index
        n++;
        return null;
    }

    // removes entry having key k from bucket with hash value h (if any)
    protected V bucketRemove(int h, K k) {
        int j = findSlot(h, k);
        if (j < 0)
            return null; // nothing to remove
        V answer = table[j].getValue();
        table[j] = DEFUNCT; // mark this slot as deactivated
        n--;
        return answer;
    }

    // return an iterable collection of all key-value entries of the map
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        for (int h = 0; h < capacity; h++)
            if (!isAvailable(h))
                buffer.add(table[h]);
        return buffer;
    }

    public boolean isEmpty() {
        return false;
    }
}

public class LinearProbing {
    // main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // scanner for user input
        ProbeHashMap<String, Integer> map = new ProbeHashMap<>(); // create a new ProbeHashMap
        boolean done = false; // flag for exiting the test
        System.out.println("Menu:");
        System.out.println("1. Put a key-value pair");
        System.out.println("2. Get a value by key");
        System.out.println("3. Remove a key-value pair");
        System.out.println("4. Check the size of the map");
        System.out.println("5. Check if the map is empty");
        System.out.println("6. Display the map entries");
        System.out.println("7. Exit");
        while (!done) {
            System.out.print("Enter your choice: ");
            int choice = getIntInput(sc); // read user choice with exception handling
            switch (choice) {
                case 1:
                    System.out.print("Enter the key: ");
                    String keyPut = sc.next(); // read the key
                    System.out.print("Enter the value: ");
                    int valuePut = getIntInput(sc); // read the value with exception handling
                    map.put(keyPut, valuePut); // put the key-value pair in the map
                    System.out.println("Successfully inserted (" + keyPut + ", " + valuePut + ") in the map.");
                    break;
                case 2:
                    System.out.print("Enter the key: ");
                    String keyGet = sc.next(); // read the key
                    int valueGet = map.get(keyGet); // get the value by the key
                    if (valueGet == 0) {
                        System.out.println("No value found for the key " + keyGet + ".");
                    } else {
                        System.out.println("The value for the key " + keyGet + " is " + valueGet + ".");
                    }
                    break;
                case 3:
                    System.out.print("Enter the key: ");
                    String keyRemove = sc.next(); // read the key
                    int valueRemove = map.remove(keyRemove); // remove the key-value pair from the map
                    if (valueRemove == 0) {
                        System.out.println("No key-value pair found for the key " + keyRemove + ".");
                    } else {
                        System.out
                                .println("Successfully removed (" + keyRemove + ", " + valueRemove + ") from the map.");
                    }
                    break;
                case 4:
                    System.out.println("The size of the map is " + map.size() + ".");
                    break;
                case 5:
                    System.out.println("The map is " + (map.isEmpty() ? "empty." : "not empty."));
                    break;
                case 6:
                    System.out.println("The map entries are:");
                    for (AbstractHashMap.Entry<String, Integer> entry : map.entrySet()) {
                        System.out.println("(" + entry.getKey() + ", " + entry.getValue() + ")");
                    }
                    break;
                case 7:
                    done = true; // exit the test
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static int getIntInput(Scanner sc) {
        int input = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                input = sc.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.next(); // consume the invalid input
            } catch (NoSuchElementException e) {
                System.out.println("Input not available. Please try again.");
                sc.nextLine(); // consume the invalid input
            } catch (NullPointerException e) {
                System.out.println("Null Key ");
            }
        }

        return input;
    }
}
