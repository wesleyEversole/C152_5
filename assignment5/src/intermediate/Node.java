package intermediate;

public class Node {
	private String value;
	private Node left;
	private Node right;
	private Node parent;

	public Node(Node p) {
		value = "";
		left = null;
		right = null;
		parent = p;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String name) {
		value = name;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}
}
