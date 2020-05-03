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
import java.lang.Math;
import java.util.Collection;
import java.io.File; 
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class GoogleHappy 
{
	private int teamSize; //the size teams need to be
	private int verbose; //the verbose level
	private int powerKey; //the power key
	private char sequenceKey; //the sequence key
	private int numPeople; //the number of people
	private int numTeams; //the number of teams
	private String[][] teamsMatrix; //holds the actual teams, people on column c are on the same team
	private int[][] individualHappinessMatrix; //holds the happiness of person teamsMatrix[r][c] in it's own [r][c] position
	private int[] teamHappiness; //holds total team happiness of team teamsMatrix[r][0-teamSize] in it's own [r] position
	private int totalHappiness; //the total happiness of the teams
	private int[][] adjacencyMatrix; //holds the preferences for person r at adjacencyMatrix[r][0-numPeople]
	private int[][] pagerankMatrix; //the matrix used for storing the values for use in pagerank calculation, either polynomial or sequential
	private String[] names; //holds all the names of the people in the order they are read in
	private int[] maxPrefs;//holds the max number of prefs for each person in names, parallel to names
	private String[] preferences; //holds preferences of a person for a short time
	private PageRank p; //the pagerank containing the code for a pagerank
	private Set<String> usedNames; //a set used to hold all the names that have already been added to a team
	private int[] happinessIndex; //index of happiness to be added to their happiness
	private int[] choicesGot; //the number of {1st choices, 2nd choices,.., not one of their choices}

	public GoogleHappy(int tSize, int vLevel, int pow, char seq) //creates a GoogleHappy
	{	
		if(vLevel <= 4 && vLevel >= 0)
    		verbose = vLevel;
    	else if (vLevel < 0)
    		verbose = 0;
    	else
			verbose = 4;
		if(pow < 1)
			powerKey = 1;
		else
			powerKey = pow;
		sequenceKey = seq;
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
    	if(numPeople % teamSize == 0)
    		numTeams = numPeople / teamSize;
    	else
    		numTeams = numPeople / teamSize + 1;
    	teamsMatrix = new String[teamSize][numTeams];
    	individualHappinessMatrix = new int[teamSize][numTeams];
    	teamHappiness = new int[numTeams];
		adjacencyMatrix = new int[numPeople][numPeople];
		pagerankMatrix = new int[numPeople][numPeople];
		happinessIndex = new int[] {6,5,4,3,2,1};
		choicesGot = new int[] {0,0,0,0,0,0,0};
		names = new String[numPeople];
		maxPrefs = new int[numPeople];
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
		fillPagerankMatrix();
		p = new PageRank(pagerankMatrix, numPeople);
		usedNames = new HashSet<String>();
		createTeams();
		calcAllHappiness();
	}
	public void fillAdjacencyMatrix() //fills adjacency matrix
	{
		int prefNum;
		String[] prefs;
		for(int r = 0; r < numPeople; r++)
		{
			for(int c = 0; c < numPeople; c++)
				adjacencyMatrix[r][c] = 0;
			prefs = preferences[r].split(",");
			prefNum = prefs.length;
			maxPrefs[r] = prefNum;
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
		log(2);
	}
	public void fillPagerankMatrix() //fills the matrix used to calculate pagerank with numbers, applies polynomial or a sequence
	{
		for(int r = 0; r < numPeople; r++)
		{
			for(int c = 0; c < numPeople; c++)
			{
				if(!(sequenceKey == ' '))
					pagerankMatrix[r][c] = useSequence(sequenceKey, adjacencyMatrix[r][c]);
				else
				{
					pagerankMatrix[r][c] = (int) Math.pow(adjacencyMatrix[r][c], powerKey);
				}
			}
		}
		log(3);
	}
	public int useSequence(char seqKey, int prefNum) //uses the sequence specified by seqKey
	{
		if(seqKey == 'f')
			return fibonacci(prefNum);
		if(seqKey == 'r')
			return recaman(prefNum);
		if(seqKey == 'p')
			return pascal(prefNum);
		return prefNum;
	}
	public int fibonacci(int num) //finds the numth fibonacci number
	{
		if(num <= 1)
			return num;
		return fibonacci(num - 1) + fibonacci(num - 2);
	}
	public int recaman(int num) //finds the numth recaman number
	{
		if(num == 0)
			return 0;
		if(num == 1)
			return 1;
		if(num == 2)
			return 3;
		if(num == 3)
			return 6;
		if(num == 4)
			return 2;
		if(num == 5)
			return 7;
		return 13;
	}
	public int pascal(int num) //finds the numth highest number in pascals triangle
	{
		if(num == 0)
			return 0;
		if(num == 1)
			return 1;
		if(num == 2)
			return 2;
		if(num == 3)
			return 3;
		if(num == 4)
			return 6;
		if(num == 5)
			return 10;
		return 20;
	}
	private void log(int vLevel) //handles the debugging of the code
    {
    	StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
    	String caller = stackTrace[2].getMethodName();
    	if(vLevel <= verbose && vLevel == 4) //verbose of 4 output
    		System.out.println("Created team, called by method: "+caller);
    	else if(vLevel <= verbose && vLevel == 3) //verbose of 3 output
    		System.out.println("Filled pagerankMatrix, called by: "+caller);
    	else if(vLevel <= verbose && vLevel == 2) //verbose of 2 output
    		System.out.println("Filled adjacencyMatrix, called by: "+caller);
    	else if(vLevel <= verbose && vLevel == 1) //verbose of 1 output
    		System.out.println("Printed teams in method: "+caller);
    }
	public void createTeams() //creates teams accoding to who has the most pagerank and their most preferred choice
	{
		for(int r = 0; r < teamSize-1; r++)
		{
			for(int c = 0; c < numTeams; c++)
			{
				if(r == 0) //this is for adding the first person and their number one pref
				{
					double highestPR = highestPR = -1.0;
					int prIndex = -1;
					for(int i = 0; i < numPeople; i++)
					{
						if(!usedNames.contains(names[i]) && p.getPageRankAt(i) > highestPR)
						{
							highestPR = p.getPageRankAt(i);
							prIndex = i;
						}
					}
					teamsMatrix[r][c] = names[prIndex];
					usedNames.add(names[prIndex]);
					int highestPrefIndex = prIndex;
					for(int i = 0; i < numPeople; i++)
					{
						if(!usedNames.contains(names[i]) && adjacencyMatrix[prIndex][i] > adjacencyMatrix[prIndex][highestPrefIndex])
							highestPrefIndex = i;
					}
					if(highestPrefIndex != prIndex)
					{
						teamsMatrix[r+1][c] = names[highestPrefIndex];
						usedNames.add(names[highestPrefIndex]);
					}
					else
					{
						highestPR = -1.0;
						prIndex = 0;
						for(int i = 0; i < numPeople; i++)
						{
							if(!(usedNames.contains(names[i])) && p.getPageRankAt(i) > highestPR)
							{
								highestPR = p.getPageRankAt(i);
								prIndex = i;
							}
						}
						teamsMatrix[r+1][c] = names[prIndex];
						usedNames.add(names[prIndex]);
					}
				}
				else
				{
					String baseName = teamsMatrix[r][c];
					int baseNameIndex = -1;
					for(int i = 0; i < numPeople && baseNameIndex == -1; i++)
					{
						if(names[i].equals(baseName))
							baseNameIndex = i;
					}
					int highestPrefIndex = baseNameIndex;
					for(int i = 0; i < numPeople; i++)
					{
						if(!usedNames.contains(names[i]) && adjacencyMatrix[r][i] > adjacencyMatrix[r][highestPrefIndex])
							highestPrefIndex = i;
					}
					if(highestPrefIndex != baseNameIndex)
					{
						teamsMatrix[r+1][c] = names[highestPrefIndex];
						usedNames.add(names[highestPrefIndex]);
					}
					else
					{
						double highestPR = -1.0;
						baseNameIndex = -1;
						for(int i = 0; i < numPeople; i++)
						{
							if(!usedNames.contains(names[i]) && p.getPageRankAt(i) > highestPR)
							{
								highestPR = p.getPageRankAt(i);
								baseNameIndex = i;
							}
						}
						if(baseNameIndex >= 0)
						{
							teamsMatrix[r+1][c] = names[baseNameIndex];
							usedNames.add(names[baseNameIndex]);
						}
						else
							teamsMatrix[r+1][c] = "Placeholder";
					}
				}
			}
		}
		log(4);
	}
	private void calcIndividualHappiness() //calculates individual happiness of the people in the teams matrix
	{
		for(int c = 0; c < numTeams; c++)
		{
			for(int r = 0; r < teamSize; r++)
			{
				int happiness = 0;
				String baseName = teamsMatrix[r][c];
				if(baseName.equals("Placeholder"))
					break;
				int baseNameIndex = -1;
				for(int i = 0; i < numPeople && baseNameIndex == -1; i++)
					if(names[i].equals(baseName))
						baseNameIndex = i;
				for(int r2 = 0; r2 < teamSize; r2++)
				{
					String currTeammate = teamsMatrix[r2][c];
					if(!currTeammate.equals("Placeholder"))
					{
						int currTeammateIndex = -1;
						for(int i = 0; i < numPeople && currTeammateIndex == -1; i++)
							if(names[i].equals(currTeammate))
								currTeammateIndex = i;
						if(!currTeammate.equals(baseName))
						{
							int prefPoints = adjacencyMatrix[baseNameIndex][currTeammateIndex];
							if(maxPrefs[baseNameIndex] - prefPoints < 6 && prefPoints != 0)
								happiness += happinessIndex[maxPrefs[baseNameIndex] - prefPoints];

							choicesGot[maxPrefs[baseNameIndex] - prefPoints]++;
						}
					}
				}
				individualHappinessMatrix[r][c] = happiness;
			}	
		}
	}
	private void calcTeamHappiness() //Gauges the happiness of each team.
	{
		int happiness = 0;
		for(int c = 0; c < numTeams; c++)
		{
			happiness = 0;
			for(int r = 0; r < teamSize; r++)
			{
				if(!(teamsMatrix[r][c].equals("Placeholder")))
				{
					happiness += individualHappinessMatrix[r][c];
				}
			}
			teamHappiness[c] = happiness;
		}
	}
	private void calcTotalHappiness() //calculates the total happiness of the set
	{
		totalHappiness = 0;
		for(int c = 0; c < numTeams; c++)
		{
			totalHappiness += teamHappiness[c];
		}
	}
	private void calcAllHappiness() //calls all calcXHappiness() functions in order to get the new happiness of the set
	{
		calcIndividualHappiness();
		calcTeamHappiness();
		calcTotalHappiness();
	}
	public void printAdjacencyMatrix() //prints adjacencyMatrix
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
	public void printPagerankMatrix() //prints pagerankMatrix
	{
		for(int r = 0; r < numPeople; r++)
		{
			for(int c = 0; c < numPeople; c++)
			{
				System.out.print(pagerankMatrix[r][c] + " ");
			}
			System.out.println("");
		}
	}
	public void printTeams() //prints the teams formatted with the number of 1st choices, 2nd choices, and 3rd choices
	{
		System.out.println("Pagerank Teams (" + totalHappiness + ", " + choicesGot[0] + ", " + choicesGot[1] + ", " + choicesGot[2] + ", " + choicesGot[3] + ", " + choicesGot[4] + ", " + choicesGot[5] + ", " + choicesGot[6] +")");
		for (int c = 0; c < numTeams; c++) 
		{ 
			System.out.print("Team " + (c+1) + " (" + teamHappiness[c] + "):");
			for (int r = 0; r < teamSize; r++) 
			{
				if(r < teamSize-1)
				{
					System.out.print(" " + teamsMatrix[r][c] + " (" + individualHappinessMatrix[r][c] + "),");	
				}
				else
				{
					System.out.print(" " + teamsMatrix[r][c] + " (" + individualHappinessMatrix[r][c] + ")");
				}
			}
			System.out.println("");
		}
		log(1);
	}
	public int getAdjacencyMatrixIndex(int r, int c) //gets the index of the adjacency matrix
	{
		return adjacencyMatrix[r][c];
	}
	public int getNumPeople() //gets the number of people
	{
		return numPeople;
	}
	public int getTeamSize() //gets the team size
	{
		return teamSize;
	}
	public int getVerboseLevel() //gets the verbose level
	{
		return verbose;
	}
	public PageRank getPageRank() //gets the pagerank object
	{
		return p;
	}
	public int getIndividualHappiness(int r, int c) //gets the individual happiness at r,c
	{
		return individualHappinessMatrix[r][c];
	}
	public int getTeamHappiness(int index) //gets the team happiness at index
	{
		return teamHappiness[index];
	}
	public int getTotalHappiness() //gets the total happiness
	{
		return totalHappiness;
	}
	public static void main( String[] args )
    {
    	int t = 3;
        int v = 0;
        int p = 1;
        char s = ' ';
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
            	if(args[i].equals("-p"))
            	{
            		i++;
            		if(i < args.length && !args[i].equals("-t") && !args[i].equals("-v") && !args[i].equals("-p") && !args[i].equals("-s"))
            			p = Integer.parseInt(args[i]);
            		else
            			i--;
            	}
            	if(args[i].equals("-s"))
            	{
            		i++;
            		if(i < args.length && !args[i].equals("-t") && !args[i].equals("-v") && !args[i].equals("-p") && !args[i].equals("-s"))
            			s = args[i].charAt(0);
            		else
            			i--;
            	}
            }
        } 
		GoogleHappy test = new GoogleHappy(t, v, p, s);
		System.out.println("");
		test.printTeams();
	}
}