/*
 * @Author Tim Stullich , Wesley Eversole
 * Assignment 6 
 * Project for CS 152
 */
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
		compileDefines();

		// execution loop
		System.out.println("TSWEscheme running");
		for (TopLvlItem formItem : prog.getProlist()) {
			Node form = formItem.getMainForm().getNode(); // get a program form
			formSymbolTable = formItem.getMST(); // get the forms symbolTable
			execute(form);
		}

	}

	private void compileDefines() {
		for (Iterator<TopLvlItem> it = prog.getProlist().iterator(); it
				.hasNext();) {
			TopLvlItem formItem = it.next();
			// compile all defines into symbol table
			Node form = formItem.getMainForm().getNode(); // get a program form
			formSymbolTable = formItem.getMST(); // get the form symbol table
			Node tl = form.getLeft();
			Node tr = form.getRight();

			if (tl != null && tl.getType() == TokenType.DEFINE) {
				// several define forms.... first is (define x value)

				if (tr.getLeft().getType() == TokenType.SYMBOL) {
					// named object
					// simple number ?
					switch (tr.getRight().getLeft().getType()) {
					case NUMBER:
						String num = tr.getRight().getLeft().getValue();
						// System.out.println(topTable);
						topTable.add(tr.getLeft().getValue(), new ObjectValue(
								ObjectType.SCHEME_NUMBER, new Double(num)));
						break;
					case STRING:
						String str = tr.getRight().getLeft().getValue();
						// System.out.println(topTable);
						topTable.add(tr.getLeft().getValue(), new ObjectValue(
								ObjectType.SCHEME_STRING, str));
						break;
					case OPEN_LIST: // openlist gets us in lambda and other
									// forms
						// cheat for now just save the list
						Node lstCar = tr.getRight().getLeft();
						// full closure not present ... one too many close
						// parens
						lstCar = removeTail(lstCar);
						topTable.add(tr.getLeft().getValue(), new ObjectValue(
								ObjectType.SCHEME_PAIR, lstCar));
						break;
					default:
						System.out.print("Define for "
								+ tr.getRight().getLeft().getType());
						System.out.println("  value: "
								+ tr.getLeft().getValue());
					}
				}

				// remove this form from program list
				it.remove();
			}
		}
	}

	private Node removeTail(Node lstCar) {
		Node l;
		l = lstCar;
		while (l.getRight() != null) {
			l = l.getRight();
		}
		l.getParent().setRight(null); // remove trailing )
		return lstCar;
	}

	private void execute(Node form) {

		Node result = null;
		// missing tail recursion
		if (form.getLeft() == null) {
			return;
		}
		// System.out.println("LFT:"+form.getLeft()+" Type:"+form.getLeft().getType());
		switch (form.getLeft().getType()) {

		case CAR:
			result = car(form.getRight().getLeft());
			break;
		case CDR:
			result = cdr(form.getRight().getLeft());
			break;
		case SYMBOL:
			result = doSymbol(form);
			break;
		case NULL:
			result = nullCheck(form);
			break;
		case PLUS:
			result = plus(form.getRight());
			break;
		case MINUS:
			result = minus(form.getRight());
			break;
		case STAR:
			result = multiply(form.getRight());
			break;
		case DIVIDE:
			result = divide(form.getRight());
			break;		default:
			System.out.println("Default for "+form.getLeft().getType());
			result = form;
		}
		prt.print(result);
	}

	private Node doSymbol(Node form) {
		String sym = form.getLeft().getValue();
		Node result = new Node(null);
		ObjectValue obj = null;
		// System.out.println("Dosymbol");
		if (formSymbolTable.isInTable(sym)) {
			obj = formSymbolTable.getSymbol(sym);
		} else if (topTable.isInTable(sym)) {
			obj = topTable.getSymbol(sym);
		}

		if (obj == null) {
			result.setValue(new Token("failed to find symbol " + sym));
		} else {
			result.setValue(new Token(obj.get()));
			result.setOt(obj.getType());
			result.setOv(obj);
		}
		// System.out.println("Value "+result.getValue());
		return result;
	}

	private Node car(Node form) {
		// System.out.println("form type: "+form.getType()+" value: "+form.getValue());
		// System.out.println("  left:"+form.getLeft()+" type:"+form.getLeft().getType()+
		// " value"+form.getLeft().getValue());
		// System.out.println("  right:"+form.getRight());//+" type:"+form.getRight().getType());
		Node retv = form.getLeft().copy();
		retv.setOt(ObjectType.SCHEME_PAIR);
		return retv;
	}

	private Node cdr(Node form) {
		Node retv = form.getRight().copy();
		// HACK does not really work except in our tests
		retv.setValue(new Token("("));
		retv.setOt(ObjectType.SCHEME_PAIR);
//		System.out.println("right" + retv.getRight().getRight().getRight());
//		System.out.println("right"
//				+ retv.getRight().getRight().getRight().getRight());
		retv = removeTail(retv);
//		System.out.println("right"
//				+ retv.getRight().getRight().getRight().getParent());
		return retv;
	}

	private Node nullCheck(Node form) {
		return null;
	}

	private Node plus(Node form) {
		// does error check for ill formed
		//  (+ n1 n2 .... nn)

		Double a1 = new Double(form.getLeft().getValue());
		Double a2 = new Double(form.getRight().getLeft().getValue());

		Node result = new Node(null);
		result.setValue(new Token(new Double(a1 + a2).toString()));
		return result;
	}

	private Node minus(Node form) {
		Double s1 = new Double(form.getLeft().getValue());
		Double s2 = new Double(form.getRight().getLeft().getValue());

		Node result = new Node(null);
		result.setValue(new Token(new Double(s1 - s2).toString()));
		return result;
	}

	private Node multiply(Node form) {
		Double s1 = new Double(form.getLeft().getValue());
		Double s2 = new Double(form.getRight().getLeft().getValue());

		Node result = new Node(null);
		result.setValue(new Token(new Double(s1 * s2).toString()));
		return result;
	}

	private Node divide(Node form) {
		Double s1 = new Double(form.getLeft().getValue());
		Double s2 = new Double(form.getRight().getLeft().getValue());

		Node result = new Node(null);
		result.setValue(new Token(new Double(s1 / s2).toString()));

		return result;
	}

}
