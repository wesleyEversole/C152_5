/*
 * @Author Tim Stullich , Wesley Eversole
 * Assignment 5 
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
		exc(n);
	}

	private void exc(Node n) {
		// System.out.println("exc");
		if (n==null) {
			//System.out.println("Scheme: null exception");
			return;
		}
		if (n.getParent() == null) {
			// Top most node
			System.out.println(n.getValue());
			//System.out.println("TopLeft");
			exc(n.getLeft());
			//System.out.println("Top Right");
			exc(n.getRight());
			return;
		}

		if (n.getLeft() != null) {
			// System.out.println(indent + "(");
			// indent += " ";
			//System.out.println("Left");
			exc(n.getLeft());
		}

		if (n != null) {
			if (!n.getValue().isEmpty()) {
				//System.out.print("Node VALUE:");
				// need to print (
				// needs formating
				if (n.getType() == TokenType.OPEN_LIST) {
					System.out.println();
					indent += " ";
				}
				//System.out.println(indent + n.getValue() + " ");
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
			} else {
				// empty value
				//System.out.println("Empty Value");
			}
		}

		if (n.getRight() != null) {
			//System.out.println("Right");
			exc(n.getRight());
		}
		//System.out.println("Leaving:"+ ((n!= null)?n.getValue():"EMPTY"));
		return;
	}
}
