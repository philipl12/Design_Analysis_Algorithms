import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

public class Test {
	
	
	public static void main(String[] args) throws IOException {
		Random rand = new Random();
		PrintStream out = new PrintStream(new File("output.txt"));
		
		System.setOut(out);
		
		int[] numTestCases = {10, 100, 1000, 10000, 100000};
		
		try {
			for (int i = 0; i < numTestCases.length; i++) {
				long[] testArray = new long[numTestCases[i]];
				
				for (int j = 0; j < testArray.length; j++) {		
					testArray[j] = rand.nextInt(numTestCases[i]);					
				}
				
				System.out.println("Number of elements being sorted: " + numTestCases[i]);
				largeSorts.bubbleSort(testArray);
				largeSorts.selectionSort(testArray);
				largeSorts.insertionSort(testArray);
				largeSorts.mergeSort(testArray);
				largeSorts.quickSort(testArray);
				largeSorts.heapSort(testArray);
				largeSorts.countingSort(testArray);
				largeSorts.radixSort(testArray);
				largeSorts.bucketSort(testArray, numTestCases[i]);
				largeSorts.cocktailSort(testArray);
				System.out.println();
			}
			out.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
