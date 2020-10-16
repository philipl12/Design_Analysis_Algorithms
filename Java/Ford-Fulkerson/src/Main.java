import java.util.Random;

public class Main {
	/**
	 * Find max flow through a graph of size n
	 * with source s and sink t
	 * 
	 * 
	 * @author Philip Lin & Austin Daniel
	 */
	public static void shuffle(long arr[][], int size) {
		Random r = new Random();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (j == 0 || i == j || i == size - 1) {
					arr[i][j] = 0;
				}
				else {
					if(Math.abs(r.nextLong()) % 3 == 1) {
						arr[i][j] = 0;
					}
					else { arr[i][j] = Math.abs(r.nextLong()) % 50; }
				}
			}
		}
		
	}

	public static void main (String[] args) throws java.lang.Exception {
		
	/*	test graph
		long graph[][] = new long[][] { {0, 16, 13, 0, 0, 0}, 
                                     {0, 0, 10, 12, 0, 0}, 
                                     {0, 4, 0, 0, 14, 0}, 
                                     {0, 0, 9, 0, 0, 20}, 
                                     {0, 0, 0, 7, 0, 4}, 
                                     {0, 0, 0, 0, 0, 0} };
	*/
		int[] size = new int[] {10, 100, 1000};
		//int[] size = new int[] {10000};
		//int[] size = new int[] {100000};
		
		for (int i = 0; i < size.length; i++) {
			long graph[][] = new long[size[i]][size[i]];
			shuffle(graph, size[i]);
	                         
	        MaxFlow m = new MaxFlow();
	         
	        m.fordFulkerson(graph, 0, size[i] - 1, size[i]);
		}
		

                                     
    }
}
