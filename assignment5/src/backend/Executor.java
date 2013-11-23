package backend;

import java.util.Iterator;

import intermediate.*;
import frontend.*;

public class Executor {

	private Print prt = new Print(); // list printer
	private Program prog;
	private SymbolTable topTable;
	private SymbolTable formSymbolTable;

	// Will have tree and top-level symbols
	public Executor(Program proj) {
		prog = proj;
		topTable = proj.getTLST();
	}

	public void run() {
		for (Iterator<TopLvlItem> it= prog.getProlist().iterator();it.hasNext();) {
			TopLvlItem formItem = it.next();
			// compile all defines into symbol table
			Node form = formItem.getMainForm().getNode(); // get a program form
			formSymbolTable = formItem.getMST(); // get the form symbol table
			Node tl = form.getLeft();
			Node tr=form.getRight();
			//System.out.println("Parent:"+form.getParent());
			//System.out.println("Form: "+ form.getValue());
			//System.out.println(" TL: "+tl.getValue());
			//System.out.println(" TR.left: "+tr.getLeft().getValue());
			//System.out.println(" TR.right: "+tr.getRight().getLeft().getValue());
			if (tl !=null && tl.getType()==TokenType.DEFINE) {
				// several define forms.... first is (define x value)
				
				if (tr.getLeft().getType() == TokenType.SYMBOL) {
					// named object
					// simple number ?
					switch(tr.getRight().getLeft().getType()) {
					case NUMBER:
						String s = tr.getRight().getLeft().getValue();
						//System.out.println(topTable);
						topTable.add(tr.getLeft().getValue(), new ObjectValue(ObjectType.SCHEME_NUMBER,new Double(s)));
						break;
					case STRING:break;
					default:;
					}
				}
				// remove this form from program list
				it.remove();
			}
		}
		
		// execution loop
		System.out.println("++++ EXECUTE ++++");
		for (TopLvlItem formItem : prog.getProlist()) {
			Node form = formItem.getMainForm().getNode(); // get a program form
			formSymbolTable = formItem.getMST(); // get the forms symbolTable
			execute(form);
		}

	}

	private void execute(Node form) {

		Node result = null;
		// missing tail recursion
		if (form.getLeft()==null) {
			return;
		}
		System.out.println("LFT:"+form.getLeft()+" Type:"+form.getLeft().getType());
		switch (form.getLeft().getType()) {

		case CAR:
			result = car(form);
			break;
		case CDR:
			result = cdr(form);
			break;
		case SYMBOL:
			result = doSymbol(form);
			break;
		case NULL:
			result = nullCheck(form);
			break;
		default:
			result = form;
}
		prt.print(result);
	}

	private Node doSymbol(Node form) {
		String sym = form.getLeft().getValue();
		Node result = new Node(null);
		System.out.println("Dosymbol");
		if (formSymbolTable.isInTable(sym)) {
			result.setValue(new Token(formSymbolTable.getSymbol(sym).get()));
		} else if (topTable.isInTable(sym)) {
			result.setValue(new Token(topTable.getSymbol(sym).get()));
		} else {
			result.setValue(new Token("failed to find symbol "+sym)); 
		}
		System.out.println("Value "+result.getValue());
		return result;
	}

	private Node car(Node form) {
		return form.getLeft();
	}

	private Node cdr(Node form) {
		return form.getRight();
	}
	
	private Node nullCheck(Node form)
	{
		return null;
	}

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

	private Node plus(Node form) {
		// does error check for ill formed
		Node c1 = form.getRight();
		Double a1 = new Double(c1.getLeft().getValue());
		Double a2 = new Double(c1.getRight().getLeft().getValue());

		Node result = new Node(null);
		result.setValue(new Token(new Double(a1 + a2).toString()));
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

		return result;
	}


}
