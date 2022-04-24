package project.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.puppycrawl.tools.checkstyle.api.*;
import com.puppycrawl.tools.checkstyle.checks.javadoc.AbstractJavadocCheck;

import project.checks.ExpressionCheck;
import project.checks.HalsteadDifficultyCheck;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HalsteadDifficultyCheckTest {

	HalsteadDifficultyCheck difficultyTest = new HalsteadDifficultyCheck();
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
		int[] nums2 = difficultyTest.getAcceptableTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getRequiredTokensTest() {
		int[] nums = new int[0];
		int[] nums2 = difficultyTest.getRequiredTokens();
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
		int[] nums2 = difficultyTest.getDefaultTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getTotalOpsTest() {
		assertEquals(0, difficultyTest.getTotalOps());
	}
	
	@Test
	public void getUniqueOperandsTest() {
		assertEquals(0, difficultyTest.getUniqueOperands());
	}
	
	@Test
	public void getUniqueOperatorsTest() {
		assertEquals(0, difficultyTest.getUniqueOperators());
	}
	
	@Test
	public void getDifficultyTest() {
		assertEquals(0, difficultyTest.getDifficulty());
	}
	
	@Test
	public void getOperandsArrayTest() {
		ArrayList<String> arr = new ArrayList<String>();
		assertIterableEquals(arr, difficultyTest.getOperandsArray());
	}
	
	@Test
	public void getOperatorsArrayTest() {
		ArrayList<String> arr = new ArrayList<String>();
		assertIterableEquals(arr, difficultyTest.getOperatorsArray());
	}
	
	@Test
	public void beginTreeTest() {
		ArrayList<String> arr = new ArrayList<String>();

		difficultyTest.beginTree(astMock);
		assertEquals(0, difficultyTest.getTotalOps());
		assertEquals(0, difficultyTest.getUniqueOperands());
		assertEquals(0, difficultyTest.getUniqueOperators());
		assertEquals(0, difficultyTest.getDifficulty());
		assertIterableEquals(arr, difficultyTest.getOperandsArray());
		assertIterableEquals(arr, difficultyTest.getOperatorsArray());
	}
	
	@Test
	public void visitTokenTest() {
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("+");
		ArrayList<String> arr2 = new ArrayList<String>();
		arr2.add("x");
		
		//parent mock
		DetailAST astParent = mock(DetailAST.class);
		when(astMock.getParent()).thenReturn(astParent);
		
		//test first if statement
		when(astMock.hasChildren()).thenReturn(false);
		when(astParent.getText()).thenReturn("+");
		when(astMock.getText()).thenReturn("x");
		difficultyTest.visitToken(astMock);
		assertEquals(1, difficultyTest.getTotalOps());
		
		//test elseif (function will go to this in the same track as the first if)
		difficultyTest.visitToken(astMock);
		assertIterableEquals(arr2, difficultyTest.getOperandsArray());
		assertEquals(1, difficultyTest.getUniqueOperands());
		
		//second if statement
		when(astMock.hasChildren()).thenReturn(true);
		when(astMock.getText()).thenReturn("+");
		difficultyTest.visitToken(astMock);
		assertIterableEquals(arr, difficultyTest.getOperatorsArray());
		assertEquals(1, difficultyTest.getUniqueOperators());
		
	}
	
	@Test
	public void finishTreeTest() {
		HalsteadDifficultyCheck mockSpy = spy(new HalsteadDifficultyCheck());
		when(astMock.getLineNo()).thenReturn(2);
		
		//test first line
		//Mockito.verify(difficultyTest.getDifficulty()).equals(Mockito.anyDouble());
		
		//test log
		Mockito.doNothing().when((AbstractCheck)mockSpy).log(Mockito.anyInt(),  Mockito.anyString(), Mockito.isA(Object.class));
		mockSpy.finishTree(astMock);
		
	}
}
