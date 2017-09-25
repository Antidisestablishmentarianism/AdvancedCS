package linked_list;

/**
 * Created by Saif on 9/25/2017.
 */
public class Node<Item> {
    private Node<Item> next, prev;

    public Node(Node next, Node prev) {
        this.next = next;
        this.prev = prev;
    }

    public Node next() {
        return next;
    }

    public Node prev() {
        return prev;
    }

    public Node<Item> setNext(Node value) {
        next = value;
        return next;
    }

    public Node<Item> setPrev(Node value) {
        prev = value;
        return prev;
    }
}
