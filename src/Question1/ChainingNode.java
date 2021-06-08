package Question1;

/**
 * @author Miguel Emmara - 18022146
 */
public class ChainingNode<K, V> {
    private K key;
    private V value;
    private ChainingNode<K, V> next;

    /**
     * @param <K> the type of keys maintained by this map
     * @param <V> the type of mapped values
     */
    ChainingNode(K key, V value) {
        this(key, value, null);
    }

    /**
     * @param <K> the type of keys maintained by this map
     * @param value the value in the new entry
     * @param next  the next entry in the chain
     */
    ChainingNode(K key, V value,
                 ChainingNode<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    /**
     * Gets the key.
     *
     * @return the key
     */
    K getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param <K> the type of keys maintained by this map
     */
    void setKey(K key) {
        this.key = key;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    V getValue() {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value new value to be stored in this entry
     */
    void setValue(V value) {
        this.value = value;
    }

    /**
     * Gets the next entry.
     *
     * @return the next entry
     */
    public ChainingNode<K, V> getNext() {
        return next;
    }

    /**
     * Sets the next entry.
     *
     * @param next the new next entry
     */
    void setNext(ChainingNode<K, V> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        String key = this.key == null ? "null" : this.key.toString();
        String value = this.value == null ? "null" : this.value.toString();
        return String.format("(%s, %s)", key, value);
    }

    /*@Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (!(o instanceof ChainingNode)) {
            return false;
        } else {
            ChainingNode<K, V> that =
                    (ChainingNode<K, V>) o;
            return that.getKey().equals(key) && that.getValue().equals(value);
        }
    }*/
}