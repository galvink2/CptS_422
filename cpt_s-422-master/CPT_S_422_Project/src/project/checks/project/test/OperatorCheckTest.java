package project.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.puppycrawl.tools.checkstyle.api.*;
import com.puppycrawl.tools.checkstyle.checks.javadoc.AbstractJavadocCheck;

import project.checks.OperandCheck;
import project.checks.OperatorCheck;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OperatorCheckTest {

	DetailAST astMock = mock(DetailAST.class);

	@Test
	public void getAcceptableTokensTest() {
		//LinesOfCommentsCheck commentMock = mock(LinesOfCommentsCheck.class);
		OperatorCheck opTest = new OperatorCheck();
		int[] nums = { TokenTypes.PLUS,
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
	    		TokenTypes.POST_DEC };
		int[] nums2 = opTest.getAcceptableTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getRequiredTokensTest() {
		OperatorCheck opTest = new OperatorCheck();
		int[] nums = new int[0];
		int[] nums2 = opTest.getRequiredTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getDefaultTokensTest() {
		//LinesOfCommentsCheck commentMock = mock(LinesOfCommentsCheck.class);
		OperatorCheck opTest = new OperatorCheck();
		int[] nums = {TokenTypes.PLUS,
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
	    		TokenTypes.POST_DEC};
		int[] nums2 = opTest.getDefaultTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void beginTreeTest() {
		OperatorCheck opTest = new OperatorCheck();
		//need to mock a DetailAST
		opTest.beginTree(astMock);
		assertEquals(0, opTest.getNumOps());
	}
	
	@Test
	public void visitTokenTest() {
		OperatorCheck opTest = new OperatorCheck();
		
		opTest.visitToken(astMock);
		assertEquals(1, opTest.getNumOps());
	}
	
	@Test
	public void finishTreeTest() {
		OperatorCheck mockSpy = spy(new OperatorCheck());
		
		when(astMock.getLineNo()).thenReturn(2);
		Mockito.doNothing().when((AbstractCheck)mockSpy).log(Mockito.anyInt(),  Mockito.anyString(), Mockito.isA(Object.class));
		mockSpy.finishTree(astMock);
	}
}
