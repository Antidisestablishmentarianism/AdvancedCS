package queue_array;

/**
 * Created by 180502 on 8/31/2017.
 */
public class ArrayQueue<Item> implements IQueue<Item> {

    private Item[] container;
    private int head;
    private int tail;
    private int size;

    public ArrayQueue(int initSize) {
        container = (Item[]) new Object[initSize];
        head = 0;
        tail = 0;
        size = 0;
    }

    @Override
    public void enqueue(Item value) {
        if (size == container.length) throw new IllegalStateException("Tried to add " + value + " when " + this + " was full");

        if (--tail == -1) tail = container.length - 1;
        size++;

        container[tail] = value;
    }

    @Override
    public boolean offer(Item value) {
        try {
            enqueue(value);
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    @Override
    public Item dequeue() {
        if (size == 0) throw new ArrayIndexOutOfBoundsException("No element to dequeue in " + this);

        Item temp = container[tail];
        //if (++tail >= container.length)
        return null;
    }

    @Override
    public Item poll() {
        return null;
    }

    @Override
    public Item element() {
        return null;
    }

    @Override
    public Item peek(int index) {
        return null;
    }
}
