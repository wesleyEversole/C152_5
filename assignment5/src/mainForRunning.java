import java.io.File;

import frontend.Scan;


public class mainForRunning {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(String i:args){
		System.out.println(i);
		}
		
		Scan s= new Scan(args[0]);
	}

}
