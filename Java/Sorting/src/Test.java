import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

public class Test {
	
	
	public static void main(String[] args) throws IOException {
		Random rand = new Random();
		PrintStream out = new PrintStream(new File("output.txt"));
		//PrintStream console = System.out;
		
		System.setOut(out);
		
		int[] numTestCases = {10, 100, 1000, 10000};
		
		try {
			for (int i = 0; i < numTestCases.length; i++) {
				int[] testArray = new int[numTestCases[i]];
				
				for (int j = 0; j < testArray.length; j++) {		
					testArray[j] = rand.nextInt(numTestCases[i]);					
				}
				/*
				System.out.println("Number of elements being sorted: " + numTestCases[i]);
				System.out.println();
				
				Sorts.bubbleSort(testArray);
				Sorts.selectionSort(testArray);
				Sorts.insertionSort(testArray);
				Sorts.mergeSort(testArray);
				Sorts.quickSort(testArray);
				Sorts.heapSort(testArray);
				Sorts.countingSort(testArray);
				Sorts.radixSort(testArray);
				Sorts.bucketSort(testArray, numTestCases[i]);
				Sorts.cocktailSort(testArray);
				System.out.println();
				*/
			}
			/*
			long[] largeArray = new long[100000];
			for (int j = 0; j < 100000; j++) {		
				largeArray[j] = rand.nextInt(10000);
			}
			largeSorts.bubbleSort(largeArray);
			*/
			out.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
