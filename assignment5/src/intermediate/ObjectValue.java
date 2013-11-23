/*
 * @Author Tim Stullich , Wesley Eversole
 * Assignment 5 
 * Project for CS 152
 */
package intermediate;

import frontend.TokenType;

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
	String indent;
	private String print(Node n) {
		    StringBuilder retv = new StringBuilder();
			if (n==null) {
				return "";
			}
			if (n.getParent() == null) {
				// Top most node
				retv.append(print(n.getLeft()));
				retv.append(print(n.getRight()));
				return retv.toString();
			}

			if (n != null) {
				if (!n.getValue().isEmpty()) {
					if (n.getType() == TokenType.OPEN_LIST) {
						indent += " ";
					}
					//retv.append(indent);
					retv.append(n.getValue());
					retv.append(" ");

					if (n.getLeft() == null && n.getRight() != null) {
						Node n2 = n.getRight();
						if (n2.getLeft() == null && n2.getRight() == null) {
							if (indent.length() > 0) {
								indent = indent.substring(0, indent.length() - 1);
							} else {
								indent = "";
							}
							retv.append(indent + ")");
						}
					}
				} else {
					// empty value
					//System.out.println("Empty Value");
				}
			}

			if (n.getLeft() != null) {
				retv.append(print(n.getLeft()));
			}

			if (n.getRight() != null) {
				retv.append(print(n.getRight()));
			}
			return retv.toString();
	}
	// needs to be improved to provide proper values based upon types....
	public String get() {
		//System.out.println("objectvalue tostring() "+value);
		
		if (value==null) return "";
		
		switch (type) {
		case SCHEME_PAIR:
			// create a string value for a list/pair
			return print((Node)value);
			//break;
		case SCHEME_NUMBER:
			//System.out.println("Value: "+value);
			return value.toString();
			//break;
		default:
			System.out.println("get type"+type);
			return value.toString();
		}
	}

	public void set(String s) {
		value = s;
		type = ObjectType.SCHEME_STRING;
	}
}
