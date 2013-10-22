package frontend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import intermediate.Token;

public class Scan {
final static String[] RPLACE = {"(",")"};
	/*
 * need to make a FileReader and a BufferedReader to deal with the input file*/
public Scan(String fName){
	String str= "";
	
	Scanner s = null;
	
	try {
		s = new Scanner(new File(fName));
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	int n=1;
	while(s.hasNext()){
		
		str = s.nextLine();
		System.out.println("current line #"+n+": "+str);
		//may not want to replace the ( and )
		str = str.replace(RPLACE[0],"");
		str = str.replace(RPLACE[1],"");
		str=str.trim();
		String[] strSplit =str.split(" ");
		for(String i:strSplit ){
			if(i.equals(";")){
				break;
				}
				if(!i.equals("	")){
					Token t = new Token(i);
					System.out.println("token= "+t.getValue()
							+" type = "
							+ t.getType());
					}	
		}
		 n++;
	}
}

	
}
