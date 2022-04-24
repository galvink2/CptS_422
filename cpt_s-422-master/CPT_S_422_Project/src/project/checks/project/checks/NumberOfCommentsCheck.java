package project.checks;

import com.puppycrawl.tools.checkstyle.api.*;
import com.puppycrawl.tools.checkstyle.checks.javadoc.AbstractJavadocCheck;

public class NumberOfCommentsCheck extends AbstractCheck {
	
	// holder for number of whole comments found
	int numComments = 0;
	
	@Override
	public int[] getAcceptableTokens()
	{
		return new int[] { TokenTypes.COMMENT_CONTENT }; 
	}
	
	@Override
	public int[] getRequiredTokens() 
	{
	  return new int[0];
	}
	
	@Override
	public int[] getDefaultTokens()	
	{
		return new int[] { TokenTypes.COMMENT_CONTENT };
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
		//increment # of comments
		numComments++;
	}
	
	// finishes and prints the total number of comments found
	@Override
	public void finishTree(DetailAST rootAST)
	{
		log(rootAST.getLineNo(), "Total number of comments: " + numComments, numComments);
	}
}
