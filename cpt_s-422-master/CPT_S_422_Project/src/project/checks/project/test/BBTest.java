package project.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import project.checks.*;
//import sample_code.*;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BBTest {

	//test if operands are counted multiple times for their place in the tree
	@Test
	public void operatorDupTest() throws CheckstyleException, IOException {
		OperandCheck check = new OperandCheck();
		TestDriver td = new TestDriver("src/blackboxfiles/OperatorSample.java", check);
		td.runCheck();
		assertEquals(7, check.getNumOps());
	}
	
	//test if bitwise ops like &&, || etc count twice
	@Test
	public void operatorBWTest() throws CheckstyleException, IOException {
		OperatorCheck check = new OperatorCheck();
		TestDriver td = new TestDriver("src/blackboxfiles/OperatorSample2.java", check);
		td.runCheck();
		assertEquals(11, check.getNumOps());
	}
	
	//test if post ops like ++ and -- count twice
	//also tests if operators in comments are counted
	@Test
	public void operatorPOSTTest() throws CheckstyleException, IOException {
		OperatorCheck check = new OperatorCheck();
		TestDriver td = new TestDriver("src/blackboxfiles/OperatorSample3.java", check);
		td.runCheck();
		assertEquals(15, check.getNumOps());
	}
	
	//different body indicators; with and without stars for block comments
	@Test
	public void commentNumBodyTest() throws CheckstyleException, IOException {
		NumberOfCommentsCheck check = new NumberOfCommentsCheck();
		TestDriver td = new TestDriver("src/blackboxfiles/CommentSample.java", check);
		td.runCheck();
		assertEquals(3, check.getNumComment());
	}
	
	//checking single line
	@Test
	public void commentNumSingleTest() throws CheckstyleException, IOException {
		NumberOfCommentsCheck check = new NumberOfCommentsCheck();
		TestDriver td = new TestDriver("src/blackboxfiles/CommentSample2.java", check);
		td.runCheck();
		assertEquals(1, check.getNumComment());
	}
	
	//testing all types
	@Test
	public void commentNumTypesTest() throws CheckstyleException, IOException {
		NumberOfCommentsCheck check = new NumberOfCommentsCheck();
		TestDriver td = new TestDriver("src/blackboxfiles/CommentSample3.java", check);
		td.runCheck();
		assertEquals(7, check.getNumComment());
	}
	
	//different body indicators; with and without stars for block comments
	@Test
	public void commentLineBodyTest() throws CheckstyleException, IOException {
		LinesOfCommentsCheck check = new LinesOfCommentsCheck();
		TestDriver td = new TestDriver("src/blackboxfiles/CommentSample.java", check);
		td.runCheck();
		assertEquals(7, check.getNumComment());
	}
	
	//checking single line
	@Test
	public void commentLineSingleTest() throws CheckstyleException, IOException {
		LinesOfCommentsCheck check = new LinesOfCommentsCheck();
		TestDriver td = new TestDriver("src/blackboxfiles/CommentSample2.java", check);
		td.runCheck();
		assertEquals(1, check.getNumComment());
	}
	
	//testing all types
	@Test
	public void commentLineTypesTest() throws CheckstyleException, IOException {
		LinesOfCommentsCheck check = new LinesOfCommentsCheck();
		TestDriver td = new TestDriver("src/blackboxfiles/CommentSample3.java", check);
		td.runCheck();
		assertEquals(16, check.getNumComment());
	}
	
	//makes sure loops types are all counted
	@Test
	public void loopTypesTest() throws CheckstyleException, IOException {
		LoopingStatementsCheck check = new LoopingStatementsCheck();
		TestDriver td = new TestDriver("src/blackboxfiles/LoopSample2.java", check);
		td.runCheck();
		assertEquals(2, check.getNumLoops());
	}
	
	//make sure nested loops are counted
	@Test
	public void loopTest() throws CheckstyleException, IOException {
		LoopingStatementsCheck check = new LoopingStatementsCheck();
		TestDriver td = new TestDriver("src/blackboxfiles/LoopSample.java", check);
		td.runCheck();
		assertEquals(2, check.getNumLoops());
	}
	
	//test if math is right
	@Test
	public void halsteadMathTest() throws CheckstyleException, IOException {
		HalsteadEffortCheck check = new HalsteadEffortCheck();
		TestDriver td = new TestDriver("src/blackboxfiles/HalsteadSample.java", check);
		td.runCheck();
		assertEquals(60, check.getEffort());
	}
	
	//test to see if duplicate operators are added to the arraylist
	@Test
	public void halsteadDupOperatorsTest() throws CheckstyleException, IOException {
		HalsteadEffortCheck check = new HalsteadEffortCheck();
		TestDriver td = new TestDriver("src/blackboxfiles/HalsteadSample2.java", check);
		td.runCheck();
		assertEquals(2, check.getOperatorsArray().size());
	}
	
	//test to see if duplicate operands are added to the arraylist
	@Test
	public void halsteadDupOperandsTest() throws CheckstyleException, IOException {
		HalsteadEffortCheck check = new HalsteadEffortCheck();
		TestDriver td = new TestDriver("src/blackboxfiles/HalsteadSample3.java", check);
		td.runCheck();
		assertEquals(3, check.getOperandsArray().size());
	}
	
	
	
}
