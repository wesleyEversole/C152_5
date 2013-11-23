/*
 * @Author Tim Stullich , Wesley Eversole
 * Assignment 56
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
		Executor interp;
		Program prog;
		prog = p.buildTopLvl();
		System.out.println("Toplevel forms:" + prog.getProlist().size());
		interp = new Executor(prog);
		interp.run();
	}

}