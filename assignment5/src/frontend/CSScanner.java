package frontend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class CSScanner {
	final static String[] RPLACE = { "(", ")" };
	private Stack<Token> symbols;
	private Scanner s;
	private String currentLine;
	private int currentChar;

	public CSScanner(String fileName) {
		s = null;
		// symbols = new Stack<Token>();
		try {
			s = new Scanner(new File(fileName));
			currentChar = Integer.MAX_VALUE;
			currentLine = "";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Token nextToken() {
		Token token = null;
		String tString = "";
		//System.out.println("\t\tnextToken");
		if (s.hasNext()) {
			if (currentChar > currentLine.length()) {
				currentLine = s.nextLine();
				currentLine = currentLine.trim();
				//System.out.println(currentLine);
				currentChar = 0;
			}
			char c = 0;
			boolean isComment = false;
			while ( (currentChar < currentLine.length()) && 
			        !(c == ' ' || c == '('|| c == ')')) {
				c = currentLine.charAt(currentChar++);
				
				//System.out.println("\t\t>>>" + c);
				if (isComment) {
					tString += Character.toString(c);
					c = 0;
				} else if (c == '(' || c == ')') {
					//System.out.println("\tFound: " + c);
					if (tString.length() > 0) {
						currentChar--;
					} else {
						tString = Character.toString(c);
					}
				} else if (c == ';') {
					tString += Character.toString(c);
					isComment = true;
				} else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c=='*') || c== '\'') {
					// System.out.println("letter");
					tString += Character.toString(c);
				} else if ((c >= '0') && (c <= '9')) {
					// System.out.println("Is Number: " + c);
				}
				// no case to handle ' or + or anything of the sort.
				// cases like null? would not be handled yet

			}

			if (tString.isEmpty() || isComment) {// should only be here at the end of line
				// System.out.println(tString);
				currentChar++;
				token = nextToken();
			} else { 
				//System.out.println("\tPushing: " + tString);
				token = new Token(tString);
			}

		} else {
			token = new Token("");
		}
		return token;
	}

	public boolean hasNext() {
		
		return s.hasNext();
	}
}

