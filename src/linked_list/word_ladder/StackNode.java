import java.util.*;

public class StackNode {
	StackList data;
	StackNode next, prev;

	public StackNode() {
		this(null);
	}

	public StackNode(StackList data) {
		this.data = data;

		next = null;
		prev = null;
	}

	public StackList getData() {
		return data;
	}

	public void setData(StackList value) {
		data = value;
	}

	public StackNode getNext() {
		return next;
	}

	public void setNext(StackNode value) {
		next = value;
	}

	public StackNode getPrev() {
		return prev;
	}

	public void setPrev(StackNode value) {
		prev = value;
	}
}
