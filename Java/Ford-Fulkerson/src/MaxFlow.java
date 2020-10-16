
public class MaxFlow {
	// code for this project from
	// https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
	//private static final int V = 6;
	public static int compares = 0, arrayAccess = 0;
	public static long startTime;
	public static long endTime;
	public static long duration;
    private int u, v, maxFlow = 0;

	
	private int getMin(int pathFlow, long rGraph) {
		if (pathFlow < (int)rGraph) {
			compares++;
			return pathFlow; 
		}
		
		else {
			compares++;
			return (int)rGraph; 
		}
	}
	
    public void fordFulkerson(long graph[][], int s, int t, int V) {
        long copyOne[][] = new long[V][V];
        long copyTwo[][] = new long[V][V];
        int parent[] = new int[V];
  
        for (u = 0; u < V; u++) {
        	for (v = 0; v < V; v++) {
        		copyOne[u][v] = copyTwo[u][v] = graph[u][v];
        		//copyTwo[u][v] = graph[u][v];
        		arrayAccess += 3;
        	}
        }
        
        startTime = System.nanoTime();
        while (Search.BFS(copyOne, parent, s, t, V)) {
            int pathFlow = Integer.MAX_VALUE;
            
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                pathFlow = getMin(pathFlow, copyOne[u][v]);
                arrayAccess += 3;
            }
            
            for (v = t; v != s; v = parent[v]) { 
                u = parent[v];
                copyOne[u][v] -= pathFlow; 
                copyOne[v][u] += pathFlow;
                arrayAccess += 4;
            }
            maxFlow += pathFlow;
        }
        endTime = System.nanoTime();
		duration = ((endTime - startTime) / 1000);
        
        System.out.println("Max flow using BFS is " + maxFlow);
        System.out.println(duration);
        System.out.println(compares);
        System.out.println(arrayAccess);
        System.out.println();
        maxFlow = compares = arrayAccess = 0;
        duration = 0;
        
        for (int i = 0; i < V; i++) {
        	parent[i] = 0;
        	arrayAccess++;
        }
        
        startTime = System.nanoTime();
        while (Search.DFS(copyTwo, parent, s, t, V)) {
            int pathFlow = Integer.MAX_VALUE;
            
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                pathFlow = getMin(pathFlow, copyTwo[u][v]);
                arrayAccess += 3;
            }
            
            for (v = t; v != s; v = parent[v]) { 
                u = parent[v]; 
                copyTwo[u][v] -= pathFlow;
                copyTwo[v][u] += pathFlow;
                arrayAccess += 4;
            }
            maxFlow += pathFlow;
        }
        endTime = System.nanoTime();
		duration = ((endTime - startTime) / 1000);
        
        System.out.println("Max flow using DFS is " + maxFlow);
        System.out.println(duration);
        System.out.println(compares);
        System.out.println(arrayAccess);
        System.out.println();
        maxFlow = compares = arrayAccess = 0;
        
    } 
}
