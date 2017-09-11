package queue_array;

import java.util.Arrays;

/**
 * Created by Saif on 8/31/2017.
 */
public class ArrayQueue<Item> implements IQueue<Item> {

    private final Item[] container;
    private final int capacity;

    private int head;
    private int tail;
    private int size;

    public ArrayQueue(int initSize) {
        if (initSize <= 0)
            throw new IllegalArgumentException("Size can not be less than or equal to zero");

        capacity = initSize;
        container = (Item[]) new Object[capacity];
        head = -1;
        tail = -1;
        size = 0;
    }

    public int capacity() {
        return capacity;
    }

    public int size() { return size; }

    @Override
    public boolean isEmpty() {
        return head == -1 && tail == -1;
    }

    @Override
    public boolean isFull() {
        return (tail + 1) % capacity == head;
    }

    @Override
    public void enqueue(Item value) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        } else if (isEmpty()) {
            head++;
            tail++;
            container[tail] = value;
        } else {
            tail = (tail + 1) % capacity;
            container[tail] = value;
        }

        size++;
    }

    public void enqueue(Item[] values) {
        if (size - size() > values.length)
            for (int i = 0; i < values.length; i++)
                enqueue(values[i]);
        else
            throw new IllegalStateException("Not enough room in queue");
    }

    @Override
    public Item dequeue() {
        Item out;

        if (isEmpty()) {
            throw new IllegalStateException("Can not dequeue an empty queue");
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

    public Item peek() {
        if (isEmpty())
            throw new IllegalStateException("Can not peek an empty queue");

        return container[head];
    }

    public Item elementAt(int index) {
        if (isEmpty())
            throw new IllegalStateException("Can not peek an empty queue");

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
