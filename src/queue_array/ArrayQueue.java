package queue_array;

import java.util.Arrays;

/**
 * Created by Saif on 8/31/2017.
 */
public class ArrayQueue<Item> implements IQueue<Item> {

    private final Item[] container;
    private final int size;

    private int head;
    private int tail;

    public ArrayQueue(int initSize) {
        if (initSize <= 0)
            throw new IllegalArgumentException("Size can not be less than or equal to zero");

        size = initSize;
        container = (Item[]) new Object[size];
        head = -1;
        tail = -1;
    }

    public int maxSize() {
        return size;
    }

    public int size() {
        int count = 0;

        for (Item element : container) {
            if (element != null)
                count++;
        }

        return count;
    }

    @Override
    public boolean isEmpty() {
        return head == -1 && tail == -1;
    }

    @Override
    public boolean isFull() {
        return (tail + 1) % size == head;
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
            tail = (tail + 1) % size;
            container[tail] = value;
        }
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
            head = (head + 1) % size;
        }

        return out;
    }

    public Item peek() {
        if (isEmpty())
            throw new IllegalStateException("Can not peek an empty queue");

        return container[head];
    }

    public String toString() {
        return "[head = " + head + ", tail = " + tail + ", queue = " + Arrays.toString(container) + ", size = " + size() + "]";
    }

    public void clear() {
        Arrays.fill(container, null);
        head = -1;
        tail = -1;
    }
}
