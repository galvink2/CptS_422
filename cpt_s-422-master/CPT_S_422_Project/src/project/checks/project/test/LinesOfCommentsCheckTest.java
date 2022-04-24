package project.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.puppycrawl.tools.checkstyle.api.*;
import com.puppycrawl.tools.checkstyle.checks.javadoc.AbstractJavadocCheck;

import project.checks.ExpressionCheck;
import project.checks.LinesOfCommentsCheck;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LinesOfCommentsCheckTest {

	DetailAST astMock = mock(DetailAST.class);
	
	@Test
	public void getAcceptableTokensTest() {
		LinesOfCommentsCheck commentTest = new LinesOfCommentsCheck();
		int[] nums = {TokenTypes.BLOCK_COMMENT_BEGIN,
				TokenTypes.SINGLE_LINE_COMMENT};
		int[] nums2 = commentTest.getAcceptableTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getRequiredTokensTest() {
		LinesOfCommentsCheck commentTest = new LinesOfCommentsCheck();
		int[] nums = new int[0];
		int[] nums2 = commentTest.getRequiredTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getDefaultTokensTest() {
		//LinesOfCommentsCheck commentMock = mock(LinesOfCommentsCheck.class);
		LinesOfCommentsCheck commentTest = new LinesOfCommentsCheck();
		int[] nums = {TokenTypes.BLOCK_COMMENT_BEGIN,
				TokenTypes.SINGLE_LINE_COMMENT};
		int[] nums2 = commentTest.getDefaultTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void isCommentNodesRequiredTest() {
		LinesOfCommentsCheck commentTest = new LinesOfCommentsCheck();
		assertEquals(true, commentTest.isCommentNodesRequired());
	}
	
	@Test
	public void getNumCommentsTest() {
		LinesOfCommentsCheck commentTest = new LinesOfCommentsCheck();
		assertEquals(0, commentTest.getNumComment());
	}
	
	@Test
	public void beginTreeTest() {
		LinesOfCommentsCheck commentTest = new LinesOfCommentsCheck();
		commentTest.beginTree(astMock);
		assertEquals(0, commentTest.getNumComment());
	}
	
	@Test
	public void visitTokenTest() {
		LinesOfCommentsCheck commentTest = new LinesOfCommentsCheck();
		
		//mock an ast that has a first token BLOCK_COMMENT_END and one that doesnt
		//begin block mock
		when(astMock.getType()).thenReturn(TokenTypes.BLOCK_COMMENT_BEGIN);
		
		//set up end block mock
		DetailAST astMock2 = mock(DetailAST.class);
		when(astMock.findFirstToken(TokenTypes.BLOCK_COMMENT_END)).thenReturn(astMock2);
		
		//make it equate to 2 to differ from later test
		when(astMock.getLineNo()).thenReturn(0);
		when(astMock2.getLineNo()).thenReturn(1);
		
		//run method
		commentTest.visitToken(astMock);
		
		assertEquals(2, commentTest.getNumComment());
		
		//test else statement
		LinesOfCommentsCheck commentTest2 = new LinesOfCommentsCheck();
		DetailAST astMock3 = mock(DetailAST.class);
		//return something else
		when(astMock3.getType()).thenReturn(TokenTypes.BLOCK_COMMENT_END);
		
		commentTest2.visitToken(astMock3);
		assertEquals(1, commentTest2.getNumComment());

	}
	
	@Test
	public void finishTreeTest() {
		LinesOfCommentsCheck mockSpy = spy(new LinesOfCommentsCheck());
		DetailAST astMock = mock(DetailAST.class);
		
		when(astMock.getLineNo()).thenReturn(2);
		Mockito.doNothing().when((AbstractCheck)mockSpy).log(Mockito.anyInt(),  Mockito.anyString(), Mockito.isA(Object.class));
		mockSpy.finishTree(astMock);
	}
	


}
