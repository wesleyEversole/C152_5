/*
 * @Author Tim Stullich , Wesley Eversole
 * Assignment 5 
 * Project for CS 152
 */
package frontend;

import intermediate.*;

public class Parse {
	// private CSScanner scan;
	private Program pro;
	private TopLvlItem TLI;
	private int parenLvl;

	public Parse(String fileName) {
		init(new CSScanner(fileName));

	}

	CSScanner scan;

	private void init(CSScanner scan) {
		this.scan = scan;
		TLI = new TopLvlItem(new Tree(new Node(null)), new SymbolTable());
		pro = new Program();
		parenLvl = 0;
	}

	public Program buildTopLvl() {
		Token t = null;
		Node n = null;
		while (true) {
			parenLvl = 0;

			n = buildItem(scan.nextToken(), new Node(null)); 
			TLI.getMainForm().setNode(n);

			pro.getProlist().add(TLI);
			TLI = new TopLvlItem(new Tree(new Node(null)), new SymbolTable());
			if (n.getType()==TokenType.EOF) {
				return pro;
			}
		}
	}

	private Node buildItem(Token t, Node n) {
		if (notsafe(t, n)) {
			n.setValue(t);
			return n;
		}
		// assignment5code(t);
		t = removeC_S(t); // remove all comment text
		switch (t.getType()) {
		case OPEN_LIST:
			openL(t, n);
			break;
		case CLOSE_LIST:
			closeL(t, n);
			break;
		default:
			other(t, n);
		}
		// main code
		return n;
	}

	private boolean notsafe(Token t, Node n) {
		if (t.getType() == TokenType.EOF) {
			return true;
		}
		if (n == null) {
			return true;
		}

		if (t.getValue().isEmpty()) {
			return true;
		}
		return false;
	}

	private Token removeC_S(Token t) {
		while (t.getType() == TokenType.COMMENT
				|| t.getType() == TokenType.SPACE) {
			System.out.println("COMMENT");
			t = scan.nextToken();
		}
		return t;
	}

	private void closeL(Token t, Node n) {
		parenLvl--;
		n.setValue(t);
		if (parenLvl ==0) {
			return;
		}
		while (n.getParent() != null) {
			n = n.getParent();
			if (n.getRight() == null) {
				n.setRight(new Node(n));
				buildItem(scan.nextToken(), n.getRight());
				break;
			}
		}
		return;
	}

	private void other(Token t, Node n) {
		n.setValue(t);
		if(n==n.getParent().getLeft()) {
			// we are a left node
			n = n.getParent(); // return to our parent
		}
		
		// fill symbol table here
		if (t.getType() == TokenType.SYMBOL) {
			pro.addSymbol(t.getValue(), new ObjectValue(ObjectType.SCHEME_SYMBOL,
					ObjectType.SCHEME_SYMBOL));
		}
		//
		n.setRight(new Node(n));
		n.getRight().setLeft(new Node(n.getRight()));
		buildItem(scan.nextToken(), n.getRight().getLeft());
	}

	private void openL(Token t, Node n) {
		//System.out.println("\tOL value:" + t.getValue() + " type:" +t.getType());
		n.setLeft(new Node(n));
		parenLvl++;
		n.setValue(t);
		buildItem(scan.nextToken(), n.getLeft());
	}
}
