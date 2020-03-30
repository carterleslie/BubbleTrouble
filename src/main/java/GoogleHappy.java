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
import java.io.File; 
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class GoogleHappy 
{
	private int teamSize;
	private int numPeople;
	private int[][] adjacencyMatrix;
	private String[] names;
	private String[][] preferences;
	public GoogleHappy()
	{
		Scanner scanner = new Scanner(System.in);
    	teamSize = scanner.nextInt();
    	numPeople = scanner.nextInt();
    	adjacencyMatrix = new int[numPeople][numPeople];
    	names = new String[numPeople];
    	preferences = new String[numPeople][numPeople];
		for(int r = 0; r < numPeople; r++)
		{
			for(int c = 0; c < numPeople; c++)
			{
				adjacencyMatrix[r][c] = scanner.nextInt();
			}
		}
		for(int r = 0; r < numPeople; r++)
		{
			int i = 1;
			String personAndPrefs = scanner.next();
			String people[] = personAndPrefs.split(",");
			names[r] = people[0];
			for(int c = 0; c < numPeople; c++)
			{
				if(adjacencyMatrix[r][c] == 1)
				{
					preferences[r][c] = people[i++];
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
	public static void main( String[] args )
    {
		GoogleHappy test = new GoogleHappy();
		test.printAdjacencyMatrix();
	}
}