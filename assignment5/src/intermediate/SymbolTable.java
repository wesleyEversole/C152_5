package intermediate;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class SymbolTable {
	Map<String, Symbol> tm;

	public SymbolTable() {
		tm = new TreeMap<>();
	}

	public Boolean isInTable(String s) {
		return tm.containsKey(s);
	}

	public Symbol getSymbol(String s) {
		return tm.get(s);
	}

	public void add(String s, Symbol sy) {
		tm.put(s, sy);
	}

	public Set<Entry<String, Symbol>> entrySet() {
		return tm.entrySet();
	}
}
