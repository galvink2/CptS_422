package project.checks;

import com.puppycrawl.tools.checkstyle.api.*;

public class LoopingStatementsCheck extends AbstractCheck 
{
	
	// holder for total loops found in the file
	int numLoops = 0;
	
	  @Override
	  public int[] getAcceptableTokens() {
		// whenever it sees these, it calls visitToken()
	    return new int[] { TokenTypes.FOR_CONDITION,
	    		TokenTypes.DO_WHILE,
	    		TokenTypes.LITERAL_WHILE,
	    		TokenTypes.FOR_EACH_CLAUSE}; 
	  }
	
	  @Override
	  public int[] getRequiredTokens() {
	    return new int[0];
	  }

	  @Override
	  public int[] getDefaultTokens() {
	    return new int[] { TokenTypes.FOR_CONDITION,
	    		TokenTypes.DO_WHILE,
	    		TokenTypes.LITERAL_WHILE,
	    		TokenTypes.FOR_EACH_CLAUSE };
	  }
	  
	  public int getNumLoops()
	  {
		  return numLoops;
	  }

	  @Override
	  public void beginTree(DetailAST rootAST)
	  {
		  numLoops = 0;
	  }

	  @Override
	  public void visitToken(DetailAST ast) {
		    // increment the amount of loops
		    numLoops++;
	  }
	  
	  public void finishTree(DetailAST rootAST)
	  {
		  log(rootAST.getLineNo(), "Total number of looping statements: " + numLoops, numLoops);
	  }
}