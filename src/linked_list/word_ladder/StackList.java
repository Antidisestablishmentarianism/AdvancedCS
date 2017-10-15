import java.util.*;

public class StackList {
	private WordNode head;
	private int count;

	public StackList() {
		head = null;
		count = 0;
	}

	public void add(String data) {
		add(new WordNode(data));
	}

	public void add(WordNode node) {
		if (count == 0) {
			head = node;
			head.setNext(null);
			count++;
			return;
		}

		WordNode temp = head;
		head = node;
		head.setNext(temp);
		count++;
	}

	public WordNode remove() {
		WordNode temp = head;
		head = head.getNext();
		count--;
		return temp;
	}

	public boolean contains(String value) {
		if (count == 0) return value == null;
		if (head.getData().equals(value)) return true;

		for (int i = 0; i < count; i++) {
			curr = curr.getNext();
			if (curr.getData().equals(value)) return true;
		}

		return false;
	}

	public boolean contains(WordNode value) {
		if (count == 0) return value == null;
		if (head == value) return true;

		for (int i = 0; i < count; i++) {
			curr = curr.getNext();
			if (curr == value) return true;
		}

		return false;
	}

	public WordNode get(int index) {
		WordNode curr = head;

		for (int i = 0; i < count; i++)
			curr = curr.getNext();

		return curr;
	}
}
