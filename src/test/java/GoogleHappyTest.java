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
import java.io.IndexOutOfBoundsException;
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

    @BeforeClass
    public static void setUpBeforeClass() throws Exception 
    {
    }

    /*@Test
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
    
    
}