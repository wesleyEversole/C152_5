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

	public Parse(String s) {
		init(new CSScanner(s));

	}

	CSScanner scan;

	private void init(CSScanner scan) {
		this.scan = scan;
		// this.scan = new ScanTest();
		// scan = scan;
		TLI= new TopLvlItem(new Tree(new Node(null)), new SymbolTable());
		pro=new Program();
		parenLvl = 0;
	}

	public Program buildTopLvl() {
		Token t = null;
		while (t == null || t.getType() != TokenType.EOF) {
			while (t == null || t.getType() != TokenType.OPEN_LIST) {
				t = scan.nextToken();
			}
			parenLvl++;
			while (parenLvl > 0) {
				t= buildItem(scan.nextToken(), TLI.getMT().getNode());
			}
		}
		return pro;
	}

	private Token buildItem(Token t, Node n) {

		// System.out.println("buildItem");
		if (notsafe(t, n)) {
			return t;
		}
		assignment5code(t);
		removeC_S(t);
		// main code
		closeL(t, n);
		openL(t, n);
		return t;

		//
		// System.out.println("Exit builditem");
	}

	private boolean notsafe(Token t, Node n) {
		if (t.getType() == TokenType.EOF) {
			System.out.println("End Of File");
			return true;
		}
		// if(t.getType()== TokenType.COMMENT){ }
		if (n == null) {
			System.out.println("Null node");
			return true;
		}

		if (t.getValue().isEmpty()) {
			System.out.println("Empty token");
			return true;
		}
		return false;
	}

	private void assignment5code(Token t) {
		if (t.getType() == TokenType.SPACE) {
			System.out.println("Token " + "Space");
		} else {
			System.out.println("Token " + t.getValue());
		}
	}

	private void removeC_S(Token t) {
		while (t.getType() == TokenType.COMMENT
				|| t.getType() == TokenType.SPACE) {
			t = scan.nextToken();
		}
	}

	private void closeL(Token t, Node n) {
		if (t.getType() == TokenType.CLOSE_LIST) {
			// System.out.println("\t)");
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
	}

	private void openL(Token t, Node n) {
		if (t.getType() == TokenType.OPEN_LIST) {
			// System.out.println("Left entry");
			n.setLeft(new Node(n));
			buildItem(scan.nextToken(), n.getLeft());
			// System.out.println("Left exit");
		} else {
			// System.out.println("\tvalue");
			n.setValue(t.getValue());
			// fill symbol table here
			if (t.getType() == TokenType.SYMBOL) {
				pro.getMST().add(t.getValue(), new Symbol());
			}
			//
			n.setRight(new Node(n));
			buildItem(scan.nextToken(), n.getRight());
			// System.out.println("Right exit");
		}
	}

}
