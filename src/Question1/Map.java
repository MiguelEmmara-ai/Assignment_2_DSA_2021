package Question1;

import java.util.Collection;
import java.util.Set;

/**
 * @author Miguel Emmara - 18022146
 */
public interface Map<K, V> {
    void clear();

    boolean equals(Object o);

    int hashCode();

    Set<K> keySet();

    boolean containsKey(K key);

    V put(K key, V value);

    V remove(Object key);

    V get(K key);

    int size();

    Collection<V> values();

}
