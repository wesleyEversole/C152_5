package backend;

import java.util.Map.Entry;

import intermediate.*;

/*
 * should print out the tree and table*/
public class Print {
	String indent = "";
	public Print() {
	}
	public void interp(TopLvlItem tli){
		exc(tli.getMT().getNode());
		for (Entry<String,Symbol> entry :tli.getMST().entrySet()) {
		System.out.println(entry.getKey());	
		}
	}
	private void exc(Node n){
		if(n.getLeft()!=null){
			System.out.println(indent + "(");
			indent += " ";
			exc(n.getLeft());
		}
		if(!n.getValue().isEmpty()){
			//need to print (
			// needs formating
			System.out.println(indent + n.getValue());
			//System.out.println(n.getLeft() + " " + ((n.getRight()!=null)?n.getRight().getValue():"null") + " " + n.getParent());

			//need to print )
			if (n.getLeft()==null && n.getRight() != null) {
				Node n2 = n.getRight();
				if (n2.getLeft()==null && n2.getRight()==null) {
					indent = indent.substring(0, indent.length()-1);
					System.out.println(indent+")");
				}
			}
		}
		if(n.getRight() !=null){
			exc(n.getRight());
		}
		return;
	}
}
