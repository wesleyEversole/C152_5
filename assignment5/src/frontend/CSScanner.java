package frontend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

import intermediate.Token;

public class CSScanner 
{
	final static String[] RPLACE = {"(",")"};
	private Stack<Token> symbols;
	private Scanner s;
	
	public CSScanner(String fileName)
	{
		s = null;
		symbols = new Stack<Token>();
		try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String token = null;
		while (s.hasNext()) {
			String currentLine;
			currentLine = s.nextLine();
			currentLine = currentLine.trim();
			System.out.println(currentLine);
			for (int i = 0; i < currentLine.length(); i++) {
				char c = currentLine.charAt(i);
				System.out.println();
				if (c == '(' || c == ')') {
					//System.out.println("Found: " + c);
				}
				if(c == ';') {
					token += Character.toString(c);
				}
				if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
					//System.out.println("letter");
					token += Character.toString(c);
				}
				if ((c >= 0) && (c <= 9)) {
					//System.out.println("Is Number: " + c);
				}
			}
			
			if (token.length() > 0 && (token.charAt(0) != ';')) {
				System.out.println("Pushing: " + token);
				symbols.push(new Token(token));
			}
		}
	}
	
	public Token nextToken() {
		return symbols.pop();
	}
}

/*
 * need to make a FileReader and a BufferedReader to deal with the input file*/
	/*public Scan(String fName){
		symbols = new Stack<Token>();
		String str = "";
		Scanner s = null;
		try {
			s = new Scanner(new File(fName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int n=1;
		while(s.hasNext()) {
			str = s.nextLine();
			System.out.println("current line #" + n + ": "+str);
			//may not want to replace the ( and )
			str = str.replace(RPLACE[0],"");
			str = str.replace(RPLACE[1],"");
			str = str.trim();
			String[] strSplit =str.split(" ");
			for(String i:strSplit ) {
				if(i.equals(";")) {
					break;
				}
				if(!i.equals("	")) {
					Token t = new Token(i);
					System.out.println("token= "+t.getValue()
							+" type = "
							+ t.getType());
				}	
			}
			n++;
		}*/