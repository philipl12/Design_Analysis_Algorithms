import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Sorts {
	
	private static void printArray(int[] a) {
		
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		
		System.out.println();
		System.out.println();
	}
	
	//code from https://www.geeksforgeeks.org/bubble-sort/
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
	
	public static void selectionSort(int[] a) {
		int n =  a.length;
		System.out.println("Selection Sort");
		
		for (int i = 0; i < n - 1; i++) {
			int minimum = i;
			
			for (int j = i + 1; j < n; j++) {
				if (a[minimum] > a[j]) {
					minimum = j;
				}
			}
			
			int temp = a[i];
			a[i] = a[minimum];
			a[minimum] = temp;
		}
		printArray(a);
	}
	
	//code from https://www.geeksforgeeks.org/insertion-sort/
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
	
	public static void main(String[] args) throws IOException {

		//PrintStream out = new PrintStream(new File("output.txt"));
		//PrintStream console = System.out;
		
		//System.setOut(out);
		
		//out.close();
	
	}
	
	
}
