package queue_array;

/**
 * Created by Saif on 8/31/17.
 */
public class QueueTester {
    public static void main(String[] args) {

        // Test: Create queue
        ArrayQueue<Double> queue = new ArrayQueue<>(5);

        // Test: push to queue
        queue.enqueue(1.1);
        queue.enqueue(2.2);
        queue.enqueue(3.3);
        queue.enqueue(4.4);
        queue.dequeue();
        queue.enqueue(5.5);
        queue.enqueue(6.6);
        System.out.println("\nPushed 3 items to queue.");
        // Prints memory refernce w/o toString override
        System.out.println("Stack contents: " + queue);

        // Test: pop queue
        double d = queue.dequeue();
        System.out.println("\nPopped from top of queue: " + d);
        System.out.println("Queue contents: " + queue);

        // Test: clear queue
        queue.clear();
        System.out.println("\nQueue was cleared.");
        System.out.println("Queue contents: " + queue);

        // Test: peek/pop to queue
        queue.enqueue(1.1);
        queue.enqueue(2.2);
        queue.enqueue(3.3);
        System.out.println("\nPushed 3 items to stack.");
        System.out.println("Stack contents: " + queue);
        System.out.println("Peeked from top of stack: " + queue.peek());
        double d1 = queue.dequeue();
        double d2 = queue.dequeue();
        System.out.println("Popped from top of queue: " + d1);
        System.out.println("Popped again from top of queue: " + d2);
        System.out.println("\nPeeked from top of queue: " + queue.peek());
        System.out.println("Queue contents: " + queue);
    }
}
