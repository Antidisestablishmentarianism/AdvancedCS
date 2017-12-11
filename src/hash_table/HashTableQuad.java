package hash_table;

/**
 * Created by Saif on 12/1/2017.
 */
public class HashTableQuad<k, v> {
    private static final int maxIterations = 200;

    private Node<k, v>[] container;
    private int size;

    public HashTableQuad(int initCap) {
        container = new Node[initCap];
        size = 0;

        for (int i = 0; i < container.length; i++)
            container[i] = new Node<>();
    }

    public HashTableQuad() {
        this(101);
    }

    public v put(k key, v value) {
        int index = hashFunction(key);
        int count = (index + 1) % container.length;
        int skip = 2;
        int iterations = 0;

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

                if (++iterations > maxIterations) return null;

                HashTest.probes++;
                count = Math.abs((count + skip)) % container.length;
                skip *= 2;
            }
        }

        return null;
    }

    public v remove(k key) {
        int index = hashFunction(key);
        int count = (index + 1) % container.length;
        int skip = 2;
        int iterations = 0;

        if (key == null) return null;

        if (container[index].getKey() == key && !container[index].removed()) {
            return container[index].getValue();
        } else {
            while (count != index) {

                if (container[count] == key)
                    return container[count].remove();

                if (container[count].removed())
                    return null;

                if (++iterations > maxIterations) return null;

                HashTest.probes++;
                count = Math.abs((count + skip)) % container.length;
                skip *= 2;
            }
        }

        return null;
    }

    public v get(k key) {
        int index = hashFunction(key);
        int count = (index + 1) % container.length;
        int skip = 2;
        int iterations = 0;

        if (key == null) return null;

        if (container[index].getKey() == key && !container[index].removed()) {
            return container[index].getValue();
        } else {
            while (count != index) {

                if (container[count] == key)
                    return container[count].getValue();

                if (container[count].removed())
                    return null;

                if (++iterations > maxIterations) return null;

                HashTest.probes++;
                count = Math.abs((count + skip)) % container.length;
                skip *= 2;
            }
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

    public String toString() {
        String out = "[";

        for (int i = 0; i < container.length; i++) {
            Node<k, v> node = container[i];

            if (node.removed()) {
                out += "[removed]" + (i == container.length - 1 ? "" : ", ");
                continue;
            } else {
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
