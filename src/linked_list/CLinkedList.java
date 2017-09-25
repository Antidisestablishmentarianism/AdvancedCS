package linked_list;

import java.util.NoSuchElementException;

/**
 * Created by Saif on 9/25/2017.
 */
public class CLinkedList<Item> {

    private Node<Item> head, tail;

    public CLinkedList() {
        head = null;
        tail = null;
    }

    public void add(Node value) {
        if (head == null) {
            head = tail.setPrev(value);
            head.setNext(tail);
        } else {
            tail.setPrev(tail);
            tail = tail.setNext(value);
            tail.setNext(null);
        }
    }

    public Node<Item> remove() {
        if (head == null) throw new NoSuchElementException("No element ro remove");
        // TODO: Implement
        return null;
    }
}
