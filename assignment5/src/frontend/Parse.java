package frontend;

import intermediate.*;

public class Parse {
	// private CSScanner scan;
	private TopLvlItem pro;

	public Parse(String s) {
		init(new CSScanner(s));

	}


	CSScanner scan;


	private void init(CSScanner scan) {
		this.scan = scan;
		// this.scan = new ScanTest();
		// scan = scan;
		pro = new TopLvlItem(new Tree(new Node(null)), new SymbolTable());

	}

	public TopLvlItem buildTopLvl() {
		buildItem(scan.nextToken(), pro.getMT().getNode());
		return pro;
	}

	private void buildItem(Token t, Node n) {
		// System.out.println("buildItem");
		if (t.getType() == TokenType.EOF) {
			System.out.println("End Of File");
			return;
		}
		// if(t.getType()== TokenType.COMMENT){ }
		if (n == null) {
			System.out.println("Null node");
			return;
		}

		if (t.getValue().isEmpty()) {
			System.out.println("Empty token");
			return;
		}
		if(t.getType()== TokenType.SPACE){
			System.out.println("Token " + "Space");
		}else{
		System.out.println("Token " + t.getValue());
		}
		while (t.getType() == TokenType.COMMENT
				|| t.getType() == TokenType.SPACE) {
			t = scan.nextToken();
		}
		// main code
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
		//
		// System.out.println("Exit builditem");
	}

}
