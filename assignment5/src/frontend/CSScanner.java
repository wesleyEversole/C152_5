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
			else if (isLetter(c)) {
				currentIdx++;
				return nextTokenRec(token += c, line);
			}
			else if (isParan(c)) {
				if (c == '(') {
					currentIdx++;
					return Character.toString(c);
				}
				else {
					return Character.toString(c);
				}
			}
			else if (isSingleChar(c)) {
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
	
	private static boolean isLetter(char c) {
		return (c >= 'a') && (c <= 'z');
	}
	
	private static boolean isParan(char c) {
		return (c == '(') || (c == ')');
	}

	private static boolean isSingleChar(char c) {
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