package hash_table;

/**
 * Created by Saif on 11/2/2017.
 */
public class Node<k, v> {
    private k key;
    private v value;
    private boolean removed;

    public Node(k key, v value, boolean removed) {
        this.key = key;
        this.value = value;
        this.removed = removed;
    }

    public Node(k key, v value) {
        this(key, value, true);
    }

    public Node() {
        this(null, null);
    }

    public k getKey() {
        return removed ? null : key;
    }

    public v getValue() {
        return removed ? null : value;
    }

    public v set(k key, v value) {
        v prev = getValue();
        this.key = key;
        this.value = value;
        removed = false;
        return prev;
    }

    public boolean equals(Node node) {
        return this.removed == node.removed && this.getKey() == node.getKey();
    }

    public boolean equals(k key, boolean removed) {
        return this.key == key && this.removed == removed;
    }

    public v remove() {
        removed = true;
        return value;
    }

    public boolean removed() {
        return removed;
    }
}
