package linked_list.word_ladder;

/**
 * Created by Saif on 10/11/2017.
 */
public class WordNode {
    private String data;

	private WordNode next, prev;

	public WordNode() {
		this(null);
	}

	public WordNode(String data) {
		this.data = data;

		next = null;
		prev = null;
	}

	public String getData() {
		return data;
	}

	public void setData(String value) {
		data = value;
	}

	public WordNode getNext() {
		return next;
	}

	public void setNext(WordNode value) {
		next = value;
	}

	public WordNode getPrev() {
		return prev;
	}

	public void setPrev(WordNode value) {
		prev = value;
	}
}
