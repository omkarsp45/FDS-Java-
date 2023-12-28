import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

// concrete subclass for hash map using separate chaining
class ChainHashMap<K, V> extends AbstractHashMap<K, V> {
    // a fixed capacity array of UnsortedTableMap that serve as buckets
    private UnsortedTableMap<K, V>[] table; // initialized within createTable

    // provide same constructors as base class
    public ChainHashMap(int cap, int p) {
        super(cap, p);
    }

    public ChainHashMap(int cap) {
        super(cap);
    }

    public ChainHashMap() {
        super();
    }

    // create an empty table having length equal to current capacity
    protected void createTable() {
        table = (UnsortedTableMap<K, V>[]) new UnsortedTableMap[capacity];
    }

    // return value associated with key k in bucket with hash value h, or else null
    protected V bucketGet(int h, K k) {
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null)
            return null;
        return bucket.get(k);
    }

    // associate key k with value v in bucket with hash value h; return old value
    protected V bucketPut(int h, K k, V v) {
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null)
            bucket = table[h] = new UnsortedTableMap<>();
        int oldSize = bucket.size();
        V answer = bucket.put(k, v);
        n += (bucket.size() - oldSize); // size may have increased
        return answer;
    }

    // remove entry having key k from bucket with hash value h (if any)
    protected V bucketRemove(int h, K k) {
        UnsortedTableMap<K, V> bucket = table[h];
        if (bucket == null)
            return null;
        int oldSize = bucket.size();
        V answer = bucket.remove(k);
        n -= (oldSize - bucket.size()); // size may have decreased
        return answer;
    }

    // return an iterable collection of all key-value entries of the map
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        for (int h = 0; h < capacity; h++)
            if (table[h] != null)
                for (AbstractHashMap.Entry<K, V> entry : table[h].entrySet())
                    buffer.add((AbstractHashMap.Entry<K, V>) entry);
        return buffer;
    }

    public boolean isEmpty() {
        return false;
    }
}

public class SeparateChaining {
    // main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // scanner for user input
        ChainHashMap<String, Integer> map = new ChainHashMap<>(); // create a new ChainHashMap
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

    // method to get integer input with exception handling
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
