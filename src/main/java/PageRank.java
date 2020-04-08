import java.util.*;
import java.io.*;
public class PageRank {
 
    public int path[][];
    public double pagerank[];
 
    public PageRank(int adjMatrix[][], int people)
    {
    	path = new int[people][people];
    	pagerank = new double[people];
        for(int r = 0; r < people; r++)
	    	for(int c = 0; c < people; c++)
			{
		   		path[r][c] = adjMatrix[r][c];
			}
        calc(people);
    }
    
    public void calc(double totalNodes)
    {
    
		double InitialPageRank;
		double OutgoingLinks=0; 
		double DampingFactor = 0.85; 
		double TempPageRank[] = new double[(int) totalNodes];

		int ExternalNodeNumber;
		int InternalNodeNumber; 
		int ITERATION_STEP=1;

		InitialPageRank = 1/totalNodes;
		System.out.printf(" Total Number of Nodes :"+(int) totalNodes+"\t Initial PageRank  of All Nodes :"+InitialPageRank+"\n");
	 
		// 0th ITERATION  _ OR _ INITIALIZATION PHASE
		for(int i=0; i < totalNodes; i++)
		{
			this.pagerank[i]=InitialPageRank;
		}   
	  
		System.out.printf("\n Initial PageRank Values , 0th Step \n");
		for(int i=0; i < totalNodes; i++)
		{
			System.out.printf(" Page Rank of "+ (i+1) +" is :\t"+this.pagerank[i]+"\n");
		}  
	  
		while(ITERATION_STEP<=2) // Iterations
		{
			// Store the PageRank for All Nodes in Temporary Array 
			for(int i=0; i < totalNodes; i++)
			{  
				TempPageRank[i]=this.pagerank[i];
				this.pagerank[i]=0;
			}
	    
			for(InternalNodeNumber = 0; InternalNodeNumber < totalNodes; InternalNodeNumber++)
			{
				for(ExternalNodeNumber = 0; ExternalNodeNumber < totalNodes; ExternalNodeNumber++)
				{
					if(this.path[ExternalNodeNumber][InternalNodeNumber] >= 1)
					{ 
						OutgoingLinks=0;  // Count the Number of Outgoing Links for each ExternalNodeNumber
						for(int i=0; i < totalNodes; i++)
						{
							if(this.path[ExternalNodeNumber][i] >= 1 )
							{
								OutgoingLinks++; // Counter for Outgoing Links
							}    
						} 
						// Calculate PageRank     
						this.pagerank[InternalNodeNumber] += TempPageRank[ExternalNodeNumber]*(1/OutgoingLinks);    
					}
				}  
			}    
	     
			System.out.printf("\n After "+ITERATION_STEP+"th Step \n");
	  
			for(int i=0; i < totalNodes; i++) 
			    System.out.printf(" Page Rank of "+(i+1)+" is :\t"+this.pagerank[i]+"\n"); 
	  
			ITERATION_STEP = ITERATION_STEP+1;
		}

		// Add the Damping Factor to PageRank
		for(int i=0; i < totalNodes; i++)
		{
			this.pagerank[i]=(1-DampingFactor)+ DampingFactor*this.pagerank[i]; 
		} 
	  
		// Display PageRank
		System.out.printf("\n Final Page Rank : \n"); 
		for(int i=0; i < totalNodes; i++)
		{
			System.out.printf(" Page Rank of "+(i+1)+" is :\t"+this.pagerank[i]+"\n"); 
		}
	  
	}    

}