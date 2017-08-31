package queue_array;

/**
 * Created by 180502 on 8/31/2017.
 */
public interface IQueue<Item> {

    /**
     * Inserts the specified element into this queue if it is possible
     * to do so immediately without violating capacity restrictions, returning
     * true upon success and throwing an IllegalStateException if no space is currently available.
     * @param value
     */
    void enqueue(Item value);

    /**
     * Inserts the specified element into this queue if it is possible
     * to do so immediately without violating capacity restrictions.
     * @param value
     * @return
     */
    boolean offer(Item value);

    /**
     * Retrieves and removes the head of this queue.
     * @return
     */
    Item dequeue();

    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty.
     * @return
     */
    Item poll();

    /**
     * Retrieves, but does not remove, the head of this queue.
     * @return
     */
    Item element();

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
     * @param index
     * @return
     */
    Item peek(int index);
}
