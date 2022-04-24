package project.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.puppycrawl.tools.checkstyle.api.*;
import com.puppycrawl.tools.checkstyle.checks.javadoc.AbstractJavadocCheck;

import project.checks.ExpressionCheck;
import project.checks.LoopingStatementsCheck;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LoopingStatementsCheckTest {

	DetailAST astMock = mock(DetailAST.class);
	
	@Test
	public void getAcceptableTokensTest() {
		//LinesOfCommentsCheck commentMock = mock(LinesOfCommentsCheck.class);
		LoopingStatementsCheck loopTest = new LoopingStatementsCheck();
		int[] nums = {TokenTypes.FOR_CONDITION,
	    		TokenTypes.DO_WHILE,
	    		TokenTypes.LITERAL_WHILE,
	    		TokenTypes.FOR_EACH_CLAUSE};
		int[] nums2 = loopTest.getAcceptableTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getRequiredTokensTest() {
		LoopingStatementsCheck loopTest = new LoopingStatementsCheck();
		int[] nums = new int[0];
		int[] nums2 = loopTest.getRequiredTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getDefaultTokensTest() {
		//LinesOfCommentsCheck commentMock = mock(LinesOfCommentsCheck.class);
		LoopingStatementsCheck loopTest = new LoopingStatementsCheck();
		int[] nums = {TokenTypes.FOR_CONDITION,
	    		TokenTypes.DO_WHILE,
	    		TokenTypes.LITERAL_WHILE,
	    		TokenTypes.FOR_EACH_CLAUSE};
		int[] nums2 = loopTest.getDefaultTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getNumLoopsTest() {
		LoopingStatementsCheck loopTest = new LoopingStatementsCheck();
		assertEquals(0, loopTest.getNumLoops());
	}
	
	@Test
	public void beginTreeTest() {
		LoopingStatementsCheck loopTest = new LoopingStatementsCheck();
		// need to mock a DetailAST?
		loopTest.beginTree(astMock);
		assertEquals(0, loopTest.getNumLoops());
	}
	
	@Test
	public void visitTokenTest() {
		LoopingStatementsCheck loopTest = new LoopingStatementsCheck();

		loopTest.visitToken(astMock);
		assertEquals(1, loopTest.getNumLoops());
	}
	
	@Test
	public void finishTreeTest() {
		//mock a DetailAST with some parameter for getLineNo?
		LoopingStatementsCheck mockSpy = spy(new LoopingStatementsCheck());
		
		//commentTest.finishTree(ast);
		when(astMock.getLineNo()).thenReturn(2);
		Mockito.doNothing().when((AbstractCheck)mockSpy).log(Mockito.anyInt(),  Mockito.anyString(), Mockito.isA(Object.class));
		mockSpy.finishTree(astMock);
	}
}
