package project.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.puppycrawl.tools.checkstyle.api.*;

import project.checks.ExpressionCheck;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ExpressionCheckTest {
	
	ExpressionCheck expTest = new ExpressionCheck();
	DetailAST astMock = mock(DetailAST.class);
	
	@Test
	public void getAcceptableTokensTest() {
		int[] nums = { TokenTypes.EXPR };
		int[] nums2 = expTest.getAcceptableTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getRequiredTokensTest() {
		int[] nums = new int[0];
		int[] nums2 = expTest.getRequiredTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getDefaultTokensTest() {
		int[] nums = { TokenTypes.EXPR };
		int[] nums2 = expTest.getDefaultTokens();
		assertArrayEquals(nums, nums2);
	}
	
	@Test
	public void getNumExpTest() {
		assertEquals(0, expTest.getNumExp());
	}
	
	@Test
	public void beginTreeTest() {
		expTest.beginTree(astMock);
		assertEquals(0, expTest.getNumExp());
	}
	
	@Test
	public void visitTokenTest() {
		expTest.visitToken(astMock);
		assertEquals(1, expTest.getNumExp());
	}
	
	@Test
	public void finishTreeTest() {
		ExpressionCheck mockSpy = spy(new ExpressionCheck());
		
		when(astMock.getLineNo()).thenReturn(2);
		Mockito.doNothing().when((AbstractCheck)mockSpy).log(Mockito.anyInt(),  Mockito.anyString(), Mockito.isA(Object.class));
		mockSpy.finishTree(astMock);
	}

}
