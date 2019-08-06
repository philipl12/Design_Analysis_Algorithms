
public class Main {

	public static void main (String[] args) throws java.lang.Exception {
		long graph[][] = new long[][] { {0, 16, 13, 0, 0, 0}, 
                                     {0, 0, 10, 12, 0, 0}, 
                                     {0, 4, 0, 0, 14, 0}, 
                                     {0, 0, 9, 0, 0, 20}, 
                                     {0, 0, 0, 7, 0, 4}, 
                                     {0, 0, 0, 0, 0, 0} };

                         
        MaxFlow m = new MaxFlow();
         
        m.fordFulkerson(graph, 0, 5);

                                     
    }
}
