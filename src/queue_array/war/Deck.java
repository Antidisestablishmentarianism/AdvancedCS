package queue_array.war;

import java.util.Arrays;

/**
 * Created by Saif on 8/31/2017.
 */
public class Deck {

    private final String[] container;
    private final int capacity;

    private int head;
    private int tail;
    private int size;

    public Deck(int initSize) {
        if (initSize <= 0)
            throw new IllegalArgumentException("Size can not be less than or equal to zero");

        capacity = initSize;
        container = new String[initSize];
        head = -1;
        tail = -1;
        size = 0;
    }

    public int capacity() {
        return capacity;
    }

    public int size() { return size; }

    public boolean isEmpty() {
        return head == -1 && tail == -1;
    }

    public boolean isFull() {
        return (tail + 1) % capacity == head;
    }

    public String enqueue(String value) {
        if (isFull()) return null;

        if (isEmpty()) {
            head++;
            tail++;
            container[tail] = value;
        } else {
            tail = (tail + 1) % capacity;
            container[tail] = value;
        }

        size++;
        return value;
    }

    public String dequeue() {
        String out;

        if (isEmpty()) {
            return null;
        } else if (head == tail) {
            out = container[head];
            container[head] = null;
            head = -1;
            tail = -1;
        } else {
            out = container[head];
            container[head] = null;
            head = (head + 1) % capacity;
        }

        size--;
        return out;
    }

    public String peek() {
        if (isEmpty()) return null;

        return container[head];
    }

    public String elementAt(int index) {
        if (isEmpty()) return null;

        return container[(tail + index) % capacity];
    }

    public String toString() {
        return "[head = " + head + ", tail = " + tail + ", queue = " + Arrays.toString(container) + ", capacity = " + size() + "]";
    }

    public void clear() {
        Arrays.fill(container, null);
        head = -1;
        tail = -1;
    }
}
