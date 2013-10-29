package frontend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSScanner {
	private Scanner s;
	private String currentLine;
	private int currentIdx = 0;

	public CSScanner(String fileName) {
		s = null;
		// symbols = new Stack<Token>();
		try {
			s = new Scanner(new File(fileName));
			currentLine = "";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private String nextTokenRec(String token, String line) {
		// If I hit a space I need to return
		char c;
		if (currentIdx < line.length()) {
			c = line.charAt(currentIdx);
		}
		else {
			return "";
		}
		if ((c == ' ') && (token.length() > 0)) {
			currentIdx++;
			return token;
		}
		else {
			// Check for characters
			if (c == ';') {
				return ";";
			}
			if (c >= 'a' && c <= 'z') {
				currentIdx++;
				return nextTokenRec(token += c, line);
			}
			else if (c == '(' || c == ')') {
				currentIdx++;
				return Character.toString(c);
			}
			else if (isPunctuation(c)) {
				currentIdx++;
				System.out.println("Punctuation: " + c);
				return Character.toString(c);
			}
			else {
				currentIdx++;
				return nextTokenRec(token += c, line);
			}
		}
	}

	private static boolean isPunctuation(char c) {
		switch (c){
			case '\\' : return true;
			case '\'' : return true;
			case '"' : return true;
			case '[' : return true;
			case ']' : return true;
			case '{' : return true;
			case '}' : return true;
			default : return false;
		}
	}

	public Token nextToken() {
		if (s.hasNext()) {
			if (currentIdx < currentLine.length()) {
				String token = nextTokenRec("", currentLine);
				if (token.contains(";")) {
					currentLine = s.nextLine();
					currentIdx = 0;
					return new Token(";");
				}
				else {
					return new Token(token);
				}
			}
			else {
				currentLine = s.nextLine();
				currentIdx = 0;
				return new Token(nextTokenRec("", currentLine));
			}
		}
		return new Token("\0");
	}

	public boolean hasNext() {
		return s.hasNext();
	}
}
	
	/*private String nextTokenRec(String tok, String line) {
	Token token = null;
	String tString = "";
	if (s.hasNext()) {
		if (currentChar > currentLine.length()) {
			currentLine = s.nextLine();
			currentLine = currentLine.trim();
			// System.out.println(currentLine);
			currentChar = 0;
		}
		char c = 0;
		System.out.println(">>>>>>>> l=" + currentLine);
		System.out.println("           0000000000111111111122222222223333333333444444444455555555556666666666");
		System.out.println("           0123456789012345678901234567890123456789012345678901234567890123456789");
		
		System.out.println("  Char " + currentChar + " " + currentLine.length());
		while ((currentChar < currentLine.length())) {
			c = currentLine.charAt(currentChar++);

			System.out.println("\t\t>>>" + c);
            if (c== ' ') {
            	break;
            }
			if (c == '(' || c == ')') {
				System.out.println("\tFound: " + c);
				if (tString.length() > 0) {
					currentChar--;
					System.out.println(" char=" + currentChar);
				} else {
					tString = Character.toString(c);
				}
				break;
			} else if (c == ';') {
				tString = "";
				System.out.println(">>>>>>>> l=" + currentLine);
				currentLine = s.nextLine();
				currentLine = currentLine.trim();
				currentChar = 0;
			} else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')
					|| (c == '*') || c == '\'' || c == '-') {
				// System.out.println("letter");
				tString += Character.toString(c);
			} else if ((c >= '0') && (c <= '9')) {
				// System.out.println("Is Number: " + c);
			}
			// no case to handle ' or + or anything of the sort.
			// cases like null? would not be handled yet

		}

		if (tString.isEmpty()) {
			// should only be here at the end of
			// line
			System.out.println("  EMPTY" + tString);
			currentChar++;
			token = nextToken();
		} else {
			// System.out.println("\tPushing: " + tString);
			token = new Token(tString);
		}

	} else {
		token = new Token("");
	}
}*/
