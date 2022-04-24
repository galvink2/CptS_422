package project.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.puppycrawl.tools.checkstyle.api.*;
import com.puppycrawl.tools.checkstyle.checks.javadoc.AbstractJavadocCheck;

import project.checks.HalsteadDifficultyCheck;
import project.checks.HalsteadEffortCheck;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HalsteadEffortCheckTest {

	HalsteadEffortCheck effortTest = new HalsteadEffortCheck();
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
		int[] nums2 = effortTest.getAcceptableTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getRequiredTokensTest() {
		int[] nums = new int[0];
		int[] nums2 = effortTest.getRequiredTokens();
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
		int[] nums2 = effortTest.getDefaultTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getTotalOpsTest() {
		assertEquals(0, effortTest.getTotalOps());
	}
	
	@Test
	public void getUniqueOperandsTest() {
		assertEquals(0, effortTest.getUniqueOperands());
	}
	
	@Test
	public void getUniqueOperatorsTest() {
		assertEquals(0, effortTest.getUniqueOperators());
	}
	
	@Test
	public void getDifficultyTest() {
		assertEquals(0, effortTest.getDifficulty());
	}
	
	@Test
	public void getOperandsArrayTest() {
		ArrayList<String> arr = new ArrayList<String>();
		assertIterableEquals(arr, effortTest.getOperandsArray());
	}
	
	@Test
	public void getOperatorsArrayTest() {
		ArrayList<String> arr = new ArrayList<String>();
		assertIterableEquals(arr, effortTest.getOperatorsArray());
	}
	
	@Test
	public void getVocabTest() {
		assertEquals(0, effortTest.getVocab());
	}
	
	@Test
	public void getLengthTest() {
		assertEquals(0, effortTest.getLength());
	}
	
	@Test
	public void getVolumeTest() {
		assertEquals(0, effortTest.getVolume());
	}
	
	@Test
	public void getEffortTest() {
		assertEquals(0, effortTest.getEffort());
	}
	
	@Test
	public void beginTreeTest() {
		ArrayList<String> arr = new ArrayList<String>();

		effortTest.beginTree(astMock);
		assertEquals(0, effortTest.getTotalOps());
		assertEquals(0, effortTest.getUniqueOperands());
		assertEquals(0, effortTest.getUniqueOperators());
		assertEquals(0, effortTest.getDifficulty());
		assertIterableEquals(arr, effortTest.getOperandsArray());
		assertIterableEquals(arr, effortTest.getOperatorsArray());
		assertEquals(0, effortTest.getLength());
		assertEquals(0, effortTest.getVolume());
		assertEquals(0, effortTest.getEffort());
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
		
		// first if
		when(astMock.hasChildren()).thenReturn(true);
		when(astMock.getText()).thenReturn("+");
		effortTest.visitToken(astMock);
		assertEquals(1, effortTest.getLength());
		assertIterableEquals(arr, effortTest.getOperatorsArray());
		assertEquals(1, effortTest.getUniqueOperators());
		
		//else if
		when(astMock.hasChildren()).thenReturn(false);
		when(astParent.getText()).thenReturn("+");
		when(astMock.getText()).thenReturn("x");
		effortTest.visitToken(astMock);
		//will be 2 because we've ++'d it once before
		assertEquals(2, effortTest.getLength());
		assertEquals(1, effortTest.getTotalOps());
		assertIterableEquals(arr2, effortTest.getOperandsArray());
		assertEquals(1, effortTest.getUniqueOperands());


	}
	
	@Test
	public void log2Test() {
		double num = effortTest.log2(1.0);
		assertEquals(num, 0, 0.001);
	}
	
	@Test
	public void finishTreeTest() {
		HalsteadEffortCheck mockSpy = spy(new HalsteadEffortCheck());
		when(astMock.getLineNo()).thenReturn(2);
		
		//test log
		Mockito.doNothing().when((AbstractCheck)mockSpy).log(Mockito.anyInt(),  Mockito.anyString(), Mockito.isA(Object.class));
		mockSpy.finishTree(astMock);
		
	}

}
