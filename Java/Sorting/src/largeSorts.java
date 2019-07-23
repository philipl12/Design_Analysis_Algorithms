import java.util.Arrays;


public class largeSorts {
	public static long compare = 0;
	public static long arrayAccess = 0;
	public static long startTime;
	public static long endTime;
	public static long duration;
	
	//used to print the raw data in the array
	private static void printArray(long[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		
		System.out.println();
		System.out.println();
	}
	
	//find max element in array
	private static long getMax(long[] a, int n) { 
        long max = a[0];
        
        for (int i = 1; i < n; i++) {
            if (a[i] > max) { max = a[i]; }
        }
        
        return max; 
    }
	
	//code from https://www.geeksforgeeks.org/bubble-sort/
	public static void bubbleSort(long[] original) {
		int n = original.length;
		long[] a = Arrays.copyOf(original, n);
		startTime = System.nanoTime();

		for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    long temp = a[j];
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
		endTime = System.nanoTime();
		duration = ((endTime - startTime) / 1000);
		//duration = ((endTime - startTime) / 1000);
		
		//System.out.println("Bubble Sort");
		System.out.println(compare);
		System.out.println(arrayAccess);
		System.out.println(duration);
		System.out.println();
		compare = 0;
		arrayAccess = 0;
		//printArray(a);
	}
	//end bubble sort
	
	//code from https://www.geeksforgeeks.org/selection-sort/
	public static void selectionSort(long[] original) {
		int n =  original.length;
		long[] a = Arrays.copyOf(original, n);
		startTime = System.nanoTime();

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
			
			long temp = a[i];
			a[i] = a[minimum];
			a[minimum] = temp;
			arrayAccess += 4;
			compare += 2;
		}
		compare++;
		endTime = System.nanoTime();
		duration = ((endTime - startTime) / 1000);
		
		//System.out.println("Selection Sort");
		System.out.println(compare);
		System.out.println(arrayAccess);
		System.out.println(duration);
		System.out.println();
		compare = 0;
		arrayAccess = 0;
		//printArray(a);
	}
	//end selection sort
	
	//code from https://www.geeksforgeeks.org/insertion-sort/
	public static void insertionSort(long[] original) {
		int n = original.length;
		long[] a = Arrays.copyOf(original, n);
		startTime = System.nanoTime();
	
		for (int i = 1; i < n; i++) {
			long value = a[i];
			int j = i - 1;
			
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
		endTime = System.nanoTime();
		duration = ((endTime - startTime) / 1000);
		
		//System.out.println("Insertion sort");
		System.out.println(compare);
		System.out.println(arrayAccess);
		System.out.println(duration);
		System.out.println();
		compare = 0;
		arrayAccess = 0;
		//printArray(a);
	}
	//end insertion sort
	
	//code from https://www.baeldung.com/java-merge-sort by Baeldung
	public static void mergeSort(long[] original) {
		int n = original.length;
		long[] a = Arrays.copyOf(original, n);
		startTime = System.nanoTime();
		
		mergeSort(a, n);
		
		endTime = System.nanoTime();
		duration = ((endTime - startTime) / 1000);
		
		//System.out.println("Merge sort");
		System.out.println(compare);
		System.out.println(arrayAccess);
		System.out.println(duration);
		System.out.println();
		compare = 0;
		arrayAccess = 0;
		//printArray(a);
	}
	
	public static void mergeSort(long[] a, int n) {
		if (n < 2) {
			compare++;
	        return;
	    }
		else { compare++; } //only done when if statement condition is checked but not true
		
	    int mid = n / 2;
	    long[] leftArray = new long[mid];
	    long[] rightArray = new long[n - mid];
	 
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
	
	public static void merge(long[] a, long[] leftArray, long[] rightArray, int left, int right) {
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
    public static void quickSort(long[] original) {
    	int n = original.length;
    	long[] a = Arrays.copyOf(original, n);
    	startTime = System.nanoTime();
		
		quickSort(a, 0, a.length - 1);
		
		endTime = System.nanoTime();
		duration = ((endTime - startTime) / 1000);
		
		//System.out.println("Quick sort");
		System.out.println(compare);
		System.out.println(arrayAccess);
		System.out.println(duration);
		System.out.println();
		compare = 0;
		arrayAccess = 0;
		//printArray(a);
    }
    
    public static void quickSort(long[] a, int low, int high) { 
        if (low < high) {
        	compare++;
            int pivot = partition(a, low, high); 
   
            quickSort(a, low, pivot - 1); 
            quickSort(a, pivot + 1, high); 
        }
        else { compare++; } //only done when if statement condition is checked but not true
    }
    
    private static int partition(long[] a, int low, int high) { 
    	long pivot = a[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) { 
            if (a[j] <= pivot) {
                i++;
  
                long temp = a[i];
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
        
        long temp = a[i + 1]; 
        a[i + 1] = a[high]; 
        a[high] = temp; 
  
        arrayAccess += 5;
        return i + 1; 
    }
    //end quick sort
    
    //code from https://www.geeksforgeeks.org/heap-sort/
    public static void heapSort(long[] original) {	
    	long[] a = Arrays.copyOf(original, original.length);
        int n = a.length;
        startTime = System.nanoTime();
        
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(a, n, i); 
        }
        
        for (int i = n - 1; i >= 0; i--) {
        	long temp = a[0]; 
            a[0] = a[i]; 
            a[i] = temp; 
  
            heapify(a, i, 0); 
        }
        
        endTime = System.nanoTime();
		duration = ((endTime - startTime) / 1000);
        
        //System.out.println("Heap sort");
		System.out.println(compare);
		System.out.println(arrayAccess);
		System.out.println(duration);
		System.out.println();
		compare = 0;
		arrayAccess = 0;
        //printArray(a);
    } 

    private static void heapify(long a[], int n, int i) { 
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
        	long temp = a[i]; 
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
    public static void countingSort(long[] original) { 
    	long[] a = Arrays.copyOf(original, original.length);
        int n = a.length, i;
        int[] output = new int[n]; 
        int[] count = new int[n];
        startTime = System.nanoTime();

        for (i = 0; i < n; ++i) { 
            ++count[(int) a[i]];
            compare++;
            arrayAccess += 2;
        }
        for (i = 1; i <= n - 1; ++i) {
            count[i] += count[i - 1];
            compare++;
            arrayAccess += 2;
        }
        for (i = n - 1; i >= 0; i--) { 
            output[count[(int) a[i]] - 1] = (int) a[i];
            --count[(int) a[i]];
            compare++;
            arrayAccess += 6;
        }
        for (i = 0; i < n; ++i) {
            a[i] = output[i];
            compare++;
            arrayAccess += 2;
        }
        compare += 4; //for all for loops last false condition
        endTime = System.nanoTime();
		duration = ((endTime - startTime) / 1000);
        
        //System.out.println("Counting sort");
		System.out.println(compare);
		System.out.println(arrayAccess);
		System.out.println(duration);
		System.out.println();
		compare = 0;
		arrayAccess = 0;
        //printArray(a);
    }
    
    //counting sort used for radix sort
    public static void countingSort(long[] a, int n, long exp) {
    	long[] output = new long[n];
        long[] count = new long[10];
        int i;
  
        for (i = 0; i < n; i++) {
            count[(int) ((a[i] / exp) % 10)]++;
            compare++;
            arrayAccess += 2;
        }
        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
            compare++;
            arrayAccess += 2;
    	}
        for (i = n - 1; i >= 0; i--) { 
            output[(int) (count[(int) ((a[i] / exp) % 10)] - 1)] = a[i];
            count[(int) ((a[i] / exp) % 10)]--;
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
    public static void radixSort(long[] original) { 
    	long[] a = Arrays.copyOf(original, original.length);
        int n = a.length;
        startTime = System.nanoTime();
    	
    	long maxNum = getMax(a, n); 
 
        for (long exp = 1; maxNum/exp > 0; exp *= 10) {
            countingSort(a, n, exp);
            compare++;
        }
        compare++;
        endTime = System.nanoTime();
		duration = ((endTime - startTime) / 1000);
        
        //System.out.println("Radix sort");
		System.out.println(compare);
		System.out.println(arrayAccess);
		System.out.println(duration);
		System.out.println();
		compare = 0;
		arrayAccess = 0;
        //printArray(a);
    }
    //end radix sort
    
    //code from https://www.javacodex.com/Sorting/Bucket-Sort
    public static void bucketSort(long[] original, int maxVal) {
    	long[] a = Arrays.copyOf(original, original.length);
        int n = a.length;
    	int [] bucket=new int[maxVal];
    	startTime = System.nanoTime();

        for (int i = 0; i < n; i++) {
           bucket[(int) a[i]]++;
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
        endTime = System.nanoTime();
		duration = ((endTime - startTime) / 1000);
        
        //System.out.println("Bucket sort");
		System.out.println(compare);
		System.out.println(arrayAccess);
		System.out.println(duration);
		System.out.println();
		compare = 0;
		arrayAccess = 0;
        //printArray(a);
    }
    //end bucket sort
    
    
    //code from https://www.geeksforgeeks.org/cocktail-sort/
    public static void cocktailSort(long[] original) { 
    	long[] a = Arrays.copyOf(original, original.length);
        int end = a.length, start = 0;
        boolean swapped = true;
        startTime = System.nanoTime();
  
        while (swapped == true) {
        	compare++; //do compare increment in case loop is broken
            swapped = false;
  
            for (int i = start; i < end - 1; ++i) { 
                if (a[i] > a[i + 1]) {
                	long temp = a[i];
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
                	long temp = a[i]; 
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
        endTime = System.nanoTime();
		duration = ((endTime - startTime) / 1000);
        
        //System.out.println("Cocktail sort");
		System.out.println(compare);
		System.out.println(arrayAccess);
		System.out.println(duration);
		System.out.println();
		compare = 0;
		arrayAccess = 0;
        //printArray(a);
    }
    //end cocktail sort
    
	public static void main(String[] args) {
	
	}
}
