/*
 * @Author Tim Stullich , Wesley Eversole
 * Assignment 5 
 * Project for CS 152
 */
package intermediate;

import frontend.Token;
import frontend.TokenType;

public class Node {
	private Token value;
	private ObjectValue ov; // scheme base objects
	private ObjectType ot; // scheme object type
	private Node left;
	private Node right;
	private Node parent;

	public Node(Node p) {
		value = null;
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
		if (value == null) {
			return "";
		} else {
			return value.getValue();
		}
	}

	public TokenType getType() {
		if (value==null) return TokenType.NULL;
		return value.getType();
	}

	public void setValue(Token name) {
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

	public ObjectValue getOv() {
		return ov;
	}

	public void setOv(ObjectValue ov) {
		this.ov = ov;
	}

	public ObjectType getOt() {
		return ot;
	}

	public void setOt(ObjectType ot) {
		this.ot = ot;
	}
}
