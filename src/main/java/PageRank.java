import java.util.*;
import java.io.*;
public class PageRank {
 
    private int path[][];
    private int totalPrefs[];
    private double pagerank[];
    private double totalRank;
    private int numNodes;
 
    public PageRank(int adjMatrix[][], int people)
    {
    	path = new int[people][people];
    	totalPrefs = new int[people];
    	for(int i = 0; i < people; i++)
    		totalPrefs[i] = 0;
    	pagerank = new double[people];
    	numNodes = people;
        for(int r = 0; r < people; r++)
        {
        	int rPrefs = 0;
	    	for(int c = 0; c < people; c++)
			{
		   		path[r][c] = adjMatrix[r][c];
		   		totalPrefs[r] += path[r][c];
			}
		}
		totalRank = 1.0;
        calc();
    }
    
    public void calc()
    {
    
		double InitialPageRank;
		double OutgoingLinks=0; 
		double DampingFactor = 0.85; 
		double TempPageRank[] = new double[numNodes];

		int ITERATION_STEP=1;

		InitialPageRank = 1/((double) numNodes);

		for(int i=0; i < numNodes; i++)
		{
			this.pagerank[i]=InitialPageRank;
		}   
	  	calcTotalRank();
		while(ITERATION_STEP<=2) // Iterations
		{
			for(int i=0; i < numNodes; i++)
			{  
				TempPageRank[i]=this.pagerank[i];
				this.pagerank[i]=0;
			}
	    
			for(int c = 0; c < numNodes; c++)
			{
				for(int r = 0; r < numNodes; r++)
				{
					if(this.path[r][c] >= 1)
					{    
						this.pagerank[c] += TempPageRank[r]*((double) path[r][c] / totalPrefs[r]);    
					}
				}  
			}    
			calcTotalRank();
	  
			ITERATION_STEP = ITERATION_STEP+1;
		}
		calcTotalRank();
	}    
	public double getPageRankAt(int index)
	{
		return pagerank[index];
	}
	public double getTotalPageRank()
	{
		return totalRank;
	}
	public int getNumNodes()
	{
		return numNodes;
	}
	public void calcTotalRank()
	{
		totalRank = 0;
		for(int i=0; i < numNodes; i++) 
			totalRank += pagerank[i];
	}
}