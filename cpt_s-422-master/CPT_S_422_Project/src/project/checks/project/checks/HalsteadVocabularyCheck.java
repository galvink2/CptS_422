package project.checks;

import java.util.ArrayList;
import java.util.List;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class HalsteadVocabularyCheck extends AbstractCheck  
{
	int HVocab = 0;
	ArrayList<String> uniques = new ArrayList<String>();
	List<String> operators = List.of( "+", "-", "/", "*", "%", "+=",
			"/=", "*=", "%=", "<", ">", "==", ">=", "<=",
			"!=", "||", "&&", "!", "|", "&", "~", "|=", "&=",
    		">>", ">>=", "<<", "<<=", "=", "VARIABLE_DEF", "++", "--" );
	
	public double getVocab() {
		return HVocab;
	}
	
	public ArrayList<String> getUniques() {
		return uniques;
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

	  // go through children through EXPR
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
		  HVocab = 0;
		  uniques.clear();
	  }

	  @Override
	  public void visitToken(DetailAST ast) {
		  
		DetailAST parent = ast.getParent();
	    // if the list of uniques does not contain this one
		if (!uniques.contains(ast.getText()))
		{
			
			//if it has children, its an operator
			if(ast.hasChildren())
			{
				//add it and increment the vocab count
				uniques.add(ast.getText());
				HVocab++;
			}
			//if it does not have children and its parent is an operator, it is an operand
			else if(operators.contains(parent.getText()))
			{
				//add it and increment the vocab count
				uniques.add(ast.getText());
				HVocab++;
			}
		}
	  }
	  
	  public void finishTree(DetailAST rootAST)
	  {
		  log(rootAST.getLineNo(), "Halstead vocabulary: " + HVocab, HVocab);
	  }
}

