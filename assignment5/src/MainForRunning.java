import intermediate.TopLvlItem;
import frontend.*;
import backend.*;

public class MainForRunning {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// test parser
		Parse p = new Parse(args[0]);
		Print prt = new Print();
		TopLvlItem tli;
		tli = p.buildTopLvl();
		System.out.println("Backend printing");
		prt.interp(tli);

	}

}