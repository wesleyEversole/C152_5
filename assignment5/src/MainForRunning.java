/*
 * @Author Tim Stullich , Wesley Eversole
 * Assignment 5 
 * Project for CS 152
 */
import intermediate.Program;
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
		Executor interp;
		Program prog;
		System.out.println("call Frontend");
		prog = p.buildTopLvl();
		System.out.println("Backend printing");
		prt.interp(prog);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
		interp = new Executor(prog);
		interp.run();
	}

}