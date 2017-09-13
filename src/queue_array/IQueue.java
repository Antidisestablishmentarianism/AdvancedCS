package queue_array;

/**
 * Created by 180502 on 8/31/2017.
 */
public interface IQueue<Item> {

    boolean isEmpty();

    boolean isFull();

    Item enqueue(Item value);

    Item dequeue();

    void clear();
}
