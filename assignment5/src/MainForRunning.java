import intermediate.TopLvlItem;
import frontend.*;
import backend.*;


public class MainForRunning {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		CSScanner s = new CSScanner(args[0]);
//		while(!s.nextToken().getValue().isEmpty()) {
//			//System.out.println("M");;
//		}
		
		// test parser 
		Parse p = new Parse(args[0]);
		Print prt= new Print();
		TopLvlItem tli;
		tli=p.buildTopLvl();
		System.out.println("\n\n=======================\n");
		prt.interp(tli);
		
	}

}