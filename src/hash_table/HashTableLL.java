package hash_table;

import java.util.LinkedList;

/**
 * Created by Saif on 1/4/2018.
 */
public class HashTableLL<k, v> {
    LinkedList<Node<k, v>>[] container;
    int size;

    public HashTableLL(int initCap) {
        container = new LinkedList[initCap];
        size = 0;

        for (int i = 0; i < container.length; i++)
            container[i] = new LinkedList<>();
    }

    public HashTableLL() {
        this(101);
    }

    public v put(k key, v value) {
        int index = hashFunction(key);

        if (size >= container.length) return null;

        for (int count = 0; count < container[index].size(); count++) {
            if (container[index].get(count).equals(key, container[index].get(count).removed())) {
                container[index].set(count, new Node<>(key, value));
                return container[index].get(count).getValue();
            }
            HashTest.probes++;
            count++;
        }

        container[index].add(new Node<>(key, value));
        size++;
        return container[index].getLast().getValue();
    }

    public v remove(k key) {
        int index = hashFunction(key);

        if (key == null) return null;

        if (container[index].size() == 0) return null;

        for (int count = 0; count < container[index].size(); count++) {
            if (container[index].get(count).equals(key, container[index].get(count).removed())) {
                v val = container[index].get(count).getValue();
                container[index].remove(count);
                size--;
                return val;
            }
            HashTest.probes++;
        }

        return null;
    }

    public v get(k key) {
        int index = hashFunction(key);

        if (key == null) return null;

        if (container[index].size() == 0) return null;

        for (int count = 0; count < container[index].size(); count++) {
            if (container[index].get(count).equals(key, container[index].get(count).removed()))
                return container[index].get(count).getValue();

            HashTest.probes++;
        }

        return null;
    }

    public int capacity() {
        return container.length;
    }

    public int size() {
        return size;
    }

    private int hashFunction(k key) {
        return Math.abs(key.hashCode()) % container.length;
    }
}
