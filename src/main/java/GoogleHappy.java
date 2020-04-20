/******************************************************************************
*  Author : cal17b Carter Leslie, skh16b SK Hall, and arv16a Abi Verhelle
*  Class  : Spring 2020 CS374.01 Dr. Reeves
*  Date   : 5/6/2020
*  Task   : Project 2 - GoogleHappy
*
*  GoogleHappy
*
******************************************************************************/

import java.util.*;
import java.util.Collection;
import java.io.File; 
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class GoogleHappy 
{
	private int teamSize;
	private int verbose;
	private int numPeople;
	private int[][] adjacencyMatrix;
	private String[] names;
	private String[] preferences;
	private PageRank p;
	private double pageranks;

	public GoogleHappy(int tSize, int vLevel)
	{	
		if(vLevel <= 4 && vLevel >= 0)
    		verbose = vLevel;
    	else if (vLevel < 0)
    		verbose = 0;
    	else
			verbose = 4;
		Scanner scanner = new Scanner(System.in);
    	numPeople = 0;
    	String allNamesAndPrefs = "";
		while(scanner.hasNext())
		{
			allNamesAndPrefs = allNamesAndPrefs + scanner.next() + " ";
			numPeople++;
		}
		if(tSize >= 2 && tSize <= numPeople/2)
    		teamSize = tSize;
    	else if(tSize < 2)
    		teamSize = 2;
    	else
    		teamSize = numPeople/2;
		adjacencyMatrix = new int[numPeople][numPeople];
		names = new String[numPeople];
		preferences = new String[numPeople];
		String nameAndPrefs[] = allNamesAndPrefs.split(" ");
		for(int i = 0; i < numPeople; i++)
		{
			String currPerson[] = nameAndPrefs[i].split(",",2);
			names[i] = currPerson[0];
			if(currPerson.length > 1)
	     		preferences[i] = currPerson[1];
            else
	     		preferences[i] = "";
		}
		scanner.close();
		fillAdjacencyMatrix();
		p = new PageRank(adjacencyMatrix, numPeople);
	}

	public void fillAdjacencyMatrix()
	{
		int prefNum;
		String[] prefs;
		for(int r = 0; r < numPeople; r++)
		{
			for(int c = 0; c < numPeople; c++)
				adjacencyMatrix[r][c] = 0;
			prefs = preferences[r].split(",");
			prefNum = prefs.length;
			for(int i = 0; i < prefs.length; i++)
			{
				for(int c = 0; c < numPeople; c++)
				{
					if(prefs[i].equals(names[c]) && c != r)
					{
						adjacencyMatrix[r][c] = prefNum--;
					}
				}
			}
		}
	}
	public void printAdjacencyMatrix()
	{
		for(int r = 0; r < numPeople; r++)
		{
			for(int c = 0; c < numPeople; c++)
			{
				System.out.print(adjacencyMatrix[r][c] + " ");
			}
			System.out.println("");
		}
	}
	public int getAdjacencyMatrixIndex(int r, int c)
	{
		return adjacencyMatrix[r][c];
	}
	public int getNumPeople()
	{
		return numPeople;
	}
	public int getTeamSize()
	{
		return teamSize;
	}
	public int getVerboseLevel()
	{
		return verbose;
	}
	public PageRank getPageRank()
	{
		return p;
	}
	public static void main( String[] args )
    {
    	int t = 3;
        int v = 0;
    	if (args.length > 0)//parses in all the commandline args and handles setting the 
    	{                   //numbers they correspond to accordingly 
            for (int i = 0; i < args.length; i++) 
            {
            	if(args[i].equals("-t"))
            	{
            		i++;
            		if(i < args.length && !args[i].equals("-t") && !args[i].equals("-v"))
            			t = Integer.parseInt(args[i]);
            		else
            			i--;
            	}
            	if(args[i].equals("-v"))
            	{
            		i++;
            		if(i < args.length && !args[i].equals("-t") && !args[i].equals("-v"))
            			v = Integer.parseInt(args[i]);
            		else
            			i--;
            	}
            }
        } 
		GoogleHappy test = new GoogleHappy(t, v);
		//test.printAdjacencyMatrix();
	}
}