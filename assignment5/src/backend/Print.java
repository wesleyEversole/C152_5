/*
 * @Author Tim Stullich , Wesley Eversole
 * Assignment 6
 * Project for CS 152
 */
package backend;

import java.util.Map.Entry;

import frontend.TokenType;

import intermediate.*;

public class Print {
	String indent = "";

	public void interp(Program pro) {

		// System.out.println("(");
		for (TopLvlItem tli : pro.getProlist()) {
			System.out.println("Printing Tree");
			System.out.println("First item" + tli.getMainForm().getNode());
			exc(tli.getMainForm().getNode());
			System.out.println("Printing End of Tree\n");
			// System.out.println("Printing SymbolTable");
			// for (Entry<String, ObjectValue> entry : tli.getMST().entrySet())
			// {
			// System.out.println(entry.getKey());
			// }
		}

	}

	public void print(Node n) {
//		ObjectValue obj = new ObjectValue(n.getOt(), n);
//		if (n.getOt() == ObjectType.SCHEME_PAIR) {
//			System.out.println(obj.get());
//		} else {
			exc(n);
//		}
	}

	private void exc(Node n) {
		// System.out.println("N"+n);
		// System.out.println("exc");
		if (n == null) {
			// System.out.println("Scheme: null exception");
			return;
		}
		if (n.getParent() == null) {
			// Top most node
			System.out.println(n.getValue());
			// System.out.println("TopLeft");
			exc(n.getLeft());
			// System.out.println("Top Right");
			exc(n.getRight());
			return;
		}
		// System.out.println("N" + n);

		if (!n.getValue().isEmpty()) {
			// System.out.println("Node VALUE:");

			System.out.print(n.getValue());
			if (n.getType() == TokenType.OPEN_LIST) {
				System.out.println();
				indent += " ";
			}

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
		} else {
			// empty value
			// System.out.println("Empty Value");
		}
		if (n.getLeft() != null) {
			// System.out.println(indent + "(");
			// indent += " ";
			// System.out.println("Left");
			// System.out.println("N" + n);

			exc(n.getLeft());
		}

		if (n.getRight() != null) {
			// System.out.println("Right");
			exc(n.getRight());
			// System.out.println();
		} else {
			System.out.println();
		}
		// System.out.println("Leaving:"+ ((n!= null)?n.getValue():"EMPTY"));
		return;
	}
}
