/*
 * @Author Tim Stullich , Wesley Eversole
 * Assignment 6 
 * Project for CS 152
 */
package intermediate;

public class Symbol {
	String value;

	public Symbol() {
		value = "";
	}

	public Symbol(String s) {
		value = s;
	}

	public String get() {
		return value;
	}

	public void set(String s) {
		value = s;
	}
}
