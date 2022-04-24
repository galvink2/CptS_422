package project.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import project.checks.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultContext;
import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;


public class TestDriver {
	
	String fp;
	AbstractCheck check;
	ArrayList<Integer> tokens = new ArrayList<Integer>();
	
	public TestDriver(String filepath, AbstractCheck check)
	{
		this.fp = filepath;
		this.check = check;
	}
	
	public void runCheck() throws CheckstyleException, IOException
	{
		File file = new File(fp);
        FileText ft = new FileText(file,"UTF-8");
        FileContents fc = new FileContents(ft);
        DetailAST root = JavaParser.parse(fc);
        int[] list = check.getAcceptableTokens();
        for(int i = 0; i < check.getAcceptableTokens().length; i++)
        {
        	this.tokens.add(list[i]);
        }
        //System.out.print(this.tokens); 
        check.beginTree(root);
        helper(check,root);
	}
	
    public void helper(AbstractCheck b, DetailAST a) {
        while(a != null) {
            if(this.tokens.contains(a.getType()))
            {
            	//System.out.print(a.getText() + " "); 
            	b.visitToken(a);
            }
            helper(b,a.getFirstChild());
            a = a.getNextSibling();
        }
    }

}
