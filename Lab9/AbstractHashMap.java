import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;

// interface for map
interface Map<K, V> {
    // returns the number of key-value mappings in this map
    int size();

    // returns true if this map contains no key-value mappings
    boolean isEmpty();

    // returns true if this map contains a mapping for the specified key
    boolean containsKey(Object key);

    // returns true if this map maps one or more keys to the specified value
    boolean containsValue(Object value);

    // returns the value to which the specified key is mapped, or null if this map
    // contains no mapping for the key
    V get(Object key);

    // associates the specified value with the specified key in this map
    V put(K key, V value);

    // removes the mapping for a key from this map if it is present
    V remove(Object key);

    // copies all of the mappings from the specified map to this map
    void putAll(Map<? extends K, ? extends V> m);

    // removes all of the mappings from this map
    void clear();

    // returns a Set view of the keys contained in this map
    Set<K> keySet();

    // returns a Collection view of the values contained in this map
    Collection<V> values();

    // returns a Set view of the mappings contained in this map
    Set<Map.Entry<K, V>> entrySet();

    // compares the specified object with this map for equality
    boolean equals(Object o);

    // returns the hash code value for this map
    int hashCode();

    // nested interface for map entries
    public static interface Entry<K, V> {

        // returns the key corresponding to this entry
        K getKey();

        // returns the value corresponding to this entry
        V getValue();

        // replaces the value corresponding to this entry with the specified value
        V setValue(V value);

        // compares the specified object with this entry for equality
        boolean equals(Object o);

        // returns the hash code value for this map entry
        int hashCode();
    }
}

// abstract class for map
abstract class AbstractMap<K, V> implements Map<K, V> {

    // returns the number of key-value mappings in this map
    public abstract int size();

    // returns true if this map contains no key-value mappings
    public boolean isEmpty() {
        return size() == 0;
    }

    // returns true if this map contains a mapping for the specified key
    public abstract boolean containsKey(Object key);

    // returns true if this map maps one or more keys to the specified value
    public abstract boolean containsValue(Object value);

    // returns the value to which the specified key is mapped, or null if this map
    // contains no mapping for the key
    public abstract V get(Object key);

    // associates the specified value with the specified key in this map
    public abstract V put(K key, V value);

    // removes the mapping for a key from this map if it is present
    public abstract V remove(Object key);

    // copies all of the mappings from the specified map to this map
    public abstract void putAll(Map<? extends K, ? extends V> m);

    // removes all of the mappings from this map
    public abstract void clear();

    // returns a Set view of the keys contained in this map
    public abstract Set<K> keySet();

    // returns a Collection view of the values contained in this map
    public abstract Collection<V> values();

    // returns a Set view of the mappings contained in this map
    public abstract Set<Map.Entry<K, V>> entrySet();

    // compares the specified object with this map for equality
    public abstract boolean equals(Object o);

    // returns the hash code value for this map
    public abstract int hashCode();

    // nested class for map entries
    protected static class MapEntry<K, V> implements Map.Entry<K, V> {
        private K k; // key
        private V v; // value

        public MapEntry(K key, V value) {
            k = key;
            v = value;
        }

        // public methods of the Entry interface
        public K getKey() {
            return k;
        }

        public V getValue() {
            return v;
        }

        // utilities not exposed as part of the Entry interface
        protected void setKey(K key) {
            k = key;
        }

        public V setValue(V value) {
            V old = v;
            v = value;
            return old;
        }
    }
}

// class for unsorted map using an array list of entries
class UnsortedTableMap<K, V> extends AbstractHashMap<K, V> {

    // instance variable
    private ArrayList<MapEntry<K, V>> table = new ArrayList<>(); // underlying storage

    // constructor
    public UnsortedTableMap() {
    }

    // private utility
    // returns the index of an entry with equal key, or -1 if none found
    private int findIndex(K key) {
        int n = table.size();
        for (int j = 0; j < n; j++)
            // use Objects.equals to handle null keys
            if (Objects.equals(table.get(j).getKey(), key))
                return j;
        return -1; // special value denotes that key was not found
    }

    // public methods
    // returns the number of entries in the map
    @Override
    public int size() {
        return table.size();
    }

    // returns the value associated with the specified key, or null if no such entry
    // exists
    @Override
    public V get(K key) {
        int j = findIndex(key);
        if (j == -1)
            return null; // not found
        return table.get(j).getValue();
    }

    // associates the given value with the given key
    // if an entry with the key was already in the map, this replaced the previous
    // value with the new one and returns the old value
    // otherwise, a new entry is added and null is returned
    @Override
    public V put(K key, V value) {
        int j = findIndex(key);
        if (j == -1) {
            // use diamond operator to infer generic type arguments
            table.add(new MapEntry<>(key, value)); // add new entry
            return null;
        } else // key already exists
            return table.get(j).setValue(value); // replaced value is returned
    }

    // removes the entry with the specified key, if present, and returns its value
    // otherwise does nothing and returns null
    @Override
    public V remove(K key) {
        int j = findIndex(key);
        if (j == -1)
            return null; // nothing to remove
        V answer = table.get(j).getValue();
        if (j != table.size() - 1)
            table.set(j, table.get(table.size() - 1)); // relocate last entry to 'hole' created by removal
        table.remove(table.size() - 1); // remove last entry of table
        return answer;
    }

    // returns an iterable collection of all key-value entries of the map
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return new EntryIterable();
    }

    // support for public entrySet method
    private class EntryIterable implements Iterable<Entry<K, V>> {
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }
    }

    // support for public entrySet method
    private class EntryIterator implements Iterator<Entry<K, V>> {
        // use an iterator over the table instead of an index variable
        private Iterator<MapEntry<K, V>> iter = table.iterator();

        public boolean hasNext() {
            return iter.hasNext();
        }

        public Entry<K, V> next() {
            return iter.next();
        }

        public void remove() {
            throw new UnsupportedOperationException("remove not supported");
        }
    }

    // create an empty table having length equal to current capacity
    @Override
    protected void createTable() {
        // create a new array list with the given capacity
        table = new ArrayList<>(capacity);
    }

    // return value associated with key k in bucket with hash value h, or else null
    @Override
    protected V bucketGet(int h, K k) {
        // find the index of the entry with key k
        int j = findIndex(k);
        // if not found, return null
        if (j == -1)
            return null;
        // otherwise, return the value of the entry
        return table.get(j).getValue();
    }

    // associate key k with value v in bucket with hash value h; return old value
    @Override
    protected V bucketPut(int h, K k, V v) {
        // find the index of the entry with key k
        int j = findIndex(k);
        // if not found, add a new entry and return null
        if (j == -1) {
            table.add(new MapEntry<>(k, v));
            return null;
        }
        // otherwise, replace the value of the entry and return the old value
        return table.get(j).setValue(v);
    }

    // remove entry having key k from bucket with hash value h (if any)
    @Override
    protected V bucketRemove(int h, K k) {
        // find the index of the entry with key k
        int j = findIndex(k);
        // if not found, return null
        if (j == -1)
            return null;
        // otherwise, remove the entry and return its value
        return table.remove(j).getValue();
    }
}

// abstract class for hash map
abstract class AbstractHashMap<K, V> {

    // nested interface for map entries
    public static interface Entry<K, V> {

        // returns the key corresponding to this entry
        K getKey();

        // returns the value corresponding to this entry
        V getValue();

        // replaces the value corresponding to this entry with the specified value
        V setValue(V value);

        // compares the specified object with this entry for equality
        boolean equals(Object o);

        // returns the hash code value for this map entry
        int hashCode();
    }

    // instance variables
    protected int n; // number of entries in the hash table
    protected int capacity; // length of the table
    protected int prime; // prime factor
    protected long scale, shift; // the shift and scaling factors

    // constructors
    public AbstractHashMap(int cap, int p) {
        prime = p;
        capacity = cap;
        n = 0; // initially empty
        java.util.Random rand = new java.util.Random();
        scale = rand.nextInt(prime - 1) + 1;
        shift = rand.nextInt(prime);
        createTable();
    }

    public AbstractHashMap(int cap) {
        this(cap, 109345121); // default prime
    }

    public AbstractHashMap() {
        this(17); // default capacity
    }

    // public methods
    public int size() {
        return n;
    }

    public V get(K key) {
        return bucketGet(hashValue(key), key);
    }

    public V put(K key, V value) {
        V answer = bucketPut(hashValue(key), key, value);
        if (n > capacity / 2) // keep load factor <= 0.5
            resize(2 * capacity - 1); // (or find a nearby prime)
        return answer;
    }

    public V remove(K key) {
        return bucketRemove(hashValue(key), key);
    }

    public Iterable<K> keySet() {
        ArrayList<K> buffer = new ArrayList<>();
        for (Entry<K, V> e : entrySet())
            buffer.add(e.getKey());
        return buffer;
    }

    // abstract methods to be implemented by subclasses
    protected abstract void createTable();

    protected abstract V bucketGet(int h, K k);

    protected abstract V bucketPut(int h, K k, V v);

    protected abstract V bucketRemove(int h, K k);

    protected abstract Iterable<Entry<K, V>> entrySet();

    // hash function applying MAD method to default hash code
    protected int hashValue(K key) {
        return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
    }

    // resizes the hash table with a new capacity
    protected void resize(int newCap) {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>(n);
        for (Entry<K, V> e : entrySet())
            buffer.add(e);
        capacity = newCap;
        createTable(); // based on updated capacity
        n = 0; // will be recomputed while reinserting entries
        for (Entry<K, V> e : buffer)
            put(e.getKey(), e.getValue());
    }

    // nested class for hash entries
    protected static class MapEntry<K, V> implements Entry<K, V> {
        private K k; // key
        private V v; // value

        public MapEntry(K key, V value) {
            k = key;
            v = value;
        }

        // public methods of the Entry interface
        public K getKey() {
            return k;
        }

        public V getValue() {
            return v;
        }

        // utilities not exposed as part of the Entry interface
        protected void setKey(K key) {
            k = key;
        }

        public V setValue(V value) {
            V old = v;
            v = value;
            return old;
        }
    }
}
