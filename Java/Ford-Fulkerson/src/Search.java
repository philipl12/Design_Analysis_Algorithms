import java.util.LinkedList;

public class Search {
	static final int V = 6;
	
	// code from https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
	// and modified for use with adjacency matrix
	public static void DFSUtil(int rGraph[][], int parent[], boolean visited[], int u) {
        visited[u] = true;
  
        for (int v = 0; v < V; v++) {
        	if(visited[v] == false && rGraph[u][v] > 0) {
        		DFSUtil(rGraph, parent,visited, v);
        		parent[v] = u;
        	}
        }
         

    } 

    public static boolean DFS(int rGraph[][], int parent[], int s, int t) {
        boolean visited[] = new boolean[V];
        
        for(int i = 0; i < V; ++i) {
        	visited[i] = false;
        }
        
        parent[s] = -1;
        DFSUtil(rGraph, parent, visited, s);
        return (visited[t] == true);
    }// end DFS methods

	public static boolean bfs(int rGraph[][],int parent[], int s, int t) {
        boolean visited[] = new boolean[V];
        
        for(int i = 0; i < V; ++i) {
        	visited[i] = false;
        } 

        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (visited[v] == false && rGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        return (visited[t] == true);
    }
}
