/*
 * @Author Tim Stullich , Wesley Eversole
 * Assignment 5 
 * Project for CS 152
 */
package backend;

import java.util.Map.Entry;

import intermediate.*;

public class Print {
	String indent = "";

	public Print() {
	}

	public void interp(Program pro) {
		System.out.println("Printing Tree");
		System.out.println("(");
		for (TopLvlItem tli : pro.getProlist()) {
			exc(tli.getMT().getNode());
			System.out.println("Printing End of Tree\n");
			System.out.println("Printing SymbolTable");
			for (Entry<String, ObjectValue> entry : tli.getMST().entrySet()) {
				System.out.println(entry.getKey());
			}
		}

	}

	private void exc(Node n) {
		// System.out.println("exc");
		if (n.getLeft() != null) {
			System.out.println(indent + "(");
			indent += " ";
			exc(n.getLeft());
		}

		if (n != null && !n.getValue().isEmpty()) {
			// need to print (
			// needs formating
			System.out.println(indent + n.getValue());
			// System.out.println(n.getLeft() + " " +
			// ((n.getRight()!=null)?n.getRight().getValue():"null") + " " +
			// n.getParent());

			// need to print )
			if (n.getLeft() == null && n.getRight() != null) {
				Node n2 = n.getRight();
				if (n2.getLeft() == null && n2.getRight() == null) {
					if (indent.length() > 0) {
						indent = indent.substring(0, indent.length() - 1);
					} else {
						indent = "";
					}
					System.out.println(indent + ")");
				}
			}
		}
		if (n.getRight() != null) {
			exc(n.getRight());
		}
		return;
	}
}
