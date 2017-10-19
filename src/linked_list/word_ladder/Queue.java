import java.util.*;

public class Queue {
	private StackNode head, tail;
	private int count;

	public StackList() {
		head = null;
		tail = null;
		count = 0;
	}

	public void add(StackNode node) {
		if (count == 0) {
			head = node;
			head.setNext(tail);
			count++;
			return;
		}

		tail.setPrev(node);
		tail = node;
		count++;
	}

	public void add(StackList data) {
		add(new StackNode(data));
	}

	public StackNode remove() {
		StackNode temp = head;
		head = head.getNext();
		count--;
		return temp;
	}

	public StackNode get(int index) {
		StackNode curr = head;

		for (int i = 0; i < index; i++)
			curr = curr.getNext();

		return curr;
	}
}
