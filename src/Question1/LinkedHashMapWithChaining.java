package Question1;

import java.util.*;

/**
 * @author Miguel Emmara - 18022146
 */
public class LinkedHashMapWithChaining<K, V> implements Map<K, V> {

    public static final int INITIAL_CAPACITY = 16;

    /*
     * The max load factor of the LinkedHashMapWithChaining.
     */
    public static final double MAX_LOAD_FACTOR = 0.75;

    private ChainingNode<K, V>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public LinkedHashMapWithChaining() {
        this.table = (ChainingNode<K, V>[]) new ChainingNode[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     *
     * @param  initialCapacity the initial capacity
     */
    @SuppressWarnings("unchecked")
    public LinkedHashMapWithChaining(int initialCapacity) {
        this.table = (ChainingNode<K, V>[]) new ChainingNode[initialCapacity];
        size = 0;

    }

    /**
     * @param key   the key to add
     * @param value the value to add
     * @throws java.lang.IllegalArgumentException if the specified key or value is null
     *      *         and this map does not permit null keys or values
     */
    public V put(K key, V value) {

        if (key == null || value == null) {
            throw new IllegalArgumentException("Does not permit null keys or values!");
        }

        if (((size + 1.0) / table.length > MAX_LOAD_FACTOR)) {
            resizeBackingTable((2 * table.length) + 1);
        }

        int hash = Math.abs(key.hashCode() % table.length);
        ChainingNode<K, V> temp = table[hash];

        if (temp == null) {
            table[hash] = new ChainingNode<>(key, value);
            size++;
            return null;
        }
        while (temp != null) {
            if (temp.getKey().equals(key)) {
                V oldValue = temp.getValue();
                temp.setValue(value);
                return oldValue;
            }
            temp = temp.getNext();
        }

        ChainingNode<K, V> add = new ChainingNode<>(key, value);
        add.setNext(table[hash]);
        table[hash] = add;
        size++;
        return null;
    }

    /**
     * @param key the key to remove
     * @return the value associated with the key
     * @throws java.lang.IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException   if the specified key is null and this
     *                                            map does not permit null keys
     */
    public V remove(Object key) {
        if (key == null) {
            throw new IllegalArgumentException("Table array is null. nothing to remove");
        }
        int hash = Math.abs(key.hashCode() % table.length);
        ChainingNode<K, V> curr = table[hash];

        if (curr == null) {
            throw new NoSuchElementException("The table array is null");
        }

        if (curr.getKey().equals(key)) {
            V remove = curr.getValue();
            table[hash] = curr.getNext();
            size--;
            return remove;
        } else {
            while (curr.getNext() != null) {
                if (curr.getNext().getKey().equals(key)) {
                    V remove = curr.getNext().getValue();
                    curr.setNext(curr.getNext().getNext());
                    size--;
                    return remove;
                }
                curr = curr.getNext();
            }
        }
        throw new NoSuchElementException("key whose mapping is to be removed from the map not found in the array");
    }

    /**
     * @param key the key to search for in the map
     * @return the value associated with the given key
     * @throws java.lang.IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException   if the key is not in the map
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Does not permit null keys or values!");
        }
        int hash = Math.abs(key.hashCode() % table.length);
        ChainingNode<K, V> curr = table[hash];

        if (curr == null) {
            throw new NoSuchElementException("Table array is null. nothing to return");
        }

        while (curr != null) {
            if (curr.getKey().equals(key)) {
                return curr.getValue();
            }
            curr = curr.getNext();
        }

        throw new NoSuchElementException("Key to be found is not in the map");
    }

    /**
     * @param key the key to search for in the map
     * @return true if the key is contained within the map, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if key is null
     */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Does not permit null keys or values!");
        }
        int hash = Math.abs(key.hashCode() % table.length);
        ChainingNode<K, V> curr = table[hash];

        if (curr == null) {
            return false;
        }
        while (curr != null) {
            if (curr.getKey().equals(key)) {
                return true;
            }
            curr = curr.getNext();
        }
        return false;
    }

    /**
     * @return the set of keys in this map
     */
    public Set<K> keySet() {
        HashSet<K> set = new HashSet<>();

        int counter = 0;
        for (ChainingNode<K, V> kvChainingNode : table) {
            ChainingNode<K, V> curr = kvChainingNode;

            while (curr != null && counter != size) {
                set.add(curr.getKey());
                curr = curr.getNext();
                counter++;
            }

            if (counter == size) {
                break;
            }
        }
        return set;
    }

    /**
     * @return list of values in this map
     */
    public List<V> values() {
        List<V> list = new LinkedList<>();

        int counter = 0;

        for (ChainingNode<K, V> kvChainingNode : table) {
            ChainingNode<K, V> curr = kvChainingNode;

            while (curr != null && counter != size) {
                list.add(curr.getValue());
                curr = curr.getNext();
                counter++;
            }

            if (counter == size) {
                break;
            }

        }
        return list;
    }

    /**
     * @param length new length of the backing table
     * @throws java.lang.IllegalArgumentException if length is less than the
     *                                            number of items in the hash
     *                                            map
     */
    @SuppressWarnings("unchecked")
    public void resizeBackingTable(int length) {

        if (length < size) {
            throw new IllegalArgumentException("length is less than the number of items in the hash table");
        }

        ChainingNode<K, V>[] tempArray;
        tempArray = (ChainingNode<K, V>[]) new ChainingNode[length];

        for (ChainingNode<K, V> kvChainingNode : table) {
            ChainingNode<K, V> curr = kvChainingNode;
            int hash;
            while (curr != null) {
                hash = Math.abs(curr.getKey().hashCode() % length);
                ChainingNode<K, V> add;
                add = new ChainingNode<>(curr.getKey(), curr.getValue());
                add.setNext(tempArray[hash]);
                tempArray[hash] = add;
                curr = curr.getNext();

            }

        }
        table = tempArray;

    }

    /**
     * Clears the map.
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        ChainingNode<K, V>[] temp;
        temp = (ChainingNode<K, V>[]) new ChainingNode[INITIAL_CAPACITY];
        table = temp;
        size = 0;
    }

    /**
     * @return the table of the map
     */
    public ChainingNode<K, V>[] getTable() {
        return table;
    }

    /**
     * @return the size of the map
     */
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return Arrays.toString(table);
    }
}
