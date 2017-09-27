package linked_list;

/**
 * Created by Saif on 9/27/2017.
 */
public class Node<T> {
    private T data;
    private Node<T> next;

    public Node() {
        data = null;
        next = null;
    }

    public Node(T data) {
        this.data = data;
        next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T value) {
        data = value;
    }

    public Node getNext() {
        return next;
    }

    public Node<T> setNext(Node<T> value) {
        next = value;
        return value;
    }

    public String toString() {
        return data.toString();
    }
}
