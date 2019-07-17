
public class Sorts {
	
	private static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
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
		
		for (int i = 1; i < n - 1; i++) {
			int value = a[i], j = i - 1;
			
			while (j >= 0 && a[j] > value) {
				a[j] = a[j + 1];
				j--;
			}
			
			a[j + 1] = value;
		}
	}
}
