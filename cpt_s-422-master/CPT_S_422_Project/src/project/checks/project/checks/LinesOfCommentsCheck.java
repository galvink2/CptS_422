package project.checks;

import com.puppycrawl.tools.checkstyle.api.*;
import com.puppycrawl.tools.checkstyle.checks.javadoc.AbstractJavadocCheck;

public class LinesOfCommentsCheck extends AbstractCheck {
	
	// holder for number of comment lines found
	int numComments = 0;
	
	// looking for these four token types
	@Override
	public int[] getAcceptableTokens()
	{
		return new int[] { TokenTypes.BLOCK_COMMENT_BEGIN,
				TokenTypes.SINGLE_LINE_COMMENT }; 
	}
	
	@Override
	public int[] getRequiredTokens() 
	{
	  return new int[0];
	}
	
	@Override
	public int[] getDefaultTokens()	
	{
		return new int[] { TokenTypes.BLOCK_COMMENT_BEGIN,
				TokenTypes.SINGLE_LINE_COMMENT };
	}
	
	@Override
	public final boolean isCommentNodesRequired()
	{
		return true;
	}
	
	public int getNumComment()
	{
		return numComments;
	}
	
	@Override
	public void beginTree(DetailAST ast)
	{
		numComments = 0;
	}

	// when it finds one of the tokens, it increments numComments
	@Override
	public void visitToken(DetailAST ast)
	{
		// it's a block comment
		if (ast.getType() == TokenTypes.BLOCK_COMMENT_BEGIN)
		{
			//find the last line in this block
			DetailAST endOfBlock = ast.findFirstToken(TokenTypes.BLOCK_COMMENT_END);
			
			//add the number of comment lines in this block to the total
			numComments += (endOfBlock.getLineNo() - ast.getLineNo() + 1);
		}
		// it's a single line comment
		else
		{
			numComments++;
		}

	}
	
	// finishes and prints the total number of comments found
	@Override
	public void finishTree(DetailAST rootAST)
	{
		log(rootAST.getLineNo(), "Total number of comment lines: " + numComments, numComments);
	}
}
