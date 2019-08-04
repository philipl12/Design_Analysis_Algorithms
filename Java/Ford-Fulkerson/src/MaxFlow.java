
public class MaxFlow {
	// code for this project from
	// https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
	static final int V = 6;
	
    public int fordFulkerson(int graph[][], int s, int t) {
        int u, v;
        int rGraph[][] = new int[V][V];
  
        for (u = 0; u < V; u++) {
        	for (v = 0; v < V; v++) {
        		rGraph[u][v] = graph[u][v];
        	}
        }
        int parent[] = new int[V];
  
        int max_flow = 0;
  
        while (Search.bfs(rGraph, parent, s, t)) {
            int path_flow = Integer.MAX_VALUE;
            
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }
            
            for (v = t; v != s; v = parent[v]) { 
                u = parent[v]; 
                rGraph[u][v] -= path_flow; 
                rGraph[v][u] += path_flow; 
            }
            max_flow += path_flow; 
        }
        return max_flow;
    } 
}
