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
		if (s.hasNext()) {
			if (currentChar > currentLine.length()) {
				currentLine = s.nextLine();
				currentLine = currentLine.trim();
				System.out.println(currentLine);
				currentChar = 0;
			}
			char c = 0;
			boolean isComment = false;
			while (currentChar < currentLine.length()-1 && c != ' ') {
				c = currentLine.charAt(currentChar++);
				// System.out.println();
				if(isComment){
					tString += Character.toString(c);
					c=0;
				}else
				if (c == '(' || c == ')') {
					// System.out.println("Found: " + c);
					tString += Character.toString(c);
				}else
				if (c == ';') {
					tString += Character.toString(c);
					isComment=true;
				}else
				if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
					// System.out.println("letter");
					tString += Character.toString(c);
				}else
				if ((c >= '0') && (c <= '9')) {
					// System.out.println("Is Number: " + c);
				}
				
			}

			if (!(tString.isEmpty() || (tString.charAt(0)==';'))) {
				System.out.println("Pushing: " + tString);
				token = new Token(tString);
			} else {	// should only be here at the end of line
				//System.out.println(tString);
				currentChar++;
				token = nextToken();
			}

		} else {
			token = new Token("");
		}
		return token;
	}
}

/*
 * need to make a FileReader and a BufferedReader to deal with the input file
 */
/*
 * public Scan(String fName){ symbols = new Stack<Token>(); String str = "";
 * Scanner s = null; try { s = new Scanner(new File(fName)); } catch
 * (FileNotFoundException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); } int n=1; while(s.hasNext()) { str = s.nextLine();
 * System.out.println("current line #" + n + ": "+str); //may not want to
 * replace the ( and ) str = str.replace(RPLACE[0],""); str =
 * str.replace(RPLACE[1],""); str = str.trim(); String[] strSplit
 * =str.split(" "); for(String i:strSplit ) { if(i.equals(";")) { break; }
 * if(!i.equals("	")) { Token t = new Token(i);
 * System.out.println("token= "+t.getValue() +" type = " + t.getType()); } }
 * n++; }
 */