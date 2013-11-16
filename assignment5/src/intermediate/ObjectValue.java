/*
 * @Author Tim Stullich , Wesley Eversole
 * Assignment 5 
 * Project for CS 152
 */
package intermediate;

/**
 * 
 * Generic Object for Scheme
 * 
 * 
 */
public class ObjectValue {
	Object value;
	ObjectType type;

	public ObjectValue() {
		value = "";
	}

	public ObjectValue(ObjectType objectType) {
		value = objectType;
	}

	// needs to be improved to provide proper values based upon types....
	public String get() {
		return value.toString();
	}

	public void set(String s) {
		value = s;
	}
}
