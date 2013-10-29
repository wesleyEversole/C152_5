package frontend;

public class Token {
	private String value;
	private TokenType type;

	public Token(String s) {
		value = s;
		setType(s);
	}

	private void setType(String s) {
		// set the token type based on the string...
		switch (s.toUpperCase()) {
		    // makes it easy to just use stuff from enum
		case "\"":
		case "\'":
		case "\\":
		case "[":
		case "]":
		case "{":
		case "}":
		case ";":
		case "": 
		  type = TokenType.PUNCTUATION; break;
		  
		case "(": type = TokenType.OPEN_LIST; break; 
		case ")": type = TokenType.CLOSE_LIST; break;
		
		// RESERVED WORDS "_" instead of -
		// _BANG == !
		// _STAR == *
		case "!": type = TokenType.BANG; break;
		case "*": type = TokenType.STAR; break;

		case "AND": type=TokenType.AND;break;
		case "BEGIN": type=TokenType.BEGIN;break;
		case "BEGIN0":type=TokenType.BEGIN0;break;
		case "BREAK-VAR": type=TokenType.BREAK_VAR; break;
		case "CASE": type=TokenType.CASE; break;
		case "COND": type=TokenType.COND; break;
		case "CYCLE": type=TokenType.CYCLE; break;
		case "DEFINE": type=TokenType.DEFINE; break;
		case "DELAY": type=TokenType.DELAY; break;
		case "DELAY-LIST-CONS": type=TokenType.DELAY_LIST_CONS; break;
		case "DO": type=TokenType.DO; break;
		case "ELSE": type=TokenType.ELSE; break;
		case "EXTEND-SYNTAX": type=TokenType.EXTEND_SYNTAX; break;
		case "FOR": type=TokenType. FOR; break;
		case "FREEZE": type=TokenType.FREEZE; break;
		case "IF": type=TokenType.IF; break;
		case "LAMBDA": type=TokenType.LAMBDA; break;
		case "LET": type=TokenType.LET; break;
		case "LETREC": type=TokenType.LETREC; break;
		case "LET*": type=TokenType.LET_STAR; break;
		case "MACRO": type=TokenType.MACRO; break;
		case "OBJECT-MAKER": type=TokenType.OBJECT_MAKER; break;
		case "OR": type=TokenType.OR; break;
		case "QUOTE": type=TokenType.QUOTE; break;
		case "REPEAT": type=TokenType.REPEAT; break;
		case "SAFE-LETREC": type=TokenType.SAFE_LETREC; break;
		case "SET!": type=TokenType.SET_BANG; break;
		case "STREAM-CONS": type=TokenType.STREAM_CONS; break;
		case "VARIABLE-CASE": type=TokenType.VARIABLE_CASE; break;
		case "WHILE": type=TokenType.WHILE; break;
		case "WRAP": type=TokenType.WRAP; break;
		case "#T": type=TokenType.TRUE; break;
		case "#F": type=TokenType.FALSE; break;
		
		default:
			 // handle numbers
			 if (s.matches("[-+]?\\d*.*\\d*([eE][-+]?\\d+)?")) {
				 type=TokenType.NUMBER;
			 }
			 // else just a symbol
			  type=TokenType.SYMBOL;
			 break;
		}
	}
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
		setType(value);
	}

	public TokenType getType() {
		return type;
	}

	public void setType(TokenType type) {
		this.type = type;
	}

}
