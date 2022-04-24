package project.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import com.puppycrawl.tools.checkstyle.api.*;
import com.puppycrawl.tools.checkstyle.checks.javadoc.AbstractJavadocCheck;

import project.checks.HalsteadDifficultyCheck;
import project.checks.HalsteadEffortCheck;
import project.checks.HalsteadVocabularyCheck;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HalsteadVocabularyCheckTest {
	
	HalsteadVocabularyCheck vocabTest = new HalsteadVocabularyCheck();
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
		int[] nums2 = vocabTest.getAcceptableTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getRequiredTokensTest() {
		int[] nums = new int[0];
		int[] nums2 = vocabTest.getRequiredTokens();
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
		int[] nums2 = vocabTest.getDefaultTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getVocabTest() {
		assertEquals(0, vocabTest.getVocab());
	}
	
	@Test
	public void getUniquesTest() {
		ArrayList<String> arr = new ArrayList<String>();
		assertIterableEquals(arr, vocabTest.getUniques());
	}
	
	@Test
	public void beginTreeTest() {
		ArrayList<String> arr = new ArrayList<String>();

		vocabTest.beginTree(astMock);
		assertEquals(0, vocabTest.getVocab());
		assertIterableEquals(arr, vocabTest.getUniques());
	}
	
	@Test
	public void visitTokenTest() {
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("+");
		
		//parent mock
		DetailAST astParent = mock(DetailAST.class);
		when(astMock.getParent()).thenReturn(astParent);
		
		//first if statement
		when(astMock.getText()).thenReturn("+");
		
		//first if
		when(astMock.hasChildren()).thenReturn(true);
		vocabTest.visitToken(astMock);
		assertEquals(1, vocabTest.getVocab());
		assertIterableEquals(arr, vocabTest.getUniques());
		
		//else if
		//update to a different operator so it'll run again
		when(astMock.getText()).thenReturn("-");
		arr.add("-");
		when(astMock.hasChildren()).thenReturn(false);
		when(astParent.getText()).thenReturn("+");
		vocabTest.visitToken(astMock);
		//this will be 2 because it was called earlier
		assertEquals(2, vocabTest.getVocab());
		assertIterableEquals(arr, vocabTest.getUniques());
		
	}

	@Test
	public void finishTreeTest() {
		HalsteadVocabularyCheck mockSpy = spy(new HalsteadVocabularyCheck());
		when(astMock.getLineNo()).thenReturn(2);
		
		//test log
		Mockito.doNothing().when((AbstractCheck)mockSpy).log(Mockito.anyInt(),  Mockito.anyString(), Mockito.isA(Object.class));
		mockSpy.finishTree(astMock);
		
	}

}
