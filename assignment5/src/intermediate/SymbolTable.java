/*
 * @Author Tim Stullich , Wesley Eversole
 * Assignment 5 
 * Project for CS 152
 */
package intermediate;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class SymbolTable {
	Map<String, ObjectValue> tm;

	public SymbolTable() {
		tm = new TreeMap<>();
	}

	public Boolean isInTable(String s) {
		return tm.containsKey(s);
	}

	public ObjectValue getSymbol(String s) {
		return tm.get(s);
	}

	public void add(String s, ObjectValue objectValue) {
		tm.put(s, objectValue);
	}

	public Set<Entry<String, ObjectValue>> entrySet() {
		return tm.entrySet();
	}
}
