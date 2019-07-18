import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;

public class Test {
/*	
	private static void printArray(int[] a) {
		
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		
		System.out.println();
		System.out.println();
	}
	
	public static void bubbleSort(int[] a) {
		int n = a.length;
		System.out.println("Bubble Sort");
		
		for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (a[j] > a[j+1]) { 
                    int temp = a[j]; 
                    a[j] = a[j+1]; 
                    a[j+1] = temp; 
                }
			}
		}
		printArray(a);
	}
	
	public static void insertionSort(int[] a) {
		int n = a .length;
		System.out.println("Insertion sort");
		
		for (int i = 1; i < n; i++) {
			int value = a[i], j = i - 1;
			
			while (j >= 0 && a[j] > value) {
				a[j + 1] = a[j];
				j--;
			}
			
			a[j + 1] = value;
		}
		
		printArray(a);
	}
*/	
	
	public static void main(String[] args) throws IOException {
		Random rand = new Random();
		PrintStream out = new PrintStream(new File("output.txt"));
		//PrintStream console = System.out;
		
		System.setOut(out);
		
		//int[] numTestCases = {10, 100, 1000, 10000};
		
		int[] numTestCases = {10, 100, 1000};
		
		
		try {
			for (int i = 0; i < numTestCases.length; i++) {
				int[] testArray = new int[numTestCases[i]];
				
				for (int j = 0; j < testArray.length; j++) {
					
					testArray[j] = rand.nextInt(numTestCases[i]);
					//System.out.print(testArray[j] + " ");
					
				}
				/*
				for (int h = 0; h < testArray.length; h++) {
					System.out.print(testArray[h] + " ");
				}
				System.out.println();
				*/
				int[] copy = Arrays.copyOf(testArray, testArray.length);
				
				Sorts.bubbleSort(copy);
				
				copy = Arrays.copyOf(testArray, testArray.length);
				
				Sorts.insertionSort(copy);
				
				copy = Arrays.copyOf(testArray, testArray.length);
				
				Sorts.selectionSort(copy);
							
			}
			
			out.close();
		}
		catch (Exception e) {
			System.out.println("Ran into issues");
		}
		
	}

}
