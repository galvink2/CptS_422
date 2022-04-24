package project.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.puppycrawl.tools.checkstyle.api.*;
import com.puppycrawl.tools.checkstyle.checks.javadoc.AbstractJavadocCheck;

import project.checks.HalsteadLengthCheck;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HalsteadLengthCheckTest {

	HalsteadLengthCheck lengthTest = new HalsteadLengthCheck();
	DetailAST astMock = mock(DetailAST.class);
	
	@Test
	public void getAcceptableTokensTest() {
		//LinesOfCommentsCheck commentMock = mock(LinesOfCommentsCheck.class);
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
	    		TokenTypes.POST_DEC,
	    		
	    		//operators
	    		TokenTypes.IDENT, 
	    		TokenTypes.NUM_DOUBLE,
	    		TokenTypes.NUM_FLOAT, 
	    		TokenTypes.NUM_INT,
	    		TokenTypes.NUM_LONG};
		int[] nums2 = lengthTest.getAcceptableTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getRequiredTokensTest() {
		int[] nums = new int[0];
		int[] nums2 = lengthTest.getRequiredTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getDefaultTokensTest() {
		//LinesOfCommentsCheck commentMock = mock(LinesOfCommentsCheck.class);
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
	    		TokenTypes.POST_DEC,
	    		
	    		//operators
	    		TokenTypes.IDENT, 
	    		TokenTypes.NUM_DOUBLE,
	    		TokenTypes.NUM_FLOAT, 
	    		TokenTypes.NUM_INT,
	    		TokenTypes.NUM_LONG};
		int[] nums2 = lengthTest.getDefaultTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getLengthTest() {
		assertEquals(0, lengthTest.getLength());
	}
	
	@Test
	public void beginTreeTest() {
		lengthTest.beginTree(astMock);
		assertEquals(0, lengthTest.getLength());
	}
	
	@Test
	public void visitTokenTest() {
		
		//parent mock
		DetailAST astParent = mock(DetailAST.class);
		when(astMock.getParent()).thenReturn(astParent);
		
		//first if
		when(astMock.hasChildren()).thenReturn(true);
		lengthTest.visitToken(astMock);
		assertEquals(1, lengthTest.getLength());
		
		//else if
		when(astMock.hasChildren()).thenReturn(false);
		when(astParent.getText()).thenReturn("+");
		lengthTest.visitToken(astMock);
		//will be 2 because we've called this already
		assertEquals(2, lengthTest.getLength());
		
	}

	@Test
	public void finishTreeTest() {
		HalsteadLengthCheck mockSpy = spy(new HalsteadLengthCheck());
		
		when(astMock.getLineNo()).thenReturn(2);
		Mockito.doNothing().when((AbstractCheck)mockSpy).log(Mockito.anyInt(),  Mockito.anyString(), Mockito.isA(Object.class));
		mockSpy.finishTree(astMock);
	}

}
