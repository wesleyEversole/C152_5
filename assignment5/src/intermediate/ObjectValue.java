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

	private ObjectValue() {
		value = "";
	}

	public ObjectValue(ObjectType objectType,Object obj) {
		value = obj;
		type = objectType;
	}

	// needs to be improved to provide proper values based upon types....
	public String get() {
		//System.out.println("objectvalue tostring() "+value);
		return value.toString();
	}

	public void set(String s) {
		value = s;
		type = ObjectType.SCHEME_STRING;
	}
}
