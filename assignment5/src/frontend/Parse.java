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
		// this.scan = new ScanTest();
		// scan = scan;
		TLI = new TopLvlItem(new Tree(new Node(null)), new SymbolTable());
		pro = new Program();
		parenLvl = 0;
	}

	public Program buildTopLvl() {
		Token t = null;
		Node n = null;
		while (n == null || n.getType() != TokenType.EOF) {
			if (n != null) {
				// System.out.println("node=" + n.getValue());
			}
			// Find opening paren
			while (t == null || t.getType() != TokenType.OPEN_LIST) {
				t = scan.nextToken();

				if (t.getType() == TokenType.EOF) {
					return pro;
				}
			}
			if (n != null) {
				// System.out.println("+node=" + n.getValue());
			}
			n = new Node(null);
			n.setValue(t);
			TLI.getMainForm().setNode(n);

			parenLvl = 1;
			biCnt = 0;
			// while (parenLvl > 0) {
			// System.out.println("+++node=" + n.getValue() + " type="
			// + n.getType());

			n = buildItem(scan.nextToken(), TLI.getMainForm().getNode());
			TLI.getMainForm().setNode(n);
			// System.out.println("///////Node value:" + n.getValue() + " type="
			// + n.getType() + " parenLvl=" + parenLvl);
			// }
			// clean up the current TLI item...
			// (define items go into the symbol table
			// (lambda ?
			// (let* ?
			//
			// System.out.println("Clean up tli");
			// if (TLI.getMT().getNode().getLeft().getValue()) {

			// }
			pro.getProlist().add(TLI);
			TLI = new TopLvlItem(new Tree(new Node(null)), new SymbolTable());
			System.out.println("Another top level");
			// n = null;
		}
		// Clean up the structure here
		return pro;
	}

	Integer biCnt;

	private Node buildItem(Token t, Node n) {
		biCnt++;
		// System.out.println("BI: "+biCnt);
		if (notsafe(t, n)) {
			n.setValue(t);
			biCnt--;
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
		biCnt--;
		return n;

		//
		// System.out.println("Exit builditem");
	}

	private boolean notsafe(Token t, Node n) {
		if (t.getType() == TokenType.EOF) {
			// System.out.println("End Of File");
			return true;
		}
		// if(t.getType()== TokenType.COMMENT){ }
		if (n == null) {
			// System.out.println("Null node");
			return true;
		}

		if (t.getValue().isEmpty()) {
			// System.out.println("Empty token");
			return true;
		}
		return false;
	}

	private void assignment5code(Token t) {
		if (t.getType() == TokenType.SPACE) {
			// System.out.println("Token " + "Space");
		} else {
			// System.out.println("Token " + t.getValue());
		}
	}

	private Token removeC_S(Token t) {
		while (t.getType() == TokenType.COMMENT
				|| t.getType() == TokenType.SPACE) {
			t = scan.nextToken();
		}
		return t;
	}

	private void closeL(Token t, Node n) {
		// System.out.println("\tCL value:" + t.getValue() + " type:" +
		// t.getType());
		parenLvl--;
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
		// System.out.println("\tOT value:" + t.getValue() + " type:" +
		// t.getType());
		n.setValue(t);
		// fill symbol table here
		if (t.getType() == TokenType.SYMBOL) {
			pro.addSysmbol(t.getValue(), new ObjectValue(
					ObjectType.SCHEME_SYMBOL));
		}
		//
		n.setRight(new Node(n));
		buildItem(scan.nextToken(), n.getRight());
		// System.out.println("Right exit");
	}

	private void openL(Token t, Node n) {
		// System.out.println("Left entry");
		// System.out.println("\tOL value:" + t.getValue() + " type:" +
		// t.getType());
		n.setLeft(new Node(n));
		parenLvl++;
		buildItem(scan.nextToken(), n.getLeft());
		// System.out.println("Left exit");
	}
}
