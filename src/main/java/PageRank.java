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
		   		if(path[r][c] > rPrefs)
		   			rPrefs = path[r][c];
			}
			for(int i = rPrefs; i > 0; i--)
				totalPrefs[r] += i;
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
		System.out.printf("Total Number of Nodes :"+(int) numNodes+"\t Initial PageRank  of All Nodes :"+InitialPageRank+"\n");
	    System.out.println("Total pagerank:"+totalRank);

		// 0th ITERATION  _ OR _ INITIALIZATION PHASE
		for(int i=0; i < numNodes; i++)
		{
			this.pagerank[i]=InitialPageRank;
		}   
	  
		System.out.printf("\n Initial PageRank Values , 0th Step \n");
		for(int i=0; i < numNodes; i++)
		{
			System.out.printf(" Page Rank of "+ (i+1) +" is :\t"+this.pagerank[i]+"\n");
		}  
	  	calcTotalRank();
		while(ITERATION_STEP<=2) // Iterations
		{
			// Store the PageRank for All Nodes in Temporary Array 
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
						// Calculate PageRank     
						this.pagerank[c] += TempPageRank[r]*((double) path[r][c] / totalPrefs[r]);    
					}
				}
				//System.out.println("Interation "+c+":"+pagerank[3]);  
			}    
	     
			System.out.printf("\n After "+ITERATION_STEP+"th Step \n");
	  
			for(int i=0; i < numNodes; i++) 
			    System.out.printf(" Page Rank of "+(i+1)+" is :\t"+this.pagerank[i]+"\n"); 
			calcTotalRank();
	  
			ITERATION_STEP = ITERATION_STEP+1;
		}

		/* Add the Damping Factor to PageRank
		for(int i=0; i < numNodes; i++)
		{
			this.pagerank[i]=(1-DampingFactor)+ DampingFactor*this.pagerank[i]; 
		} */
	  
		// Display PageRank
		System.out.printf("\n Final Page Rank : \n"); 
		for(int i=0; i < numNodes; i++)
		{
			System.out.printf(" Page Rank of "+(i+1)+" is :\t"+this.pagerank[i]+"\n"); 
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
		System.out.println("Page Rank total for this iteration: "+totalRank);
	}
}