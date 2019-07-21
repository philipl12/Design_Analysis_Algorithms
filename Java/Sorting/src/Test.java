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
				Sorts.bubbleSort(testArray);
				Sorts.insertionSort(testArray);	
				Sorts.selectionSort(testArray);
				Sorts.mergeSort(testArray);
				Sorts.quickSort(testArray);
				Sorts.heapSort(testArray);
				Sorts.countingSort(testArray);
				
				*/
				Sorts.radixSort(testArray);
				
				
			}
			
			out.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
