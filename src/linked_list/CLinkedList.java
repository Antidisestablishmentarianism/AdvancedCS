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

    public CLinkedList(T[] data) {
        for (int i = 0; i < data.length; i++)
            add(data[i]);
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

    public boolean contains(T value) {
        Node curr = head;

        for (int i = 0; i < size(); i++) {
            if (curr == value) return true;

            curr = curr.getNext();
        }

        if (curr == value) return true;

        return false;
    }

    public boolean containsString(String value) {
        Node curr = head;

        for (int i = 0; i < size(); i++) {
            if (curr.getData().toString().equals(value))
                return true;

            curr = curr.getNext();
        }

        return false;
    }

    public int size() {
        return count;
    }

    public String toString() {
        String out = "[";

        Node<T> curr;

        if (head != null)
            curr = head;
        else
            return "[]";

        out += curr.getData();

        if (count == 1) return out + "]";

        out += ", ";

        for (int i = 0; i < size() - 1; i++) {
            curr = curr.getNext();
            out += curr.getData() + (i < size() - 2 ? ", " : "]");
        }

        return out;
    }
}
