package intermediate;

import java.util.ArrayList;
import java.util.List;

public class Program {
	List<TopLvlItem> prolist;
	SymbolTable TLST;

	public Program() {
	    prolist = new ArrayList<>();
		TLST = new SymbolTable();
	}

	public List<TopLvlItem> getProlist() {
		return prolist;
	}

	public void setProlist(List<TopLvlItem> prolist) {
		this.prolist = prolist;
	}

	public SymbolTable getTLST() {
		return TLST;
	}

	public void setTLST(SymbolTable tLST) {
		TLST = tLST;
	}
	public void addSysmbol(String name, Object o){
		TLST.add(name,(Symbol) o);
		
	}
}
