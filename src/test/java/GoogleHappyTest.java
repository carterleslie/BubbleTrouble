/******************************************************************************
*  Author : cal17b Carter Leslie, skh16b SK Hall, and arv16a Abi Verhelle
*  Class  : Spring 2020 CS374.01 Dr. Reeves
*  Date   : 5/6/2020
*  Task   : Project 2 - GoogleHappy 
*
*  GoogleHappy
*
******************************************************************************/

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.*;

import java.util.ArrayList;
import java.io.IOException;

import java.io.File; 
import java.util.Scanner;
import java.io.FileNotFoundException;
// import java.io.IndexOutOfBoundsException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.*;
import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.Description;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GoogleHappyTest
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    public void setUpStreams() 
    {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
    }

    public void restoreStreams() 
    {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
    }

    @Rule
    public TestRule watcher = new TestWatcher() 
    {
    	    protected void starting(Description description) 
    	    {
    			System.out.println("Starting test: " + description.getMethodName());
    	    }
    };

    @BeforeClass
    public static void setUpBeforeClass() throws Exception 
    {
    }
    /*
    @Test
    public void ExampleTest() throws IOException 
    {
        this.setUpStreams();

        File inputFile = new File("adjacencyTest.txt"); //put file name here that you want to input from
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
    	
        // now call main
        //GoogleHappy.main( new String[] {"Yes", "v", "4"} ); //sends in these variables to args of the main
        GoogleHappy test = new GoogleHappy(); //creating a GoogleHappy
    	
        // reset back to normal stdin, anything you print now will show up when running mvn test
        System.setIn(System.in);
        this.restoreStreams();

    	test.printAdjacencyMatrix(); //printing the adjacencyMatrix of test, this only works if after the prev 2 lines

        String outs = outContent.toString();  
        System.out.println(outs);            
        //These two lines print the output which was output by the GoogleHappy test,
        //it doesn't need to be in every/shouldn't be in most test cases. Right now it prints nothing
        //but eventually it will. Feel free to remove these for now            
        
    } 

   @Test
    public void TestZeroPreferences() throws IOException 
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
    	
        GoogleHappy test = new GoogleHappy(3,0);
    	
        System.setIn(System.in);
        this.restoreStreams();

    	test.printAdjacencyMatrix();

    	for (int i = 0; i < 12; i++) 
    	{
    		assertEquals(0, test.getAdjacencyMatrixIndex(6, i));
    	}       
    }

    @Test
    public void TestDiagonalZeros() throws IOException 
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(3,0);
        
        System.setIn(System.in);
        this.restoreStreams();

        test.printAdjacencyMatrix();

        for (int i = 0; i < 12; i++) 
        {
            assertEquals(0, test.getAdjacencyMatrixIndex(i, i));
        }
    }

    @Test(expected= AssertionError.class)
    public void TestFailDiagonalZeros() throws IOException 
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(3,0);
        
        System.setIn(System.in);
        this.restoreStreams();

        test.printAdjacencyMatrix();

        assertEquals(1, test.getAdjacencyMatrixIndex(3, 3));       
    }

    @Test
    public void TestCorretnessOfMatrix() throws IOException 
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(3,0);
        
        System.setIn(System.in);
        this.restoreStreams();

        test.printAdjacencyMatrix();

        int val = test.getAdjacencyMatrixIndex(6, 4);
        int ans = 5;
        int val2 = test.getAdjacencyMatrixIndex(1, 2);
        int ans2 = 3;
        int val3 = test.getAdjacencyMatrixIndex(5, 0);
        int ans3 = 0;
        assertEquals(ans,val);
        assertEquals(ans2,val2);
        assertEquals(ans3,val3);             
    }

    @Test
    public void TestCorretnessOfMatrix2() throws IOException 
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt"); 
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(3,0);

        System.setIn(System.in);
        this.restoreStreams();

        test.printAdjacencyMatrix();

        int val = test.getAdjacencyMatrixIndex(7, 9);
        int ans = 0;
        int val2 = test.getAdjacencyMatrixIndex(0, 1);
        int ans2 = 4;
        int val3 = test.getAdjacencyMatrixIndex(4, 5);
        int ans3 = 0;
        assertEquals(ans,val);
        assertEquals(ans2,val2);
        assertEquals(ans3,val3);   
    }

    @Test(expected= AssertionError.class)
    public void TestCorretnessOfMatrixFail() throws IOException 
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);

        GoogleHappy test = new GoogleHappy(3,0);

        System.setIn(System.in);
        this.restoreStreams();

        test.printAdjacencyMatrix();

        int val = test.getAdjacencyMatrixIndex(0, 2);
        int ans = 7;
        assertEquals(ans,val);    
   }

    @Test(expected= IndexOutOfBoundsException.class) 
    public void TestRange() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("bubbleTest2.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(3,0);
        
        System.setIn(System.in);
        this.restoreStreams();

        test.printAdjacencyMatrix();

        int val = test.getAdjacencyMatrixIndex(0, 12);
        int ans = 0; 
        assertEquals(ans,val);   
    }

   @Test(expected= IndexOutOfBoundsException.class) 
    public void TestRange2() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("bubbleTest2.txt"); 
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(3,0);

        System.setIn(System.in);
        this.restoreStreams();

        test.printAdjacencyMatrix();

        int val = test.getAdjacencyMatrixIndex(1, 12);
        int ans = 0; 
        assertEquals(ans,val);        
    }

    @Test
    public void TestUnpopularity() throws IOException 
    {
        this.setUpStreams();

        File inputFile = new File("bubbleTest2.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);

        GoogleHappy test = new GoogleHappy(3,0);
        
        System.setIn(System.in);
        this.restoreStreams();

        test.printAdjacencyMatrix();

        for (int i = 0; i < 5; i++) 
        {
            assertEquals(0, test.getAdjacencyMatrixIndex(i, 2));
        }
    }

    @Test
    public void TestTeamSize() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt"); 
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);

        GoogleHappy test = new GoogleHappy(3,0);

        System.setIn(System.in);
        this.restoreStreams();

        test.printAdjacencyMatrix();

        int val = test.getTeamSize();
        int ans = 3; 
        assertEquals(ans,val);         
    }

    @Test
    public void TestFinalTotalPageRank1() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("pageRankTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,0); 

        System.setIn(System.in);
        this.restoreStreams();

        double val = (test.getPageRank()).getTotalPageRank();
        int ans = 1; 
        assertTrue(Math.abs(Math.abs(val)-Math.abs(ans)) < .0001);

        String outs = outContent.toString();
        System.out.println(outs);
    }

    @Test
    public void TestFinalTotalPageRank2() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("adjacencyTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,0); 

        System.setIn(System.in);
        this.restoreStreams();

        double val = (test.getPageRank()).getTotalPageRank();
        int ans = 1; 
        assertTrue(Math.abs(Math.abs(val)-Math.abs(ans)) < .0001);

        String outs = outContent.toString();
        System.out.println(outs);
    }

    @Test
    public void TestFinalTotalPageRankLessThanOne1() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,0);

        System.setIn(System.in);
        this.restoreStreams();

        double val = (test.getPageRank()).getTotalPageRank();
        double ans = .83333333; 
        assertTrue(Math.abs(Math.abs(val)-Math.abs(ans)) < .0001);

        String outs = outContent.toString();
        System.out.println(outs);
    }

    @Test
    public void TestFinalTotalPageRankLessThanOne2() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,0); 

        System.setIn(System.in);
        this.restoreStreams();

        double val = (test.getPageRank()).getTotalPageRank();
        double ans = .83928571; 
        assertTrue(Math.abs(Math.abs(val)-Math.abs(ans)) < .0001);

        String outs = outContent.toString();
        System.out.println(outs);
    }

    @Test(expected= AssertionError.class)
    public void TestFinalTotalPageRankLessThanOneFails() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,0);

        System.setIn(System.in);
        this.restoreStreams();

        double val = (test.getPageRank()).getTotalPageRank();
        int ans = 1; 
        assertTrue(Math.abs(Math.abs(val)-Math.abs(ans)) < .0001);

        String outs = outContent.toString();
        System.out.println(outs);
    }

    @Test(expected= AssertionError.class)
    public void TestFinalTotalPageRankFails() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("adjacencyTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,0);

        System.setIn(System.in);
        this.restoreStreams();

        double val = (test.getPageRank()).getTotalPageRank(); 
        double ans = .85; 
        assertTrue(Math.abs(Math.abs(val)-Math.abs(ans)) < .0001); 

        String outs = outContent.toString();
        System.out.println(outs);
    }

    @Test
    public void TestFinalIndividualPageRank1() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt"); 
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,0); 
        
        System.setIn(System.in);
        this.restoreStreams();

        double val1 = (test.getPageRank()).getPageRank(0); 
        double ans1 = 0.18205215; 
        assertTrue(Math.abs(Math.abs(val1)-Math.abs(ans1)) < .0001);

        double val2 = (test.getPageRank()).getPageRank(4);
        double ans2 = 0.06004535; 
        assertTrue(Math.abs(Math.abs(val2)-Math.abs(ans2)) < .0001);

        double val3 = (test.getPageRank()).getPageRank(9);
        double ans3 = 0.0068027; 
        assertTrue(Math.abs(Math.abs(val3)-Math.abs(ans3)) < .0001);

        String outs = outContent.toString();
        System.out.println(outs);
    }

    @Test
    public void TestFinalIndividualPageRank2() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("adjacencyTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,0); 
        
        System.setIn(System.in);
        this.restoreStreams();

        double val1 = (test.getPageRank()).getPageRank(0); 
        double ans1 = 0.0; 
        assertTrue(Math.abs(Math.abs(val1)-Math.abs(ans1)) < .0001); 

        double val2 = (test.getPageRank()).getPageRank(4);
        double ans2 = 0.2450617; 
        assertTrue(Math.abs(Math.abs(val2)-Math.abs(ans2)) < .0001);

        double val3 = (test.getPageRank()).getPageRank(8);
        double ans3 = 0.124691358; 
        assertTrue(Math.abs(Math.abs(val3)-Math.abs(ans3)) < .0001);

        String outs = outContent.toString();  
        System.out.println(outs);
    }

    @Test
    public void TestFinalIndividualPageRank3() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,0);

        System.setIn(System.in);
        this.restoreStreams();

        double val1 = (test.getPageRank()).getPageRank(0); 
        double ans1 = 0.0897392; 
        assertTrue(Math.abs(Math.abs(val1)-Math.abs(ans1)) < .0001); 

        double val2 = (test.getPageRank()).getPageRank(3);
        double ans2 = 0.03248299; 
        assertTrue(Math.abs(Math.abs(val2)-Math.abs(ans2)) < .0001);

        double val3 = (test.getPageRank()).getPageRank(5);
        double ans3 = 0.046003401; 
        assertTrue(Math.abs(Math.abs(val3)-Math.abs(ans3)) < .0001);

        String outs = outContent.toString();
        System.out.println(outs);
    }

    @Test(expected= AssertionError.class)
    public void TestFinalIndividualPageRankFails() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,0); 
        
        System.setIn(System.in);
        this.restoreStreams();

        double val1 = (test.getPageRank()).getPageRank(0); 
        double ans1 = 0.083333333; 
        assertTrue(Math.abs(Math.abs(val1)-Math.abs(ans1)) < .0001); 

        String outs = outContent.toString();  
        System.out.println(outs);
    }*/

    @Test
    public void Testverbose1() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,0); 
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getVerboseLevel(); 
        int ans1 = 0; 
        assertEquals(ans1,val1); 

        String outs = outContent.toString();  
        System.out.println(outs);
    }

    @Test
    public void Testverbose2() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,1); 
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getVerboseLevel(); 
        int ans1 = 1; 
        assertEquals(ans1,val1); 

        String outs = outContent.toString();  
        System.out.println(outs);
    }

    @Test
    public void Testverbose3() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,2); 
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getVerboseLevel(); 
        int ans1 = 2; 
        assertEquals(ans1,val1); 

        String outs = outContent.toString();  
        System.out.println(outs);
    }

    @Test
    public void Testverbose4() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,3); 
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getVerboseLevel(); 
        int ans1 = 3; 
        assertEquals(ans1,val1); 

        String outs = outContent.toString();  
        System.out.println(outs);
    }

    @Test
    public void Testverbose5() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,4); 
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getVerboseLevel(); 
        int ans1 = 4; 
        assertEquals(ans1,val1); 

        String outs = outContent.toString();  
        System.out.println(outs);
    }

    

    @Test (expected= AssertionError.class)
    public void Testverbose6() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("labRatsTest.txt");
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        GoogleHappy test = new GoogleHappy(2,4); 
        
        System.setIn(System.in);
        this.restoreStreams();

        int val1 = test.getVerboseLevel(); 
        int ans1 = 5; 
        assertEquals(ans1,val1); 

        String outs = outContent.toString();  
        System.out.println(outs);
    }



}