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
		Program tli;
		System.out.println("call Frontend");
		tli = p.buildTopLvl();
		System.out.println("Backend printing");
		prt.interp(tli);

	}

}