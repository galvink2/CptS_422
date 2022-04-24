package project.checks;

import com.puppycrawl.tools.checkstyle.api.*;

import java.util.List;

public class OperandCheck extends AbstractCheck 
{
	
	// holder for total operands found in the file
	int numOps = 0;
	// list of all operators
	List<String> operators = List.of( "+", "-", "/", "*", "%", "+=",
			"/=", "*=", "%=", "<", ">", "==", ">=", "<=",
			"!=", "||", "&&", "!", "|", "&", "~", "|=", "&=",
    		">>", ">>=", "<<", "<<=", "=", "VARIABLE_DEF" );
	
	
	  @Override
	  public int[] getAcceptableTokens() {
		// whenever it sees an IDENT, it calls visitToken()
	    return new int[] { TokenTypes.IDENT, 
	    		TokenTypes.NUM_DOUBLE,
	    		TokenTypes.NUM_FLOAT, 
	    		TokenTypes.NUM_INT,
	    		TokenTypes.NUM_LONG }; 
	  }
	
	  @Override
	  public int[] getRequiredTokens() {
	    return new int[0];
	  }

	  @Override
	  public int[] getDefaultTokens() {
	    return new int[] { TokenTypes.IDENT, 
	    		TokenTypes.NUM_DOUBLE,
	    		TokenTypes.NUM_FLOAT, 
	    		TokenTypes.NUM_INT,
	    		TokenTypes.NUM_LONG }; 
	  }
	  
	  public int getNumOps()
	  {
		  return numOps;
	  }
	  
	  @Override
	  public void beginTree(DetailAST ast)
	  {
		  numOps = 0;
	  }

	  @Override
	  public void visitToken(DetailAST ast) {
		  // if the child has a parent that's an operator, its an operand
		  DetailAST parent = ast.getParent();
		  if (operators.contains(parent.getText()))
		  {
			  numOps++;
		  }	
	  }
	  
	  public void finishTree(DetailAST rootAST)
	  {
		  log(rootAST.getLineNo(), "Total number of operands: " + numOps, numOps);
	  }
}