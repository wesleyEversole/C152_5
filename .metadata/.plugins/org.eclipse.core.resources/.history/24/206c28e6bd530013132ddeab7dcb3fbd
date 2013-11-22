package backend;

import intermediate.*;
import frontend.*;

public class Executor {

	private Print prt = new Print(); // list printer
	private Program prog;
<<<<<<< HEAD
=======
	private SymbolTable topTable;

>>>>>>> ad52af7035e975ce33e686a148961f5a37462f77
	// Will have tree and top-level symbols
	public Executor(Program proj) {
		prog = proj;
	}

	public void run() {
		// execution loop
		for (TopLvlItem formItem : prog.getProlist()) {
			Node form = formItem.getMainForm().getNode(); // get a program form
			SymbolTable formSymbolTable = formItem.getMST(); // get the forms
																// symbolTable
			execute(form);
		}

	}

	private void execute(Node form) {

		Node result = null;
		// missing tail recursion
		switch (form.getLeft().getType()) {
<<<<<<< HEAD
		case CAR: result = car(form);break;
		case CDR: result = cdr(form);break;
		case NULL: result = nullCheck(form); break;
		default: result = form;
=======
		case CAR:
			result = car(form);
			break;
		case CDR:
			result = cdr(form);
			break;
		default:
			result = form;
>>>>>>> ad52af7035e975ce33e686a148961f5a37462f77
		}
		prt.print(result);
	}

	private Node car(Node form) {
		return form.getLeft();
	}

	private Node cdr(Node form) {
		return form.getRight();
	}
<<<<<<< HEAD
	
	private Node nullCheck(Node form)
	{
		
		return null;
	}
	
=======

	// returns tail of current node
	// actually parent of tail
	private Node getTail(Node n) {
		Node retv = n;
		while (n != null) {
			retv = n;
			n = n.getRight();
		}
		return retv;
		
	}

>>>>>>> ad52af7035e975ce33e686a148961f5a37462f77
	private Node plus(Node form) {
		// does error check for ill formed
		Node c1 = form.getRight();
		Double a1 = new Double(c1.getLeft().getValue());
		Double a2 = new Double(c1.getRight().getLeft().getValue());

		Node result = new Node(null);
		result.setValue(new Token(new Double(a1 + a2).toString()));
<<<<<<< HEAD
		return result;
	}
	
	private Node minus(Node form) {
		Node c1 = form.getRight();
		Double s1 = new Double(c1.getLeft().getValue());
		Double s2 = new Double(c1.getRight().getLeft().getValue());
		
		Node result = new Node(null);
		result.setValue(new Token(new Double(s1 - s2).toString()));
		return result;
	}
	
	private Node multiply(Node form) {
		Node c1 = form.getRight();
		Double s1 = new Double(c1.getLeft().getValue());
		Double s2 = new Double(c1.getRight().getLeft().getValue());
		
		Node result = new Node(null);
		result.setValue(new Token(new Double(s1 * s2).toString()));
		return result;
	}
	
	private Node divide(Node form) {
		Node c1 = form.getRight();
		Double s1 = new Double(c1.getLeft().getValue());
		Double s2 = new Double(c1.getRight().getLeft().getValue());
		
		Node result = new Node(null);
		result.setValue(new Token(new Double(s1 / s2).toString()));
=======
>>>>>>> ad52af7035e975ce33e686a148961f5a37462f77
		return result;
	}

	private Node define(Node form) {
<<<<<<< HEAD
		Node define = new Node(form);
		while (form.getRight().getType() != TokenType.CLOSE_BRACKET)
		{
			
		}
=======
		// dummy
>>>>>>> ad52af7035e975ce33e686a148961f5a37462f77
		return form;
	}
}
