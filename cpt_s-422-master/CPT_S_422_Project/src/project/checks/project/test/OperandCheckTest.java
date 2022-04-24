package project.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.puppycrawl.tools.checkstyle.api.*;
import com.puppycrawl.tools.checkstyle.checks.javadoc.AbstractJavadocCheck;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import project.checks.ExpressionCheck;
import project.checks.OperandCheck;

public class OperandCheckTest {

	DetailAST astMock = mock(DetailAST.class);
	
	@Test
	public void getAcceptableTokensTest() {
		//LinesOfCommentsCheck commentMock = mock(LinesOfCommentsCheck.class);
		OperandCheck opTest = new OperandCheck();
		int[] nums = { TokenTypes.IDENT, 
	    		TokenTypes.NUM_DOUBLE,
	    		TokenTypes.NUM_FLOAT, 
	    		TokenTypes.NUM_INT,
	    		TokenTypes.NUM_LONG };
		int[] nums2 = opTest.getAcceptableTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getRequiredTokensTest() {
		OperandCheck opTest = new OperandCheck();
		int[] nums = new int[0];
		int[] nums2 = opTest.getRequiredTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getDefaultTokensTest() {
		//LinesOfCommentsCheck commentMock = mock(LinesOfCommentsCheck.class);
		OperandCheck opTest = new OperandCheck();
		int[] nums = {TokenTypes.IDENT, 
	    		TokenTypes.NUM_DOUBLE,
	    		TokenTypes.NUM_FLOAT, 
	    		TokenTypes.NUM_INT,
	    		TokenTypes.NUM_LONG};
		int[] nums2 = opTest.getDefaultTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getNumOpsTest() {
		OperandCheck opTest = new OperandCheck();
		assertEquals(0, opTest.getNumOps());
	}
	
	@Test
	public void beginTreeTest() {
		OperandCheck opTest = new OperandCheck();
		//need to mock a DetailAST
		opTest.beginTree(astMock);
		assertEquals(0, opTest.getNumOps());
	}
	
	@Test
	public void visitTokenTest() {
		OperandCheck opTest = new OperandCheck();
		DetailAST astMock2 = mock(DetailAST.class);
		when(astMock.getParent()).thenReturn(astMock2);
		when(astMock2.getText()).thenReturn("+");
		//mock an ast that has a first token BLOCK_COMMENT_END and one that doesnt
		opTest.visitToken(astMock);
		assertEquals(1, opTest.getNumOps());
	}
	
	@Test
	public void finishTreeTest() {
		OperandCheck mockSpy = spy(new OperandCheck());
		
		when(astMock.getLineNo()).thenReturn(2);
		Mockito.doNothing().when((AbstractCheck)mockSpy).log(Mockito.anyInt(),  Mockito.anyString(), Mockito.isA(Object.class));
		mockSpy.finishTree(astMock);
	}
}
