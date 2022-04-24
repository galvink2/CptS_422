package project.checks;

import com.puppycrawl.tools.checkstyle.api.*;

public class ExpressionCheck extends AbstractCheck 
{
	
	int numExp = 0;
	
	  @Override
	  public int[] getAcceptableTokens() {
			// whenever it sees an EXPR, it calls visitToken()
	    return new int[] { TokenTypes.EXPR };
	  }
	
	  @Override
	  public int[] getRequiredTokens() {
	    return new int[0];
	  }

	  @Override
	  public int[] getDefaultTokens() {
	    return new int[] { TokenTypes.EXPR };
	  }
	  
	  public int getNumExp()
	  {
		  return numExp;
	  }
	  
	  @Override
	  public void beginTree(DetailAST ast)
	  {
		  numExp = 0;
	  }

	  @Override
	  public void visitToken(DetailAST ast) {
	    // increment the amount of operators
		  numExp++;
	  }
	  
	  public void finishTree(DetailAST rootAST)
	  {
		  log(rootAST.getLineNo(), "Total number of expressions: " + numExp, numExp);
	  }
}