import java.io.File;

import frontend.*;

;

public class MainForRunning {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (String i : args) {
			System.out.println(i);
		}

		CSScanner s = new CSScanner(args[0]);
		while(!s.nextToken().getValue().isEmpty()) {
			System.out.println("M");;
		}
		
		// test parser 
		Parse p = new Parse(args[0]);
		
		
	}

}