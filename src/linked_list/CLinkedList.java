package linked_list;

/**
 * Created by Saif on 9/27/2017.
 */
public class CLinkedList<T> {
    private Node<T> head;
    private int count;

    public CLinkedList() {
        head = null;
        count = 0;
    }

    public void add(T data) {
        add(new Node<>(data));
    }

    public void add(Node<T> value) {
        if (head == null) {
            head = value;
        } else {
            value.setNext(head);
            head = value;
        }

        count++;
    }

    public Node get(int index) {
        Node curr = head;

        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
            if (curr == null) break;
        }

        return curr;
    }

    public int size() {
        return count;
    }

    public String toString() {
        String out = "[";

        Node<T> curr = head;
        out += curr.getData() + ", ";

        for (int i = 0; i < size() - 1; i++) {
            curr = curr.getNext();
            out += curr.getData() + (i < size() - 2 ? ", " : "]");
        }

        return out;
    }
}
