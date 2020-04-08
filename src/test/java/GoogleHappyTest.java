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
        
    }*/

   @Test
    public void TestZeroPreferences() throws IOException 
    {
        this.setUpStreams();

        File inputFile = new File("bestTest.txt"); //put file name here that you want to input from
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
    	
        // now call main
        //GoogleHappy.main( new String[] {"Yes", "v", "4"} ); //sends in these variables to args of the main
        GoogleHappy test = new GoogleHappy(); //creating a GoogleHappy
    	
        // reset back to normal stdin, anything you print now will show up when running mvn test
        System.setIn(System.in);
        this.restoreStreams();

    	test.printAdjacencyMatrix(); //printing the adjacencyMatrix of test, this only works if after the prev 2 lines

    	for (int i = 0; i < 12; i++) 
    	{
    		assertEquals(0, test.getAdjacencyMatrixIndex(6, i));
    	}

        String outs = outContent.toString();  
        System.out.println(outs);            
        //These two lines print the output which was output by the GoogleHappy test,
        //it doesn't need to be in every/shouldn't be in most test cases. Right now it prints nothing
        //but eventually it will. Feel free to remove these for now.          
        
    }

    @Test
    public void TestDiagonalZeros() throws IOException 
    {
        this.setUpStreams();

        File inputFile = new File("bestTest.txt"); //put file name here that you want to input from
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        // now call main
        //GoogleHappy.main( new String[] {"Yes", "v", "4"} ); //sends in these variables to args of the main
        GoogleHappy test = new GoogleHappy(); //creating a GoogleHappy
        
        // reset back to normal stdin, anything you print now will show up when running mvn test
        System.setIn(System.in);
        this.restoreStreams();

        test.printAdjacencyMatrix(); //printing the adjacencyMatrix of test, this only works if after the prev 2 lines

        for (int i = 0; i < 12; i++) 
        {
            assertEquals(0, test.getAdjacencyMatrixIndex(i, i));
        }

        String outs = outContent.toString();  
        System.out.println(outs);            
        //These two lines print the output which was output by the GoogleHappy test,
        //it doesn't need to be in every/shouldn't be in most test cases. Right now it prints nothing
        //but eventually it will. Feel free to remove these for now.          
        
    }

    @Test(expected= AssertionError.class)
    public void TestFailDiagonalZeros() throws IOException 
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt"); //put file name here that you want to input from
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        // now call main
        //GoogleHappy.main( new String[] {"Yes", "v", "4"} ); //sends in these variables to args of the main
        GoogleHappy test = new GoogleHappy(); //creating a GoogleHappy
        
        // reset back to normal stdin, anything you print now will show up when running mvn test
        System.setIn(System.in);
        this.restoreStreams();

        test.printAdjacencyMatrix(); //printing the adjacencyMatrix of test, this only works if after the prev 2 lines

        assertEquals(1, test.getAdjacencyMatrixIndex(3, 3));
      
        String outs = outContent.toString();  
        System.out.println(outs);            
        //These two lines print the output which was output by the GoogleHappy test,
        //it doesn't need to be in every/shouldn't be in most test cases. Right now it prints nothing
        //but eventually it will. Feel free to remove these for now.          
        
    }

    @Test
    public void TestCorretnessOfMatrix() throws IOException 
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt"); //put file name here that you want to input from
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        // now call main
        //GoogleHappy.main( new String[] {"Yes", "v", "4"} ); //sends in these variables to args of the main
        GoogleHappy test = new GoogleHappy(); //creating a GoogleHappy
        
        // reset back to normal stdin, anything you print now will show up when running mvn test
        System.setIn(System.in);
        this.restoreStreams();

        test.printAdjacencyMatrix(); //printing the adjacencyMatrix of test, this only works if after the prev 2 lines

        int val = test.getAdjacencyMatrixIndex(6, 4);
        int ans = 5;
        int val2 = test.getAdjacencyMatrixIndex(1, 2);
        int ans2 = 3;
        int val3 = test.getAdjacencyMatrixIndex(5, 0);
        int ans3 = 0;
        assertEquals(ans,val);
        assertEquals(ans2,val2);
        assertEquals(ans3,val3);

        String outs = outContent.toString();  
        System.out.println(outs);            
        //These two lines print the output which was output by the GoogleHappy test,
        //it doesn't need to be in every/shouldn't be in most test cases. Right now it prints nothing
        //but eventually it will. Feel free to remove these for now.          
        
    }

    @Test
    public void TestCorretnessOfMatrix2() throws IOException 
    {
        this.setUpStreams();

        File inputFile = new File("bestTest.txt"); //put file name here that you want to input from
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        // now call main
        //GoogleHappy.main( new String[] {"Yes", "v", "4"} ); //sends in these variables to args of the main
        GoogleHappy test = new GoogleHappy(); //creating a GoogleHappy
        
        // reset back to normal stdin, anything you print now will show up when running mvn test
        System.setIn(System.in);
        this.restoreStreams();

        test.printAdjacencyMatrix(); //printing the adjacencyMatrix of test, this only works if after the prev 2 lines

        int val = test.getAdjacencyMatrixIndex(7, 9);
        int ans = 0;
        int val2 = test.getAdjacencyMatrixIndex(0, 1);
        int ans2 = 4;
        int val3 = test.getAdjacencyMatrixIndex(4, 5);
        int ans3 = 0;
        assertEquals(ans,val);
        assertEquals(ans2,val2);
        assertEquals(ans3,val3);

        String outs = outContent.toString();  
        System.out.println(outs);            
        //These two lines print the output which was output by the GoogleHappy test,
        //it doesn't need to be in every/shouldn't be in most test cases. Right now it prints nothing
        //but eventually it will. Feel free to remove these for now.          
        
    }

    @Test(expected= AssertionError.class)
    public void TestCorretnessOfMatrixFail() throws IOException 
    {
        this.setUpStreams();

        File inputFile = new File("suiteLifeTest.txt"); //put file name here that you want to input from
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        // now call main
        //GoogleHappy.main( new String[] {"Yes", "v", "4"} ); //sends in these variables to args of the main
        GoogleHappy test = new GoogleHappy(); //creating a GoogleHappy
        
        // reset back to normal stdin, anything you print now will show up when running mvn test
        System.setIn(System.in);
        this.restoreStreams();

        test.printAdjacencyMatrix(); //printing the adjacencyMatrix of test, this only works if after the prev 2 lines

        int val = test.getAdjacencyMatrixIndex(0, 2);
        int ans = 7;
        assertEquals(ans,val);

        String outs = outContent.toString();  
        System.out.println(outs);            
        //These two lines print the output which was output by the GoogleHappy test,
        //it doesn't need to be in every/shouldn't be in most test cases. Right now it prints nothing
        //but eventually it will. Feel free to remove these for now.          
        
   }

    @Test(expected= IndexOutOfBoundsException.class) 
    public void TestRange() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("bubbleTest2.txt"); //put file name here that you want to input from
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        // now call main
        //GoogleHappy.main( new String[] {"Yes", "v", "4"} ); //sends in these variables to args of the main
        GoogleHappy test = new GoogleHappy(); //creating a GoogleHappy
        
        // reset back to normal stdin, anything you print now will show up when running mvn test
        System.setIn(System.in);
        this.restoreStreams();

        test.printAdjacencyMatrix(); //printing the adjacencyMatrix of test, this only works if after the prev 2 lines

        int val = test.getAdjacencyMatrixIndex(0, 12);
        int ans = 0; 
        assertEquals(ans,val);

        String outs = outContent.toString();  
        System.out.println(outs);            
        //These two lines print the output which was output by the GoogleHappy test,
        //it doesn't need to be in every/shouldn't be in most test cases. Right now it prints nothing
        //but eventually it will. Feel free to remove these for now.          
        
    }

    //@Test(expected= AssertionError.class) 
   @Test(expected= IndexOutOfBoundsException.class) 
    public void TestRange2() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("bubbleTest2.txt"); //put file name here that you want to input from
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        // now call main
        //GoogleHappy.main( new String[] {"Yes", "v", "4"} ); //sends in these variables to args of the main
        GoogleHappy test = new GoogleHappy(); //creating a GoogleHappy
        
        // reset back to normal stdin, anything you print now will show up when running mvn test
        System.setIn(System.in);
        this.restoreStreams();

        test.printAdjacencyMatrix(); //printing the adjacencyMatrix of test, this only works if after the prev 2 lines

        int val = test.getAdjacencyMatrixIndex(1, 12);
        int ans = 0; 
        assertEquals(ans,val);

        String outs = outContent.toString();  
        System.out.println(outs);            
        //These two lines print the output which was output by the GoogleHappy test,
        //it doesn't need to be in every/shouldn't be in most test cases. Right now it prints nothing
        //but eventually it will. Feel free to remove these for now.          
        
    }

    @Test
    public void TestUnpopularity() throws IOException 
    {
        this.setUpStreams();

        File inputFile = new File("bubbleTest2.txt"); //put file name here that you want to input from
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        // now call main
        //GoogleHappy.main( new String[] {"Yes", "v", "4"} ); //sends in these variables to args of the main
        GoogleHappy test = new GoogleHappy(); //creating a GoogleHappy
        
        // reset back to normal stdin, anything you print now will show up when running mvn test
        System.setIn(System.in);
        this.restoreStreams();

        test.printAdjacencyMatrix(); //printing the adjacencyMatrix of test, this only works if after the prev 2 lines

        for (int i = 0; i < 5; i++) 
        {
            assertEquals(0, test.getAdjacencyMatrixIndex(i, 2));
        }

        String outs = outContent.toString();  
        System.out.println(outs);
        //These two lines print the output which was output by the GoogleHappy test,
        //it doesn't need to be in every/shouldn't be in most test cases. Right now it prints nothing
        //but eventually it will. Feel free to remove these for now.          
        
    }

    //@Test(expected= AssertionError.class) 
   @Test
    public void TestTeamSize() throws IOException
    {
        this.setUpStreams();

        File inputFile = new File("bubbleTest2.txt"); //put file name here that you want to input from
        
        InputStream targetStream = new FileInputStream(inputFile);
        System.setIn(targetStream);
        
        // now call main
        //GoogleHappy.main( new String[] {"Yes", "v", "4"} ); //sends in these variables to args of the main
        GoogleHappy test = new GoogleHappy(); //creating a GoogleHappy
        
        // reset back to normal stdin, anything you print now will show up when running mvn test
        System.setIn(System.in);
        this.restoreStreams();

        test.printAdjacencyMatrix(); //printing the adjacencyMatrix of test, this only works if after the prev 2 lines

        int val = test.getNumPeople();
        int ans = 5; 
        assertEquals(ans,val);// is this right?? Where is val coming from?? 

        String outs = outContent.toString();  
        System.out.println(outs);            
        //These two lines print the output which was output by the GoogleHappy test,
        //it doesn't need to be in every/shouldn't be in most test cases. Right now it prints nothing
        //but eventually it will. Feel free to remove these for now.          
        
    }


}