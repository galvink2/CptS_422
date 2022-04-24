package project.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import com.puppycrawl.tools.checkstyle.api.*;
import com.puppycrawl.tools.checkstyle.checks.javadoc.AbstractJavadocCheck;

import project.checks.HalsteadDifficultyCheck;
import project.checks.HalsteadEffortCheck;
import project.checks.HalsteadVolumeCheck;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HalsteadVolumeCheckTest {
	
	HalsteadVolumeCheck volumeTest = new HalsteadVolumeCheck();
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
		int[] nums2 = volumeTest.getAcceptableTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getRequiredTokensTest() {
		int[] nums = new int[0];
		int[] nums2 = volumeTest.getRequiredTokens();
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
		int[] nums2 = volumeTest.getDefaultTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getVocabTest() {
		assertEquals(0, volumeTest.getVocab());
	}
	
	@Test
	public void getLengthTest() {
		assertEquals(0, volumeTest.getLength());
	}
	
	@Test
	public void getVolumeTest() {
		assertEquals(0, volumeTest.getVolume());
	}
	
	@Test
	public void getUniquesTest() {
		ArrayList<String> arr = new ArrayList<String>();
		assertIterableEquals(arr, volumeTest.getUniques());
	}
	
	@Test
	public void beginTreeTest() {
		ArrayList<String> arr = new ArrayList<String>();
		
		volumeTest.beginTree(astMock);
		assertEquals(0, volumeTest.getVocab());
		assertIterableEquals(arr, volumeTest.getUniques());
		assertEquals(0, volumeTest.getLength());
		assertEquals(0, volumeTest.getVolume());
	}
	
	@Test
	public void visitTokenTest() {
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("+");
		
		//parent mock
		DetailAST astParent = mock(DetailAST.class);
		when(astMock.getParent()).thenReturn(astParent);
		
		//is an operator
		when(astMock.hasChildren()).thenReturn(true);
		when(astMock.getText()).thenReturn("+");
		volumeTest.visitToken(astMock);
		assertEquals(1, volumeTest.getVocab());
		assertIterableEquals(arr, volumeTest.getUniques());
		assertEquals(1, volumeTest.getLength());
		
		//is an operand
		when(astMock.hasChildren()).thenReturn(false);
		//add new operator to let it run again
		when(astMock.getText()).thenReturn("-");
		arr.add("-");
		when(astParent.getText()).thenReturn("+");
		//these are 2 because they were called earlier
		volumeTest.visitToken(astMock);
		assertEquals(2, volumeTest.getVocab());
		assertIterableEquals(arr, volumeTest.getUniques());
		assertEquals(2, volumeTest.getLength());
	}
	
	@Test
	public void log2Test() {
		double num = volumeTest.log2(1.0);
		assertEquals(num, 0, 0.001);
	}

	@Test
	public void finishTreeTest() {
		HalsteadVolumeCheck mockSpy = spy(new HalsteadVolumeCheck());
		when(astMock.getLineNo()).thenReturn(2);
		
		//test log
		Mockito.doNothing().when((AbstractCheck)mockSpy).log(Mockito.anyInt(),  Mockito.anyString(), Mockito.isA(Object.class));
		mockSpy.finishTree(astMock);
	}

}
