/*
 * @Author Tim Stullich , Wesley Eversole
 * Assignment 5 
 * Project for CS 152
 */
package intermediate;

import java.util.ArrayList;
import java.util.List;

public class Program {
	List<TopLvlItem> formList;
	SymbolTable TLST;

	public Program() {
		formList = new ArrayList<>();
		TLST = new SymbolTable();
	}

	public List<TopLvlItem> getProlist() {
		return formList;
	}

	public void setProlist(List<TopLvlItem> formList) {
		this.formList = formList;
	}

	public SymbolTable getTLST() {
		return TLST;
	}

	public void setTLST(SymbolTable tLST) {
		TLST = tLST;
	}

	public void addSysmbol(String name, Object o) {
		TLST.add(name, (ObjectValue) o);

	}
}
