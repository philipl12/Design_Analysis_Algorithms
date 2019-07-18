import java.util.Arrays;


public class Sorts {
	
	private static void printArray(int[] a) {
		
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		
		System.out.println();
		System.out.println();
	}
	
	//code from https://www.geeksforgeeks.org/bubble-sort/
	public static void bubbleSort(int[] original) {
		int n = original.length;
		int[] a = Arrays.copyOf(original, n);
		
		
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
	
	//code from https://www.geeksforgeeks.org/selection-sort/
	public static void selectionSort(int[] original) {
		int n =  original.length;
		int[] a = Arrays.copyOf(original, n);
		
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
	public static void insertionSort(int[] original) {
		int n = original.length;
		int[] a = Arrays.copyOf(original, n);
		
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
	
	//code from https://www.baeldung.com/java-merge-sort by Baeldung
	public static void mergeSort(int[] original) {
		int n = original.length;
		int[] a = Arrays.copyOf(original, n);
		
		System.out.println("Merge sort");
		mergeSort(a, n);
		printArray(a);
	}
	
	public static void mergeSort(int[] a, int n) {
		if (n < 2) {
	        return;
	    }
		
	    int mid = n / 2;
	    int[] leftArray = new int[mid];
	    int[] rightArray = new int[n - mid];
	 
	    for (int i = 0; i < mid; i++) {
	    	leftArray[i] = a[i];
	    }
	    for (int i = mid; i < n; i++) {
	    	rightArray[i - mid] = a[i];
	    }
	    mergeSort(leftArray, mid);
	    mergeSort(rightArray, n - mid);
	 
	    merge(a, leftArray, rightArray, mid, n - mid);
	    
	}
	
	public static void merge(int[] a, int[] leftArray, int[] rightArray, int left, int right) {
		int i = 0, j = 0, k = 0;
	    
	    while (i < left && j < right) {
	        if (leftArray[i] <= rightArray[j]) {
	            a[k++] = leftArray[i++];
	        }
	        else {
	            a[k++] = rightArray[j++];
	        }
	    }
	    while (i < left) {
	        a[k++] = leftArray[i++];
	    }
	    while (j < right) {
	        a[k++] = rightArray[j++];
	    }
	}
	
	
    public static int partition(int[] a, int low, int high) { 
        int pivot = a[high];  
        int i = (low - 1);
        for (int j = low; j < high; j++) { 
            if (a[j] <= pivot) { 
                i++; 
  
                int temp = a[i]; 
                a[i] = a[j]; 
                a[j] = temp; 
            } 
        } 
  
        int temp = a[i + 1]; 
        a[i + 1] = a[high]; 
        a[high] = temp; 
  
        return i + 1; 
    } 
    
    public static void quickSort(int[] original) {
    	int n = original.length;
		int[] a = Arrays.copyOf(original, n);
		
		System.out.println("Quick sort");
		quickSort(a, 0, a.length - 1);
		printArray(a);
    }
    
    public static void quickSort(int[] a, int low, int high) { 
        if (low < high) {
            int pivot = partition(a, low, high); 
   
            quickSort(a, low, pivot - 1); 
            quickSort(a, pivot + 1, high); 
        } 
    }
    
    
	public static void main(String[] args) {
	
	}
}
