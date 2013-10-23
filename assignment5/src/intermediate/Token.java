package intermediate;

public class Token {
String value ;
TokenType type ;
	public Token(String s){
		value= s;
		//type=tt;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public TokenType getType() {
		return type;
	}
	public void setType(TokenType type) {
		this.type = type;
	}
	
}
