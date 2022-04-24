package project.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.puppycrawl.tools.checkstyle.api.*;
import com.puppycrawl.tools.checkstyle.checks.javadoc.AbstractJavadocCheck;

import project.checks.LinesOfCommentsCheck;
import project.checks.NumberOfCommentsCheck;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class NumberOfCommentsCheckTest {

	NumberOfCommentsCheck commentTest = new NumberOfCommentsCheck();
	DetailAST astMock = mock(DetailAST.class);

	@Test
	public void getAcceptableTokensTest() {
		//LinesOfCommentsCheck commentMock = mock(LinesOfCommentsCheck.class);
		int[] nums = {TokenTypes.COMMENT_CONTENT};
		int[] nums2 = commentTest.getAcceptableTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getRequiredTokensTest() {
		int[] nums = new int[0];
		int[] nums2 = commentTest.getRequiredTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getDefaultTokensTest() {
		//LinesOfCommentsCheck commentMock = mock(LinesOfCommentsCheck.class);
		int[] nums = {TokenTypes.COMMENT_CONTENT};
		int[] nums2 = commentTest.getDefaultTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void isCommentNodesRequiredTest() {
		assertEquals(true, commentTest.isCommentNodesRequired());
	}
	
	@Test
	public void getNumCommentsTest() {
		assertEquals(0, commentTest.getNumComment());
	}
	
	@Test
	public void beginTreeTest() {
		commentTest.beginTree(astMock);
		assertEquals(0, commentTest.getNumComment());
	}
	
	@Test
	public void visitTokenTest() {
		commentTest.visitToken(astMock);
		assertEquals(1, commentTest.getNumComment());
	}
	
	@Test
	public void finishTreeTest() {
		NumberOfCommentsCheck mockSpy = spy(new NumberOfCommentsCheck());
		
		when(astMock.getLineNo()).thenReturn(2);
		Mockito.doNothing().when((AbstractCheck)mockSpy).log(Mockito.anyInt(),  Mockito.anyString(), Mockito.isA(Object.class));
		mockSpy.finishTree(astMock);
	}

}
