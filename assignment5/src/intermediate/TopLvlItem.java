package intermediate;

public class TopLvlItem {
	Tree MT;
	SymbolTable MST;
	public TopLvlItem(Tree t, SymbolTable st){
		MT= t;
		MST= st;
	}
	public Tree getMT() {
		return MT;
	}
	public void setMT(Tree mT) {
		MT = mT;
	}
	public SymbolTable getMST() {
		return MST;
	}
	public void setMST(SymbolTable mST) {
		MST = mST;
	}
}