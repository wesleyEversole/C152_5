package frontend;

public class Parse {

	public Parse(String s) {
		CSScanner scan = new CSScanner(s);
		Token tkn = new Token(null);
	//	while (scan.hasNext()) {
		//	tkn = scan.getNext();
		//}
	}

	// builds the tree and symbol table
	// make a tree map first.
	// once the tree map is made make the stack the treemap will sit on
	// logic for the token type will be need... need to find out all the meaning
	// for the token types.
	// from the stack the tree can be made from the map.

	public void build() {
	}

}