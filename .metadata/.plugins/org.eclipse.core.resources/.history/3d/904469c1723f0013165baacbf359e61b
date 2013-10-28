package backend;

import intermediate.*;

/*
 * should print out the tree and table*/
public class Print {
	public Print() {
	}
	public void interp(TopLvlItem tli){
		exc(tli.getMT().getNode());
	}
	private void exc(Node n){
		if(n.getLeft()!=null){
			System.out.println("(");
			exc(n.getLeft());
		}
		if(!n.getValue().isEmpty()){
			//need to print (
			// needs formating
			System.out.println(n.getValue());
			//need to print )
		}
		if(n.getRight() !=null){
			exc(n.getRight());
		}
		return;
	}
}
