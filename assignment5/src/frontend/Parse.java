package frontend;

import intermediate.*;

public class Parse {
	private CSScanner scan;
	private TopLvlItem pro;

	public Parse(String s) {
		init(new CSScanner(s));

	}

	private Parse(CSScanner scan) {
		init(scan);
	}

	// builds the tree and symbol table
	// make a tree map first.
	// once the tree map is made make the stack the treemap will sit on
	// logic for the token type will be need... need to find out all the meaning
	// for the token types.
	// from the stack the tree can be made from the map.

	private void init(CSScanner scan) {
		this.scan = scan;
		Token tkn = new Token(null);
		pro = new TopLvlItem(new Tree(new Node(null)), new SymbolTable(new Symbol(
				"")));

	}

	public TopLvlItem buildTopLvl() {
		buildItem(scan.nextToken(), pro.getMT().getNode());
		return pro;
	}

	private void buildItem(Token t, Node n) {
		//System.out.println("buildItem");
		if (n == null) {
			System.out.println("Null node");
			return;
		}
		if (t == null) {
			System.out.println("Null token");
			return;
		}
		System.out.println("Token " + t.getValue());

		// main code
		if (t.getValue().equals(")")) {
			System.out.println("\t)");
			while ( n.getParent() != null) {
				n = n.getParent();
				if (n.getRight()==null) {
					n.setRight(new Node(n));
					buildItem(scan.nextToken(), n.getRight());
					break;
				}
			}
			return;
		} 

		if (t.getValue().equals("(")) {
			System.out.println("Left entry");
			n.setLeft(new Node(n));
			buildItem(scan.nextToken(), n.getLeft());
			System.out.println("Left exit");
		} else {
			System.out.println("\tvalue");
			n.setValue(t.getValue());
			// fill symbol table here

			//
			n.setRight(new Node(n));
			buildItem(scan.nextToken(), n.getRight());
			System.out.println("Right exit");
		}
		//
		System.out.println("Exit builditem");
	}

}
