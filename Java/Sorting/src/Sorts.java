import java.util.Arrays;


public class Sorts {
	public static int compare = 0;
	public static int arrayAccess = 0;
	
	//used to print the raw data in the array
	private static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		
		System.out.println();
		System.out.println();
	}
	
	//find max element in array
	private static int getMax(int[] a, int n) { 
        int max = a[0];
        
        for (int i = 1; i < n; i++) {
            if (a[i] > max) { max = a[i]; }
        }
        
        return max; 
    }
	
	//code from https://www.geeksforgeeks.org/bubble-sort/
	public static void bubbleSort(int[] original) {
		int n = original.length;
		int[] a = Arrays.copyOf(original, n);

		for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    compare++;
                    arrayAccess += 6;
                }
                else { 
                	compare++;
                	arrayAccess += 2;
                } //only done when if statement condition is checked but not true
                compare++;
			}
            compare += 2; //once for inner loop and once for outer loop
		}
		compare++; //for the last compare of for loop
		
		System.out.println("Bubble Sort");
		System.out.println("Total comparisons: " + compare);
		System.out.println("Total array accesses: " + arrayAccess);
		System.out.println();
		compare = 0;
		arrayAccess = 0;
		//printArray(a);
	}
	//end bubble sort
	
	//code from https://www.geeksforgeeks.org/selection-sort/
	public static void selectionSort(int[] original) {
		int n =  original.length;
		int[] a = Arrays.copyOf(original, n);

		for (int i = 0; i < n - 1; i++) {
			int minimum = i;
			
			for (int j = i + 1; j < n; j++) {
				if (a[minimum] > a[j]) {
					compare++;
					arrayAccess += 2;
					minimum = j;
				}
				else { 
					compare++;
					arrayAccess += 2;
				} //only done when if statement condition is checked but not true
				compare++;
			}
			
			int temp = a[i];
			a[i] = a[minimum];
			a[minimum] = temp;
			arrayAccess += 4;
			compare += 2;
		}
		compare++;
		
		System.out.println("Selection Sort");
		System.out.println("Total comparisons: " + compare);
		System.out.println("Total array accesses: " + arrayAccess);
		System.out.println();
		compare = 0;
		arrayAccess = 0;
		//printArray(a);
	}
	//end selection sort
	
	//code from https://www.geeksforgeeks.org/insertion-sort/
	public static void insertionSort(int[] original) {
		int n = original.length;
		int[] a = Arrays.copyOf(original, n);
	
		for (int i = 1; i < n; i++) {
			int value = a[i], j = i - 1;
			
			while (j >= 0 && a[j] > value) {
				a[j + 1] = a[j];
				j--;
				compare += 3;
				arrayAccess += 3;
			} //decided to leave out compares for when while condition is false
			
			a[j + 1] = value;
			arrayAccess += 2;
			compare++;
		}
		compare++; //for the for loops that compared but didn't enter the body
		
		System.out.println("Insertion sort");
		System.out.println("Total comparisons: " + compare);
		System.out.println("Total array accesses: " + arrayAccess);
		System.out.println();
		compare = 0;
		arrayAccess = 0;
		//printArray(a);
	}
	//end insertion sort
	
	//code from https://www.baeldung.com/java-merge-sort by Baeldung
	public static void mergeSort(int[] original) {
		int n = original.length;
		int[] a = Arrays.copyOf(original, n);
		
		mergeSort(a, n);
		
		System.out.println("Merge sort");
		System.out.println("Total comparisons: " + compare);
		System.out.println("Total array accesses: " + arrayAccess);
		System.out.println();
		compare = 0;
		arrayAccess = 0;
		//printArray(a);
	}
	
	public static void mergeSort(int[] a, int n) {
		if (n < 2) {
			compare++;
	        return;
	    }
		else { compare++; } //only done when if statement condition is checked but not true
		
	    int mid = n / 2;
	    int[] leftArray = new int[mid];
	    int[] rightArray = new int[n - mid];
	 
	    for (int i = 0; i < mid; i++) {
	    	leftArray[i] = a[i];
	    	arrayAccess +=2;
	    	compare++;
	    }
	    
	    for (int i = mid; i < n; i++) {
	    	rightArray[i - mid] = a[i];
	    	arrayAccess += 2;
	    	compare++;
	    }
	    
	    compare += 2; //last compare of both for loops when condition becomes false
	    mergeSort(leftArray, mid);
	    mergeSort(rightArray, n - mid);
	 
	    merge(a, leftArray, rightArray, mid, n - mid);
	}
	
	public static void merge(int[] a, int[] leftArray, int[] rightArray, int left, int right) {
		int i = 0, j = 0, k = 0;
	    
	    while (i < left && j < right) { 
	    	compare += 3;
	    	
	        if (leftArray[i] <= rightArray[j]) {
	            a[k++] = leftArray[i++];
	            compare++;
	            arrayAccess += 4;
	        }
	        else {
	            a[k++] = rightArray[j++];
	            compare++;
	            arrayAccess += 4;
	        }
	    } //decided to leave out compares for when while condition is false
	    
	    while (i < left) {
	        a[k++] = leftArray[i++];
	        compare++;
	        arrayAccess += 2;
	    } //decided to leave out compares for when while condition is false
	    
	    while (j < right) {
	        a[k++] = rightArray[j++];
	        compare++;
	        arrayAccess += 2;
	    } //decided to leave out compares for when while condition is false
	}
	//end merge sort
	
	//code https://www.geeksforgeeks.org/quick-sort/
    public static void quickSort(int[] original) {
    	int n = original.length;
		int[] a = Arrays.copyOf(original, n);
		
		quickSort(a, 0, a.length - 1);
		
		System.out.println("Quick sort");
		System.out.println("Total comparisons: " + compare);
		System.out.println("Total array accesses: " + arrayAccess);
		System.out.println();
		compare = 0;
		arrayAccess = 0;
		//printArray(a);
    }
    
    public static void quickSort(int[] a, int low, int high) { 
        if (low < high) {
        	compare++;
            int pivot = partition(a, low, high); 
   
            quickSort(a, low, pivot - 1); 
            quickSort(a, pivot + 1, high); 
        }
        else { compare++; } //only done when if statement condition is checked but not true
    }
    
    private static int partition(int[] a, int low, int high) { 
        int pivot = a[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) { 
            if (a[j] <= pivot) {
                i++;
  
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                
                compare++;
                arrayAccess += 5;
            }
            else { 
            	compare++;
            	arrayAccess++;
            } //only done when if statement condition is checked but not true
            compare++;
        }
        compare++;
        
        int temp = a[i + 1]; 
        a[i + 1] = a[high]; 
        a[high] = temp; 
  
        arrayAccess += 5;
        return i + 1; 
    }
    //end quick sort
    
    //code from https://www.geeksforgeeks.org/heap-sort/
    public static void heapSort(int original[]) {	
    	int[] a = Arrays.copyOf(original, original.length);
        int n = a.length; 
        
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(a, n, i); 
        }
        
        for (int i = n - 1; i >= 0; i--) {
            int temp = a[0]; 
            a[0] = a[i]; 
            a[i] = temp; 
  
            heapify(a, i, 0); 
        }
        
        System.out.println("Heap sort");
		System.out.println("Total comparisons: " + compare);
		System.out.println("Total array accesses: " + arrayAccess);
		System.out.println();
		compare = 0;
		arrayAccess = 0;
        //printArray(a);
    } 

    private static void heapify(int a[], int n, int i) { 
        int largest = i;
        int left = 2 * i + 1; 
        int right = 2 * i + 2;
 
        if (left < n && a[left] > a[largest]) {
            largest = left;
            compare += 3;
            arrayAccess += 2;
        }
        else {
        	compare += 3;
            arrayAccess += 2;
        } //only done when if statement condition is checked but not true
        
        if (right < n && a[right] > a[largest]) {
            largest = right;
            compare += 3;
            arrayAccess += 2;
        }
        else {
        	compare += 3;
            arrayAccess += 2;
        } //only done when if statement condition is checked but not true
        
        if (largest != i) { 
            int temp = a[i]; 
            a[i] = a[largest]; 
            a[largest] = temp; 
            
            
            compare++;
            arrayAccess += 4;
            heapify(a, n, largest);
        }
        else {
        	compare ++;
        } //only done when if statement condition is checked but not true
    } 
    //end heap sort
    
    //code from https://www.geeksforgeeks.org/counting-sort/
    public static void countingSort(int[] original) { 
    	int[] a = Arrays.copyOf(original, original.length);
        int n = a.length, i;
        int[] output = new int[n]; 
        int[] count = new int[n];

        for (i = 0; i < n; ++i) { 
            ++count[a[i]];
            compare++;
            arrayAccess += 2;
        }
        for (i = 1; i <= n - 1; ++i) {
            count[i] += count[i - 1];
            compare++;
            arrayAccess += 2;
        }
        for (i = n - 1; i >= 0; i--) { 
            output[count[a[i]] - 1] = a[i];
            --count[a[i]];
            compare++;
            arrayAccess += 6;
        }
        for (i = 0; i < n; ++i) {
            a[i] = output[i];
            compare++;
            arrayAccess += 2;
        }
        compare += 4; //for all for loops last false condition
        
        System.out.println("Counting sort");
		System.out.println("Total comparisons: " + compare);
		System.out.println("Total array accesses: " + arrayAccess);
		System.out.println();
		compare = 0;
		arrayAccess = 0;
        //printArray(a);
    }
    
    //counting sort used for radix sort
    public static void countingSort(int[] a, int n, int exp) {
        int[] output = new int[n];
        int[] count = new int[10];
        int i;
  
        for (i = 0; i < n; i++) {
            count[(a[i] / exp) % 10]++;
            compare++;
            arrayAccess += 2;
        }
        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
            compare++;
            arrayAccess += 2;
    	}
        for (i = n - 1; i >= 0; i--) { 
            output[count[(a[i] / exp) % 10] - 1] = a[i];
            count[(a[i] / exp) % 10]--;
            compare++;
            arrayAccess += 6;
        } 
  
        for (i = 0; i < n; i++) {
            a[i] = output[i];
            compare++;
            arrayAccess += 2;
        }
        compare += 4; //for all for loops last false condition
    }
    //end counting sort
    
    //code from https://www.geeksforgeeks.org/radix-sort/
    public static void radixSort(int[] original) { 
    	int[] a = Arrays.copyOf(original, original.length);
        int n = a.length; 
    	
    	int maxNum = getMax(a, n); 
 
        for (int exp = 1; maxNum/exp > 0; exp *= 10) {
            countingSort(a, n, exp);
            compare++;
        }
        compare++;
        
        System.out.println("Radix sort");
		System.out.println("Total comparisons: " + compare);
		System.out.println("Total array accesses: " + arrayAccess);
		System.out.println();
		compare = 0;
		arrayAccess = 0;
        //printArray(a);
    }
    //end radix sort
    
    //code from https://www.javacodex.com/Sorting/Bucket-Sort
    public static void bucketSort(int[] original, int maxVal) {
    	int[] a = Arrays.copyOf(original, original.length);
        int n = a.length;
    	int [] bucket=new int[maxVal];

        for (int i = 0; i < n; i++) {
           bucket[a[i]]++;
           compare++;
           arrayAccess += 2;
        }
   
        int outPos = 0;
        for (int i = 0; i < bucket.length; i++) {
           for (int j = 0; j < bucket[i]; j++) {
              a[outPos++] = i;
              compare++;
              arrayAccess += 2; //one extra for array access compare
           }
           compare++;
        }
        compare += 2; //for all for loops last false condition
        
        System.out.println("Bucket sort");
		System.out.println("Total comparisons: " + compare);
		System.out.println("Total array accesses: " + arrayAccess);
		System.out.println();
		compare = 0;
		arrayAccess = 0;
        //printArray(a);
    }
    //end bucket sort
    
    
    //code from https://www.geeksforgeeks.org/cocktail-sort/
    public static void cocktailSort(int[] original) { 
        int[] a = Arrays.copyOf(original, original.length);
        int end = a.length, start = 0;
        boolean swapped = true;
  
        while (swapped == true) {
        	compare++; //do compare increment in case loop is broken
            swapped = false;
  
            for (int i = start; i < end - 1; ++i) { 
                if (a[i] > a[i + 1]) {
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    swapped = true;
                    compare++;
                    arrayAccess += 6;
                }
                else {
                	compare++;
                	arrayAccess += 2;
                } //only done when if statement condition is checked but not true
                compare++;
            }
            compare++; //this increment pertaining to this for loop
            
            if (swapped == false) { 
            	compare++;
            	break; 
            }
            else { compare++; } //only done when if statement condition is checked but not true

            swapped = false;
            end--;
 
            for (int i = end - 1; i >= start; i--) { 
                if (a[i] > a[i + 1]) {
                    int temp = a[i]; 
                    a[i] = a[i + 1]; 
                    a[i + 1] = temp; 
                    swapped = true;
                    compare++;
                    arrayAccess += 6;
                }
                else {
                	compare++;
                	arrayAccess += 2;
                } //only done when if statement condition is checked but not true
                compare++;
            }
            compare++; //this increment pertaining to this for loop
 
            start++;
        } //decided to leave out compares for when while condition is false
        
        System.out.println("Cocktail sort");
		System.out.println("Total comparisons: " + compare);
		System.out.println("Total array accesses: " + arrayAccess);
		System.out.println();
		compare = 0;
		arrayAccess = 0;
        //printArray(a);
    }
    //end cocktail sort
    
	public static void main(String[] args) {
	
	}
}
