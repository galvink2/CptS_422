package project.checks;

import java.util.ArrayList;
import java.util.List;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;


public class HalsteadEffortCheck extends AbstractCheck {

	double HTotalOperands = 0;
	double HUniqueOperands = 0;
	double HUniqueOperators = 0;
	ArrayList<String> uniqueOperands = new ArrayList<String>();
	ArrayList<String> uniqueOperators = new ArrayList<String>();
	double HDifficulty = 0;
	
	double HVocab = 0;
	double HLength = 0;
	double HVolume = 0;
	
	double HEffort = 0;
	
	List<String> operators = List.of( "+", "-", "/", "*", "%", "+=",
			"/=", "*=", "%=", "<", ">", "==", ">=", "<=",
			"!=", "||", "&&", "!", "|", "&", "~", "|=", "&=",
    		">>", ">>=", "<<", "<<=", "=", "VARIABLE_DEF", "++", "--" );
	
	public double getTotalOps() {
		return HTotalOperands;
	}
	
	public double getUniqueOperands(){
		return HUniqueOperands;
	}
	
	public double getUniqueOperators() {
		return HUniqueOperators;
	}
	
	public double getDifficulty() {
		return HDifficulty;
	}
	
	public ArrayList<String> getOperandsArray() {
		return uniqueOperands;
	}
	
	public ArrayList<String> getOperatorsArray() {
		return uniqueOperators;
	}
	
	public double getVocab() {
		return HVocab;
	}
	
	public double getLength(){
		return HLength;
	}
	
	public double getVolume() {
		return HVolume;
	}
	
	public double getEffort() {
		return HEffort;
	}
	
	  @Override
	  public int[] getAcceptableTokens() {
			// whenever it sees an EXPR or IDENT, it calls visitToken()
	    return new int[] { 
	    		// arithmetic
	    		TokenTypes.PLUS,
	    		TokenTypes.MINUS,
	    		TokenTypes.DIV,
	    		TokenTypes.STAR,
	    		TokenTypes.MOD,
	    		// plus variations
	    		TokenTypes.PLUS_ASSIGN,
	    		TokenTypes.DIV_ASSIGN,
	    		TokenTypes.STAR_ASSIGN,
	    		TokenTypes.MOD_ASSIGN,
	    		// comparison
	    		TokenTypes.LT,
	    		TokenTypes.GT,
	    		TokenTypes.EQUAL,
	    		TokenTypes.GE,
	    		TokenTypes.LE,
	    		TokenTypes.NOT_EQUAL,
	    		//boolean
	    		TokenTypes.LOR,
	    		TokenTypes.LAND,
	    		TokenTypes.LNOT,
	    		//bit
	    		TokenTypes.BOR,
	    		TokenTypes.BAND,
	    		TokenTypes.BNOT,
	    		TokenTypes.BOR_ASSIGN,
	    		TokenTypes.BAND_ASSIGN,
	    		TokenTypes.SR,
	    		TokenTypes.SR_ASSIGN,
	    		TokenTypes.SL,
	    		TokenTypes.SL_ASSIGN,
	    		//assignment
	    		TokenTypes.ASSIGN,
	    		TokenTypes.POST_INC,
	    		TokenTypes.POST_DEC,
	    		
	    		//operators
	    		TokenTypes.IDENT, 
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
	    return new int[] { 
	    		// arithmetic
	    		TokenTypes.PLUS,
	    		TokenTypes.MINUS,
	    		TokenTypes.DIV,
	    		TokenTypes.STAR,
	    		TokenTypes.MOD,
	    		// plus variations
	    		TokenTypes.PLUS_ASSIGN,
	    		TokenTypes.DIV_ASSIGN,
	    		TokenTypes.STAR_ASSIGN,
	    		TokenTypes.MOD_ASSIGN,
	    		// comparison
	    		TokenTypes.LT,
	    		TokenTypes.GT,
	    		TokenTypes.EQUAL,
	    		TokenTypes.GE,
	    		TokenTypes.LE,
	    		TokenTypes.NOT_EQUAL,
	    		//boolean
	    		TokenTypes.LOR,
	    		TokenTypes.LAND,
	    		TokenTypes.LNOT,
	    		//bit
	    		TokenTypes.BOR,
	    		TokenTypes.BAND,
	    		TokenTypes.BNOT,
	    		TokenTypes.BOR_ASSIGN,
	    		TokenTypes.BAND_ASSIGN,
	    		TokenTypes.SR,
	    		TokenTypes.SR_ASSIGN,
	    		TokenTypes.SL,
	    		TokenTypes.SL_ASSIGN,
	    		//assignment
	    		TokenTypes.ASSIGN,
	    		TokenTypes.POST_INC,
	    		TokenTypes.POST_DEC,
	    		
	    		//operators
	    		TokenTypes.IDENT, 
	    		TokenTypes.NUM_DOUBLE,
	    		TokenTypes.NUM_FLOAT, 
	    		TokenTypes.NUM_INT,
	    		TokenTypes.NUM_LONG };
	  }
	  
	  @Override
	  public void beginTree(DetailAST ast)
	  {
		HTotalOperands = 0;
		HUniqueOperands = 0;
		HUniqueOperators = 0;
		HDifficulty = 0;
		uniqueOperators.clear();
		uniqueOperands.clear();
		HVocab = 0;
		HVolume = 0;
		HEffort = 0;
	  }

	  @Override
	  public void visitToken(DetailAST ast) {
			
	    // get parent
		DetailAST parent = ast.getParent();
		
		//gets unique operators and vocab
		//if it has children, its an operator
		if(ast.hasChildren())
		{
			HLength++;
			if (!uniqueOperators.contains(ast.getText()))
			{
				//add it and increment the vocab count
				uniqueOperators.add(ast.getText());
				HUniqueOperators++;
			}
		}
		//gets unique operands
		//if it does not have children and its parent is an operator, it is an operand
		else if(operators.contains(parent.getText()))
		{
			HLength++;
			HTotalOperands++;
			if (!uniqueOperands.contains(ast.getText()))
			{
				//add it and increment the vocab count
				uniqueOperands.add(ast.getText());
				HUniqueOperands++;
			}
		}

	  }
	  
	  // aux for finding log base 2 of int
	  public static double log2(double x)
	  {
	      return (double) (Math.log(x) / Math.log(2));
	  }
	  
	  public void finishTree(DetailAST rootAST)
	  {
		  HVocab = HUniqueOperators + HUniqueOperands;
		  
		  // V    =  N      *     log2(n)
		  HVolume = HLength * log2(HVocab);
		  
		  // (unique operators/2) * (number of operands/unique operands)
		  HDifficulty = (HUniqueOperators/2) * (HTotalOperands/HUniqueOperands);
		  

		  
		  HEffort = HDifficulty * HVolume;
		  log(rootAST.getLineNo(), "Halstead effort: " + HEffort, HEffort);
	  }
}