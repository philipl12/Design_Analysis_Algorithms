
public class Main {
	// code for this project from
	// https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
	public static void main (String[] args) throws java.lang.Exception {
		int graph[][] = new int[][] { {0, 16, 13, 0, 0, 0}, 
                                     {0, 0, 10, 12, 0, 0}, 
                                     {0, 4, 0, 0, 14, 0}, 
                                     {0, 0, 9, 0, 0, 20}, 
                                     {0, 0, 0, 7, 0, 4}, 
                                     {0, 0, 0, 0, 0, 0} };

                         
        MaxFlow m = new MaxFlow();
         
        System.out.println("The maximum possible flow is " + 
                            m.fordFulkerson(graph, 0, 5));

                                     
    }
}
