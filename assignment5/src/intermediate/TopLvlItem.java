/*
 * @Author Tim Stullich , Wesley Eversole
 * Assignment 6
 * Project for CS 152
 */
package intermediate;

public class TopLvlItem {
	Tree mainForm;
	SymbolTable formSymbolTable;

	public TopLvlItem(Tree t, SymbolTable symbolTable) {
		mainForm = t;
		formSymbolTable = symbolTable;
	}

	public Tree getMainForm() {
		return mainForm;
	}

	public void setMainForm(Tree form) {
		mainForm = form;
	}

	public SymbolTable getMST() {
		return formSymbolTable;
	}

	public void setMST(SymbolTable mST) {
		formSymbolTable = mST;
	}
}
