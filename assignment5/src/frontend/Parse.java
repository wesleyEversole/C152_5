package frontend;

import intermediate.*;

public class Parse {
	//private CSScanner scan;
	private TopLvlItem pro;

	public Parse(String s) {
		init(new CSScanner(s));

	}
    private class ScanTest {
    	String[] t = {"(","define","deriv","(","lambda", "(","poly", "var",")",
    					    "(","let*","(","(","terms","(","terminize","poly",")",")",
    					           "(","deriv-term", 
    					             "(","lambda","(","term",")",
    					               "(","cond",
    					                 "(","(","null?", "term",")", "'()",")",
    					                 "(","(","not" ,"(","member?", "var" ,"term",")",")", "'(0)",")",           
    					                 "(","(","not" ,"(","member?" ,"'^", "term",")",")", "(","upto", "var", "term",")",")",
    					                 "(","else", "(","deriv-term-expo" ,"term" ,"var",")",")",
    					             ")",")",")",
    					           "(","diff" ,"(","map" ,"deriv-term", "terms",")",")",")",
    					      "(","remove-trailing-plus" ,"(","polyize", "diff",")",")",
    					")",")",")"};
    	int p=0;
    	public Token nextToken() {
    		Token tk;
    		String token;
    		if (p==t.length) {
    			token = "";
    		} else {
    			token = t[p++];
    		}
    		return new Token(token);
    	}
    	
    }
    CSScanner scan;

	// builds the tree and symbol table
	// make a tree map first.
	// once the tree map is made make the stack the treemap will sit on
	// logic for the token type will be need... need to find out all the meaning
	// for the token types.
	// from the stack the tree can be made from the map.

	private void init(CSScanner scan) {
		this.scan = scan;
		//this.scan = new ScanTest();
		//scan = scan;
		pro = new TopLvlItem(new Tree(new Node(null)), new SymbolTable());

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
		if (t.getValue().isEmpty()) {
			System.out.println("Empty token");
			return;
		}
		System.out.println("Token " + t.getValue());

		// main code
		if (t.getType()== TokenType.CLOSE_LIST) {
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

		if (t.getType()== TokenType.OPEN_LIST) {
			//System.out.println("Left entry");
			n.setLeft(new Node(n));
			buildItem(scan.nextToken(), n.getLeft());
			//System.out.println("Left exit");
		} else {
			//System.out.println("\tvalue");
			n.setValue(t.getValue());
			// fill symbol table here
            if (t.getType()== TokenType.SYMBOL) {
            	pro.getMST().add(t.getValue(),new Symbol());
            }
			//
			n.setRight(new Node(n));
			buildItem(scan.nextToken(), n.getRight());
			//System.out.println("Right exit");
		}
		//
		//System.out.println("Exit builditem");
	}

}
