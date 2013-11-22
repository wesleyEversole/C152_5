package backend;

import java.util.Map.Entry;

import intermediate.ObjectValue;
import intermediate.Program;
import intermediate.SymbolTable;
import intermediate.TopLvlItem;

public class Executor {

	private Program prog;
	private SymbolTable topTable;
	// Will have tree and top-level symbols
	public Executor(Program proj)
	{
		prog = proj;
		topTable = proj.getTLST();
		
	}
	
	public void run()
	{
		
	}
	
	private static void car()
	{
		
	}
	
	private static void cdr()
	{
		
	}
}
