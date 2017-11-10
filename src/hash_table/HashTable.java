package hash_table;

/**
 * Created by Saif on 11/2/2017.
 */
public class HashTable<k, v> {
    private Node<k, v>[] container;
    int size;

    public HashTable(int initCap) {
        container = new Node[initCap];
        size = 0;

        for (int i = 0; i < container.length; i++)
            container[i] = new Node<>();
    }

    public HashTable() {
        this(101);
    }

    public v put(k key, v value) {
        int index = getIndex(key);
        int count = (index + 1) % container.length;

        if (size >= container.length) return null;

        if (container[index].equals(key, container[index].removed()))
            return container[index].set(key, value);

        if (container[index].removed()) {
            size++;
            return container[index].set(key, value);
        } else {
            while (count != index) {
                if (container[count].removed()) {
                    size++;
                    return container[count].set(key, value);
                } else if (container[count].equals(key, container[count].removed())) {
                    return container[count].set(key, value);
                }

                count = (count + 1) % container.length;
            }
        }

        return null;
    }

    public v remove(k key) {
        return container[getIndex(key)].remove();
    }

    public v get(k key) {
        int index = getIndex(key);
        int count = (index + 1) % container.length;

        if (key == null) return null;

        if (container[index].getKey() == key && !container[index].removed()) {
            return container[index].getValue();
        } else {
            while (count != index) {
                if (container[count] == key)
                    return container[count].getValue();

                if (!container[count].removed())
                    return null;

                count = (count + 1) % container.length;
            }
        }

        return null;
    }

    private int getIndex(k key) {
        return key.hashCode() % container.length;
    }

    public String toString() {
        String out = "[";

        for (int i = 0; i < container.length; i++) {
            Node<k, v> node = container[i];

            if (node.removed()) {
                out += "[removed]" + (i == container.length - 1 ? "" : ", ");
                continue;
            }

            if (node != null) {
                out += "[" + i + ", " + container[i].getKey() + ", " + container[i].getValue() + "]";

                if (i != container.length - 1)
                    out += ", ";
            }
        }

        if (out.substring(out.length() - 2).equals(", "))
            out = out.substring(0, out.length() - 2);

        return out + "]";
    }
}
