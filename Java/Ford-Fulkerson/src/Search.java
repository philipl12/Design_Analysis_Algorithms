import java.util.LinkedList;

public class Search {
	//static final int V = 6;
	
	// code from https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
	// and modified for use with adjacency matrix
	public static void DFSUtil(long rGraph[][], int parent[], boolean visited[], int u, int V) {
        visited[u] = true;
        MaxFlow.arrayAccess++;
  
        for (int v = 0; v < V; v++) {
        	if(visited[v] == false && rGraph[u][v] > 0) {
        		DFSUtil(rGraph, parent,visited, v, V);
        		parent[v] = u;
        		MaxFlow.arrayAccess += 3;
        		MaxFlow.compares += 2;
        	}
        }
    } 

    public static boolean DFS(long rGraph[][], int parent[], int s, int t, int V) {
        boolean visited[] = new boolean[V];
        
        for(int i = 0; i < V; ++i) {
        	visited[i] = false;
        	MaxFlow.arrayAccess++;
        }
        
        parent[s] = -1;
        DFSUtil(rGraph, parent, visited, s, V);
        
        MaxFlow.arrayAccess += 2; // counted array access for the return as well
        MaxFlow.compares++; // counted compare for return statement
        return (visited[t] == true);
    }// end DFS methods

	public static boolean BFS(long rGraph[][],int parent[], int s, int t, int V) {
        boolean visited[] = new boolean[V];
        
        for(int i = 0; i < V; ++i) {
        	visited[i] = false;
        	MaxFlow.arrayAccess++;
        } 

        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (queue.size() != 0) {
            int u = queue.poll();
            MaxFlow.compares++;

            for (int v = 0; v < V; v++) {
                if (visited[v] == false && rGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                    MaxFlow.arrayAccess += 4;
                    MaxFlow.compares += 2;
                }
            }
        }
        
        MaxFlow.arrayAccess += 3; // counted array access for the return as well
        MaxFlow.compares++; // counted compare for return statement
        return (visited[t] == true);
    }
}
